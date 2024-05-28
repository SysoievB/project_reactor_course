package com.sec3flux_creation;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class FluxCreation {

    public static void main(String[] args) {
        Flux<String> justFlux = Flux.just("Hello", "from", "flux");
        justFlux.subscribe(System.out::println);

        List<String> list = List.of("Hello", "from", "list");
        Flux<String> fluxFromList = Flux.fromIterable(list);
        fluxFromList.subscribe(System.out::println);

        String[] arr = {"Hello", "from", "array"};
        Flux<String> fluxFromArray = Flux.fromArray(arr);
        fluxFromArray.subscribe(System.out::println);

        Stream<String> stream = Stream.of("Hello", "from", "stream");
        Flux<String> fluxFromStream = Flux.fromStream(stream);
        fluxFromStream.subscribe(System.out::println);

        Flux<Integer> fromRange = Flux.range(1, 4).log();
        fromRange.subscribe(System.out::println);
    }
}
