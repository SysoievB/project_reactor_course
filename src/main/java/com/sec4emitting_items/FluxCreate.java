package com.sec4emitting_items;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                    String country;
                    do {
                        country = Faker.instance().address().country();
                        fluxSink.next(country);
                    } while (!country.equalsIgnoreCase("canada"));
                    fluxSink.complete();
                })
                .subscribe(System.out::println);


        Flux.create(fluxSink -> {
                    fluxSink.next(10);
                    fluxSink.next(20);
                    fluxSink.complete();
                })
                .subscribe(System.out::println);
    }
}
