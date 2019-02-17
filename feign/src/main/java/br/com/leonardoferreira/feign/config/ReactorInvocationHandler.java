package br.com.leonardoferreira.feign.config;

import feign.InvocationHandlerFactory;
import feign.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactorInvocationHandler implements InvocationHandler {

    private Target target;

    private Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;

    public ReactorInvocationHandler(Target target, Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
        this.target = target;
        this.dispatch = dispatch;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (ReflectionUtils.isEqualsMethod(method)) {
            return args.length == 1 && target.equals(args[0]);
        }

        if (ReflectionUtils.isToStringMethod(method)) {
            return target.toString();
        }

        if (Mono.class.isAssignableFrom(method.getReturnType())) {
            return Mono.create(monoSink -> {
                try {
                    monoSink.success(dispatch.get(method).invoke(args));
                } catch (Throwable e) {
                    monoSink.error(e);
                }
            }).subscribeOn(Schedulers.parallel());
        }

        if (Flux.class.isAssignableFrom(method.getReturnType())) {
            return Flux.create(fluxSink -> {
                try {
                    fluxSink.next(dispatch.get(method).invoke(args));
                    fluxSink.complete();
                } catch (Throwable e) {
                    fluxSink.error(e);
                }
            }).subscribeOn(Schedulers.parallel());
        }

        return dispatch.get(method).invoke(args);
    }

}
