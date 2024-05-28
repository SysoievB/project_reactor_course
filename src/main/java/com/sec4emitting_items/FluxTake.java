package com.sec4emitting_items;

import reactor.core.publisher.Flux;

public class FluxTake {
    public static void main(String[] args) {
        Flux.range(1, 5).take(2).subscribe(System.out::println);//1 2
        Flux.range(1, 5).takeLast(2).subscribe(System.out::println);//4 5
        Flux.range(1, 5).takeWhile(num -> num < 4).subscribe(System.out::println);//1 2 3
        Flux.range(1, 5).last().subscribe(System.out::println);//5
        //it will return values until the condition will be met + one more value
        Flux.range(1, 5).takeUntil(num -> num > 3).subscribe(System.out::println);//1 2 3 4
    }
}
