package com.sec5operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class NextOperator {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C");

        Mono<String> mono = flux.next();

        mono.subscribe(
                item -> System.out.println("Received: " + item),
                throwable -> System.err.println("Error: " + throwable),
                () -> System.out.println("Completed")
        );
    }
}
