package com.sec10batching_windowing_grouping;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class BatchingClass {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 10)
                .buffer(3)
                .subscribe(System.out::println);
        //[1, 2, 3]
        //[4, 5, 6]
        //[7, 8, 9]
        //[10]
        Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500))
                .buffer(3)
                .subscribe(System.out::println);
        //[1, 2, 3]
        //[4, 5, 6]
        //[7, 8, 9]
        //[10]
        Flux.interval(Duration.ofMillis(100))
                .take(10) // Take 10 items to prevent infinite stream
                .buffer(Duration.ofMillis(300)) // Collect items every 300ms
                .subscribe(System.out::println);
        //[0, 1, 2]
        //[3, 4, 5]
        //[6, 7, 8]
        //[9]
        Thread.sleep(Duration.ofSeconds(10));
    }
}
