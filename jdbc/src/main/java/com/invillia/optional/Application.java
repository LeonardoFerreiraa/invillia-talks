package com.invillia.optional;

import java.util.Optional;

public class Application {

    public static void main(String[] args) {
        Optional<String> optionalName = Optional.ofNullable(null);
        if (optionalName.isPresent()) {
            final String name = optionalName.get();
            if (name.equals("mundo")) {
                System.out.println("hello world");
            } else {
                System.out.println("ola " + optionalName);
            }
        }
    }

//    final class Pessoa {
//        public final void sayMyName() {
//            System.out.println("rafaelzembergui");
//        }
//    }
//
//    class Estudante extends Pessoa {
//        @Override
//        public void sayMyName() {
//            System.out.println("Thiago rafael");
//        }
//    }

}
