package com.sec9combining_publishers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CollectListOperator {
    public static void main(String[] args) {
        Flux.just(1,2,3)
                .collectList()
                .subscribe(System.out::println);
    }
}
