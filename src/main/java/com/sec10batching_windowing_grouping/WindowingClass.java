package com.sec10batching_windowing_grouping;

import reactor.core.publisher.Flux;

public class WindowingClass {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .window(3)
                .flatMap(Flux::collectList) // Collect items in each window into a list
                .subscribe(System.out::println);
    }
}
