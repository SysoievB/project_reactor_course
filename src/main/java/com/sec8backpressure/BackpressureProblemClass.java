package com.sec8backpressure;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureProblemClass {
    public static void main(String[] args) {
        Flux<Integer> fastProducer = Flux.range(1, 10)
                .doOnNext(i -> System.out.println("Producing item: " + i))
                .publishOn(Schedulers.boundedElastic()); // Emitting items on a bounded elastic scheduler

        fastProducer.subscribe(
                item -> {
                    // Simulate slow consumer
                    try {
                        Thread.sleep(1000); // Slow down the consumer
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Consuming item: " + item);
                },
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        // Sleep to allow time for async processing (not needed in a real reactive application with a proper termination mechanism)
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
