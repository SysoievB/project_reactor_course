package com.sec9combining_publishers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FlatMapManyOperator {
    public static void main(String[] args) {
        Mono.just(List.of(1, 2, 3))
                .flatMapMany(Flux::fromIterable)
                .map(num -> "val is - " + num)
                .subscribe(System.out::println);

    }
}
