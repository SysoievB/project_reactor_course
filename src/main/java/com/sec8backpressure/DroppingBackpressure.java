package com.sec8backpressure;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class DroppingBackpressure {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> fastProducer = Flux.range(1, 100)
                .doOnNext(i -> System.out.println("Producing item: " + i))
                .onBackpressureDrop(i -> System.out.println("Dropping item: " + i)) // Drop items when the buffer is full
                .publishOn(Schedulers.boundedElastic());

        fastProducer.subscribe(
                item -> {
                    try {
                        Thread.sleep(100); // Simulate slow consumer
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
