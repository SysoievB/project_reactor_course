package com.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class ProcessingExample {

    private static final Logger logger = LoggerFactory.getLogger(ProcessingExample.class);

    public static void main(String[] args) {
        List<String> elements = IntStream.range(1, 10_000)
                        .mapToObj(num -> "Item " + num)
                        .toList();
                //List.of("Item1", "Item2", "Item3", "Item4", "Item5");

        Mono<List<String>> monoList = Mono.just(elements);

        monoList.flatMapMany(Flux::fromIterable)
                .index()
                .flatMap(tuple -> processElement(tuple.getT2()) // Process each element
                        .then(Mono.just(tuple)))
                .bufferTimeout(elements.size(), Duration.ofSeconds(1)) // Buffer emissions and emit every 3 seconds
                .doOnNext(buffer -> {
                    // Calculate the percentage of processed elements
                    int percentage = ((int)((double) buffer.getLast().getT1() + 1) * 100 / elements.size());
                    logger.info("Processed {}% of elements", percentage);
                })
                .flatMap(Flux::fromIterable) // Flatten the buffered lists back into individual elements
                .collectList() // Collect the processed elements back into a list
                .block(); // Block to wait for the completion of processing
    }

    private static Mono<Void> processElement(String element) {
        return Mono.delay(Duration.ofMillis(500)) // Simulate processing delay
                .then(); // Complete the processing
    }
}

