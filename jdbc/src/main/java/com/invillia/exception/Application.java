package com.invillia.exception;

public class Application {

    public static void main(String[] args) {
        try (
                final Random random = new Random();
        ) {
            random.greet("mundo");
            random.greet(null);
            random.greet("mundo");
        } catch (Exception xablau) {
            System.out.println("erro");
        }

        // RuntimeException -> unchecked
        // Exception -> checked
    }

    public static class IncubadoraException extends RuntimeException {

    }


    public static class Random implements AutoCloseable {

        public void greet(String name) {
            if (name.equals("mundo")) {
                System.out.println("hello world");
            } else {
                System.out.println("ola " + name);
            }
        }

        @Override
        public void close() {
            System.out.println("fechado");
        }

    }
}
