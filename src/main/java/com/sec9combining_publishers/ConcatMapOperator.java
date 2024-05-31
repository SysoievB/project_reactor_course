package com.sec9combining_publishers;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConcatMapOperator {
    public static void main(String[] args) {
        Flux.just(1,2,3).concatMap(ConcatMapOperator::getUserById)
                .subscribe(System.out::println);

    }

    private static Mono<User> getUserById(int id) {
        return Mono.fromSupplier(() -> new User(id, Faker.instance().name().firstName()));
    }

    private record User(int id, String name){}
}
