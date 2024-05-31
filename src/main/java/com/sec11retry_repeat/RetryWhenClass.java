package com.sec11retry_repeat;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
public class RetryWhenClass {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> flux = Flux.range(1, 4)
                .map(num -> {
                    if (num == 3) {
                        throw new RuntimeException("Error on 3");
                    }
                    return num;
                });

        flux.retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1))
                        .doBeforeRetry(retrySignal -> log.info("Retrying due to error: " + retrySignal.failure())))
                .subscribe(System.out::println, error -> System.err.println("Final error: " + error));

        Thread.sleep(Duration.ofSeconds(10)); // Allow time for retries
    }
}
