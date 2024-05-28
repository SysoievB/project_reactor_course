package com.sec4emitting_items;

import reactor.core.publisher.Flux;

public class FluxTakeFirstElement {
    public static void main(String[] args) {
        Flux.just("hello", "world").take(1).subscribe(System.out::println);
        Flux.just("hello", "world").elementAt(0).subscribe(System.out::println);
        Flux.just("hello", "world").toStream().limit(1).forEach(System.out::println);
    }
}
