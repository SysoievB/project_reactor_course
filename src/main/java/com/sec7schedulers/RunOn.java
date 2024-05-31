package com.sec7schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RunOn {

    public static void main(String[] args) {
        Flux.range(1, 2)
                .map(i -> {
                    System.out.println(String.format("First map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                    return i;
                })
                .parallel()
                .runOn(Schedulers.parallel())//will use quantity threads the same as CPU has cores
                .map(i -> {
                    System.out.println(String.format("Second map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                    return i;
                })
                .sequential()
                .blockLast();
    }
}
