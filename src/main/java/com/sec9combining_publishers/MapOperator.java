package com.sec9combining_publishers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MapOperator {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just(1, 2, 3)
                .map(num -> STR."\{num} number was mapped");

        flux1.subscribe(System.out::println);

        correctMapping();
        wrongMapping();
    }

    private static void correctMapping() {
        Mono<String> mono = Mono.just("Hello")
                .map(value -> {
                    System.out.println("Outer map: " + value);
                    return Mono.just(value + " World")
                            .map(innerValue -> {
                                System.out.println("Inner map: " + innerValue);
                                return innerValue + "!";
                            });
                })
                .flatMap(innerMono -> innerMono);

        mono.subscribe(result -> System.out.println("Result: " + result));
    }

    private static void wrongMapping() {
        Mono<Mono<String>> nestedMono = Mono.just("Hello")
                .map(value -> {
                    System.out.println("Outer map: " + value);
                    return Mono.just(value + " World")
                            .map(innerValue -> {
                                System.out.println("Inner map: " + innerValue);
                                return innerValue + "!";
                            });
                });

        nestedMono.subscribe(result -> {
            // This will print Mono.toString() instead of the actual result
            System.out.println("Result: " + result);
        });
    }
}
