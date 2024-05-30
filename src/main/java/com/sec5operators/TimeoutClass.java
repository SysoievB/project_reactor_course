package com.sec5operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TimeoutClass {
    public static void main(String[] args) {
       Flux.just("A", "B", "C")
                .delayElements(Duration.ofMillis(100))
                .timeout(Duration.ofMillis(50))
               .subscribe(System.out::println);

        Flux.just("X", "Y", "Z")
                .delaySequence(Duration.ofMillis(100))
                .timeout(Duration.ofMillis(200))
                .subscribe(System.out::println);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
