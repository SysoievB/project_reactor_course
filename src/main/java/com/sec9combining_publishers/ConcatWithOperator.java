package com.sec9combining_publishers;

import reactor.core.publisher.Flux;

public class ConcatWithOperator {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("hello", "world");
        Flux<String> flux2 = Flux.just("start", "end");

        flux1.concatWith(flux2).subscribe(System.out::println);

        flux1.concatWithValues("val1", "val2").subscribe(System.out::println);
    }
}
