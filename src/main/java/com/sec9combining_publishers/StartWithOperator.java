package com.sec9combining_publishers;

import reactor.core.publisher.Flux;

public class StartWithOperator {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("hello", "world");
        Flux<String> flux2 = Flux.just("start", "end");

        flux1.startWith(flux2).subscribe(System.out::println);
    }
}
