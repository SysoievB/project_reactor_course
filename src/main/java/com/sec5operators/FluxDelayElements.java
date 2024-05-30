package com.sec5operators;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxDelayElements {
    public static void main(String[] args) {
        Flux.just("hello", "wor")
                .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);

        try {
            Thread.sleep(Duration.ofSeconds(5));//main thread stays live for 5 seconds at least, otherwise we need to block flux
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
