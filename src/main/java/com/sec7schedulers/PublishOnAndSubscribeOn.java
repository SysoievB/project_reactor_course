package com.sec7schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class PublishOnAndSubscribeOn {
    public static void main(String[] args) {
        Scheduler schedulerA = Schedulers.newParallel("scheduler-a", 4);
        Scheduler schedulerB = Schedulers.newParallel("scheduler-b", 4);

        Flux.range(1, 2)
                .map(i -> {
                    System.out.println(String.format("First map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                    return i;
                })
                .subscribeOn(schedulerA)
                .map(i -> {
                    System.out.println(String.format("Second map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                    return i;
                })
                .publishOn(schedulerB)
                .map(i -> {
                    System.out.println(String.format("Third map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                    return i;
                })
                .blockLast();
    }
}
//First map - (1), Thread: scheduler-a-2
//Second map - (1), Thread: scheduler-a-2
//First map - (2), Thread: scheduler-a-2
//Third map - (1), Thread: scheduler-b-1
//Second map - (2), Thread: scheduler-a-2
//Third map - (2), Thread: scheduler-b-1