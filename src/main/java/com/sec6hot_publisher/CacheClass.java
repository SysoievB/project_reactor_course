package com.sec6hot_publisher;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class CacheClass {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> source = Flux.just("A", "B", "C", "D", "E")
                .delayElements(Duration.ofMillis(500))
                .cache();  // Cache all items indefinitely

        // First subscriber
        source.subscribe(item -> System.out.println("Subscriber 1: " + item));
        Thread.sleep(1200);

        // Second subscriber
        source.subscribe(item -> System.out.println("Subscriber 2: " + item));
        Thread.sleep(2000);
    }
}
