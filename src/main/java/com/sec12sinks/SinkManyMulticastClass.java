package com.sec12sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyMulticastClass {
    public static void main(String[] args) {
        Sinks.Many<Integer> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux = sink.asFlux();

        flux.subscribe(value -> System.out.println("Subscriber 1 received: " + value));
        flux.subscribe(value -> System.out.println("Subscriber 2 received: " + value));

        // Emit multiple values
        sink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitComplete(Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
