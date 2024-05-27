package com.sec2mono_creation;

import reactor.core.publisher.Mono;

public class MonoDeferCreation {

    public static void main(String[] args) {
        Mono<String> mono = Mono.just(generateValue());//Generating value - prints without subscription

        Mono<String> deferredMono = Mono.defer(() -> {
            System.out.println("Generating value");
            return Mono.just(generateValue());
        });//will print only after subscription that is why it called cold

        deferredMono.subscribe();
        /*Generating value
          Generating value*/
        deferredMono.subscribe(System.out::println);
        /*Generating value
          Generating value
          Hello, World!*/

        // Create a Mono that either emits a value or an error based on some condition
        Mono<String> valueOrError = Mono.defer(() -> {
            if (shouldEmitValue()) {
                return Mono.just("Hello, from defer!");
            } else {
                return Mono.error(new RuntimeException("Something went wrong!"));
            }
        });

        // Subscribe to the Mono and print the value or the error
        valueOrError.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage())
        );
    }

    public static String generateValue() {
        System.out.println("Generating value");
        return "Hello, World!";
    }

    public static boolean shouldEmitValue() {
        // Here you can implement your logic to determine whether to emit a value or an error
        return Math.random() > 0.5;
    }
}
