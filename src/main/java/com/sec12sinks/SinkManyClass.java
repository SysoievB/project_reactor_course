package com.sec12sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyClass {
    public static void main(String[] args) {
        Sinks.Many<Integer> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> flux = sink.asFlux();

        flux.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        // Emit multiple values
        sink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitComplete(Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
