package com.sec6hot_publisher;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Hot - 1 data producer for all the subscribers.
    share => publish().refCount(1)
    It needs 1 min subscriber to emit data.
    It stops when there is 0 subscriber.
    Re-subscription - It starts again where there is a new subscriber.
    To have min 2 subscribers, use publish().refCount(2);
 */
public class HotPublisherTheatreClass {

        public static void main(String[] args) throws InterruptedException {
            Flux<String> source = Flux.just("A", "B", "C", "D", "E")
                    .delayElements(Duration.ofMillis(500));

            ConnectableFlux<String> hotSource = source.publish();

            hotSource.connect(); // Start emitting items immediately

            // First subscriber
            hotSource.subscribe(item -> System.out.println("Subscriber 1: " + item));
            Thread.sleep(1200);

            // Second subscriber
            hotSource.subscribe(item -> System.out.println("Subscriber 2: " + item));
            Thread.sleep(2000);
        }
    }
