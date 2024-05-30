package com.sec5operators;

import reactor.core.publisher.Flux;

public class ErrorHandling {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .map(num -> {
                    if (num == 3){
                        num = num/0;
                    }
                    return num;
                })
                //.onErrorReturn(-1)
                .onErrorReturn(ArithmeticException.class, -1)
               // .onErrorReturn(RuntimeException.class, -2)
                .subscribe(System.out::println);

        Flux.range(1, 5)
                .map(num -> {
                    if (num == 3){
                        num = num/0;
                    }
                    return num;
                })
                .onErrorResume(error -> Flux.just(-1))
                .subscribe(System.out::println);

        Flux.range(1, 5)
                .map(num -> {
                    if (num == 3){
                        num = num/0;
                    }
                    return num;
                })
                .onErrorComplete()
                .subscribe(System.out::println);

        Flux.range(1, 5)
                .map(num -> {
                    if (num == 3){
                        num = num/0;
                    }
                    return num;
                })
                .onErrorContinue((err, _) -> err.getMessage())
                .subscribe(System.out::println);
    }
}
