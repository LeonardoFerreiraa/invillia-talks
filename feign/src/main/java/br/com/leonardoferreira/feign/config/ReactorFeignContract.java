package br.com.leonardoferreira.feign.config;

import feign.MethodMetadata;
import feign.Util;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactorFeignContract extends SpringMvcContract {

    @Autowired(required = false)
    public ReactorFeignContract(List<AnnotatedParameterProcessor> annotatedParameterProcessors,
                                ConversionService conversionService) {
        super(annotatedParameterProcessors, conversionService);
    }

    @Override
    public List<MethodMetadata> parseAndValidatateMetadata(Class<?> targetType) {
        List<MethodMetadata> metadataList = super.parseAndValidatateMetadata(targetType);
        for (MethodMetadata methodMetadata : metadataList) {
            Type type = methodMetadata.returnType();

            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;

                if (parameterizedType.getRawType().equals(Mono.class)) {
                    methodMetadata.returnType(Util.resolveLastTypeParameter(type, Mono.class));
                } else if (parameterizedType.getRawType().equals(Flux.class)) {
                    methodMetadata.returnType(Util.resolveLastTypeParameter(type, Flux.class));
                }
            }
        }
        return metadataList;
    }
}
