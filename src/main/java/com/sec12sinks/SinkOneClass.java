package com.sec12sinks;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOneClass {
    public static void main(String[] args) {
        Sinks.One<String> sink = Sinks.one();
        Mono<String> mono = sink.asMono();

        mono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        // Emit a value
        sink.emitValue("Hello, Reactor!", Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
