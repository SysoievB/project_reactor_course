package com.sec11retry_repeat;

import reactor.core.publisher.Flux;

public class RepeatClass {
    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.just(1, 2, 3)
                .repeat(2); // Repeat the sequence twice

        numbers.subscribe(System.out::println);
    }
}
