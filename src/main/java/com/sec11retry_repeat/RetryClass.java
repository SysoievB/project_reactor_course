package com.sec11retry_repeat;

import reactor.core.publisher.Flux;

public class RetryClass {
    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.range(1, 4)
                .map(num -> {
                    if (num == 3) {
                        throw new RuntimeException("Error on 3");
                    }
                    return num;
                })
                .retry(2); // Retry up to 2 times on error

        numbers.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error)
        );
    }
}
