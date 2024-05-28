package com.sec3flux_creation;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;
import java.util.concurrent.CountDownLatch;

import java.time.Duration;

public class FluxInterval {
    public static void main(String[] args) throws InterruptedException {
        // Latch to keep the main thread running for 5 seconds
        CountDownLatch latch = new CountDownLatch(1);

        Flux.interval(Duration.ofSeconds(1))
                .map(i -> Faker.instance().address().city())
                .doOnTerminate(latch::countDown)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // Wait for 5 seconds before terminating
        latch.await(5, java.util.concurrent.TimeUnit.SECONDS);
    }
}
