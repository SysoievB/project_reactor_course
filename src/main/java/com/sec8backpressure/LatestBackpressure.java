package com.sec8backpressure;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class LatestBackpressure {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> fastProducer = Flux.range(1, 1000)
                .doOnNext(i -> System.out.println("Producing item: " + i))
                .onBackpressureLatest() // Keep only the latest item, dropping the rest
                .publishOn(Schedulers.boundedElastic());

        fastProducer.subscribe(
                item -> {
                    try {
                        Thread.sleep(10); // Simulate slow consumer
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Consuming item: " + item);
                },
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        Thread.sleep(10000);
    }
}
