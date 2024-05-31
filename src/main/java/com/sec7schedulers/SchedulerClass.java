package com.sec7schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerClass {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 10)
                .map(i -> {
                    System.out.println("Mapping on thread: " + Thread.currentThread().getName());
                    return i * 2;
                })
                .subscribeOn(Schedulers.parallel())  // Subscribe and execute upstream operations on parallel scheduler
               // .publishOn(Schedulers.single())  // Execute downstream operations on single-threaded scheduler
                .subscribe(i -> System.out.println("Received " + i + " on thread: " + Thread.currentThread().getName()));

        Thread.sleep(1000);  // Wait for all tasks to complete
    }
}
