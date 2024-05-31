package com.sec7schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class PublishOnClass {
    public static void main(String[] args) throws InterruptedException {
        Scheduler schedulerA = Schedulers.newParallel("scheduler-a", 4);

        Flux.range(1, 2)
                .map(i -> {
                    System.out.println(String.format("First map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                    return i;
                })
                .publishOn(schedulerA)
                .map(i -> {
                    System.out.println(String.format("Second map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                    return i;
                })
                .blockLast();
    }
}
//First map - (1), Thread: main
//First map - (2), Thread: main
//Second map - (1), Thread: scheduler-a-1
//Second map - (2), Thread: scheduler-a-1