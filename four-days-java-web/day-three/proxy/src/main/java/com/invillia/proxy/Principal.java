package com.invillia.proxy;

import java.lang.reflect.Proxy;

public class Principal {

    public static void main(String[] args) {
        MyInterface myInterface = (MyInterface) Proxy.newProxyInstance(
                Principal.class.getClassLoader(),
                new Class[]{MyInterface.class},
                new MyHandler()
        );

        System.out.println(myInterface.bla());
        System.out.println(myInterface.mineirão());
        System.out.println(myInterface.xablau());
        System.out.println(myInterface.blabla());
    }



}
