package com.invillia.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class MyHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.getName();
    }

}
