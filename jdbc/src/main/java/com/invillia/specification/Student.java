package com.invillia.specification;

public class Student implements PersonSpecification {

    @Override
    public void sayMyName() {
        System.out.println("Ola eu sou um estudante");
    }
}
