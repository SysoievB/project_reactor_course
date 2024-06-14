package com.sec2mono_creation;

import reactor.core.publisher.Mono;

public class MonoWhen {
    public static void main(String[] args) {
        Mono<String> mono1 = Mono.just("hello");
        Mono<String> mono2 = Mono.just(" world");

        Mono<Void> monoWhen = Mono.when(mono1, mono2);

        monoWhen.subscribe(
                unused -> {},
                throwable -> System.err.println("An error occurred: " + throwable),
                () -> System.out.println("All side effects have completed.")
        );
    }
}
