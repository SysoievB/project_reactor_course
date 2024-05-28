package com.sec4emitting_items;

import com.github.javafaker.Faker;
import lombok.val;
import reactor.core.publisher.Flux;

public class FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            synchronousSink.next(1);//allows only one value to process
            // synchronousSink.next(2);//error will be thrown -> java.lang.IllegalStateException: More than one call to onNext
            synchronousSink.complete();
        }).subscribe(System.out::println);

        Flux.generate(synchronousSink -> {
                    val country = Faker.instance().address().country();
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("canada")) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(System.out::println);

        Flux.<String>generate(synchronousSink -> {
                    synchronousSink.next(Faker.instance().address().country());
                })
                .takeUntil(country -> country.equalsIgnoreCase("canada"))
                .subscribe(System.out::println);
    }
}
