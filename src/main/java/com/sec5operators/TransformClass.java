package com.sec5operators;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class TransformClass {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C")
                .transform(f -> f
                        .delayElements(Duration.ofMillis(100))
                        .timeout(Duration.ofMillis(200))
                        .onErrorResume(e -> Flux.just("Timeout Fallback"))
                );

        //or the same without transform()
        Flux<String> flux1 = Flux.just("A", "B", "C")
                        .delayElements(Duration.ofMillis(100))
                        .timeout(Duration.ofMillis(200))
                        .onErrorResume(e -> Flux.just("Timeout Fallback"));

        flux1.subscribe(
                System.out::println,
                throwable -> System.err.println("Error: " + throwable),
                () -> System.out.println("Completed")
        );

        try {
            Thread.sleep(1000); // To keep the JVM alive for observing the output
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

