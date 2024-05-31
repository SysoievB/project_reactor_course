package com.sec9combining_publishers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ThenOperator {
    public static void main(String[] args) {
        Flux.just(1, 2, 3).then(Mono.just(5)).subscribe(System.out::println);//5

        Flux.just(1, 2, 3).thenMany(Flux.just(5, 6)).subscribe(System.out::println);//5 6

        //nothing will be printed
        Flux.just(1, 2, 3).thenEmpty(Flux.empty()).subscribe(System.out::println);

        Mono<String> process = Mono.just("Initial value")
                .doOnNext(value -> System.out.println("First step: " + value))
                .thenReturn("Final value");

        process.subscribe(
                result -> System.out.println("Result: " + result),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
