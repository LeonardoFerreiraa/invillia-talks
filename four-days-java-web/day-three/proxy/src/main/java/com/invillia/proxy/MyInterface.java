package com.invillia.proxy;

public interface MyInterface {

    String bla();

    String xablau();

    String mineirão();

    default String blabla() {
        return "ok";
    }

}
