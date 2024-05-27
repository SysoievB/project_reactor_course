package com.sec2mono_creation;

import reactor.core.publisher.Mono;

public class SupplierVsCallable {

    public static void main(String[] args) {
        Mono.fromCallable(() -> divideByZero(10)).subscribe(System.out::println);
        Mono.fromSupplier(() -> {
            try {
                return divideByZero(10);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).subscribe(System.out::println);
    }

    private static int divideByZero(int value) throws Exception{
        return value/0;
    }
}
