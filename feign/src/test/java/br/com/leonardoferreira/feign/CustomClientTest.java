package br.com.leonardoferreira.feign;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomClientTest {

    @Test
    void clientTest() {
        MyClient myClient = (MyClient) Proxy.newProxyInstance(CustomClientTest.class.getClassLoader(),
                new Class[]{MyClient.class}, new GenericClientImpl());
        String anything = myClient.anything();
        System.out.println(anything);
        Assertions.assertNotNull(anything);

        String ip = myClient.ip();
        System.out.println(ip);
        Assertions.assertNotNull(ip);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EndPoint {
        String value();
    }

    interface MyClient {
        @EndPoint("/anything")
        String anything();

        @EndPoint("/ip")
        String ip();
    }

    class GenericClientImpl implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("Method={}", method.getName());

            if (method.isAnnotationPresent(EndPoint.class)) {
                EndPoint endPoint = method.getAnnotation(EndPoint.class);
                HttpClient httpClient = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_2)
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://0.0.0.0:8088" + endPoint.value()))
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            }

            return null;
        }
    }

}
