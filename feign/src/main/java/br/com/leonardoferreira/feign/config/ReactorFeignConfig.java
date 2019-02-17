package br.com.leonardoferreira.feign.config;

import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Logger;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.Target;
import feign.codec.ErrorDecoder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ReactorFeignConfig {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder()
//                .retryer(new Retryer() {
//                    @Override
//                    public void continueOrPropagate(RetryableException e) {
//
//                    }
//
//                    @Override
//                    public Retryer clone() {
//                        return null;
//                    }
//                })
//                .logger(new Logger() {
//                    @Override
//                    protected void log(String configKey, String format, Object... args) {
//
//                    }
//                })
//                .errorDecoder(new ErrorDecoder() {
//                    @Override
//                    public Exception decode(String methodKey, Response response) {
//                        return null;
//                    }
//                })
                .invocationHandlerFactory(new InvocationHandlerFactory() {
                    @Override
                    public InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch) {
                        return new ReactorInvocationHandler(target, dispatch);
                    }
                });
    }

}
