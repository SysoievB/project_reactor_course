package com.sec3flux_creation;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

public class FluxLog {
    public static void main(String[] args) {
        Flux.range(1, 3)
                .log("numbers")
                .map(i -> Faker.instance().name().firstName())
                .log("first names")
                .subscribe(System.out::println);
    }
}
