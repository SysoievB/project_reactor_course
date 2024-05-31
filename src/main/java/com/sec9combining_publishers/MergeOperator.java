package com.sec9combining_publishers;

import reactor.core.publisher.Flux;

public class MergeOperator {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("hello", "world");
        Flux<String> flux2 = Flux.just("start", "end");

        flux1.mergeWith(flux2).subscribe(System.out::println);
        Flux.merge(flux1, flux2).subscribe(System.out::println);
    }
}
