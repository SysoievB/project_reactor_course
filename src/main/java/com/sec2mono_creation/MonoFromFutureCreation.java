package com.sec2mono_creation;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class MonoFromFutureCreation {
    private static final Logger log = LoggerFactory.getLogger(MonoFromFutureCreation.class);

    public static void main(String[] args) {
        Mono.fromFuture(getName()).subscribe(System.out::println);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name...");
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Faker.instance().name().firstName();
        });
    }
}
