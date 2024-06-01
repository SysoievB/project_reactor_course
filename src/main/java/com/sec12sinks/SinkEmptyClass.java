package com.sec12sinks;

import reactor.core.publisher.Sinks;

public class SinkEmptyClass {
    public static void main(String[] args) {
        Sinks.Empty<String> sinkEmpty = Sinks.empty();
        sinkEmpty.asMono().subscribe(System.out::println);
    }
}
