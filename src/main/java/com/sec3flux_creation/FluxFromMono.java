package com.sec3flux_creation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromMono {
    public static void main(String[] args) {
        Flux.from(Mono.just("Hello")).subscribe(System.out::println);
        //gets only first element
        Mono.from(Flux.just("hello", "world"))
                .subscribe(System.out::println);//hello
    }
}
