package com.sec5operators;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class ElapsedClass {
    public static void main(String[] args) {
        Flux<Tuple2<Long, String>> flux = Flux.just("Item1", "Item2", "Item3")
                .delayElements(Duration.ofSeconds(1))  // Simulate some delay
                .elapsed();  // Measure elapsed time

        flux.subscribe(tuple -> {
            Long timeElapsed = tuple.getT1();
            String item = tuple.getT2();
            System.out.println("Time elapsed: " + timeElapsed + " ms, Item: " + item);
        });

        // Keep the application running to observe the output
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
