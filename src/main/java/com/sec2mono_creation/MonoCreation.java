package com.sec2mono_creation;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class MonoCreation {
    public static void main(String[] args) {
        Mono<String> monoJust = Mono.just("me mono");
        Mono<Optional<String>> monoJustOrEmpty = Mono.just(Optional.of("me mono"));
        Mono<String> monoEmpty = Mono.empty();
        Mono<String> monoError = Mono.error(RuntimeException::new);
        Mono<Void> fromRunnable = Mono.fromRunnable(MonoCreation::hello);
        Mono<ZonedDateTime> fromCallable = Mono.fromCallable(ZonedDateTime::now);
        Mono<LocalDate> fromSupplier = Mono.fromSupplier(MonoCreation::supply);
        Mono<String> fromFuture = Mono.fromFuture(() -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate a long-running task
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello from CompletableFuture!";
        }));

        monoJust.subscribe(System.out::println);//me mono
        monoEmpty.subscribe(System.out::println);//
        monoJustOrEmpty.subscribe(System.out::println);//Optional[me mono]
        monoError.subscribe(System.out::println);//java.lang.RuntimeException
        fromRunnable.subscribe();//hello
        fromCallable.subscribe(System.out::println);//2024-05-27T07:32:27.260562400+02:00[Europe/Warsaw]
        fromSupplier.subscribe(System.out::println);//2024-05-27
        fromFuture.subscribe(System.out::println);

        subscribeTest(monoJust);//me mono
//====== Execution Completed ======

        monoJust.subscribe(System.out::println,//me mono
                System.err::println,
                () -> System.out.println("Completed"),//Completed
                subscription -> subscription.request(1));

        Mono.fromCallable(MonoCreation::supply).subscribe(System.out::println);
    }

    private static void hello() {
        System.out.println("hello");
    }

    private static LocalDate supply(){
        try {
        }catch (Exception e){}
        return LocalDate.now();
    }

    private static void subscribeTest(Mono<String> mono) {
        mono.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("====== Execution Completed ======");
            }
        });
    }
}
