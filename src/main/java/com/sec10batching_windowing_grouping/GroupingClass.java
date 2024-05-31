package com.sec10batching_windowing_grouping;

import reactor.core.publisher.Flux;

public class GroupingClass {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .groupBy(num -> num % 2 == 0 ? "Even" : "Odd") // Group elements based on whether they are even or odd
                .collectList()
                .subscribe(list -> {
                    list.forEach(groupedFlux -> {
                        String key = groupedFlux.key();
                        groupedFlux.subscribe(element -> {
                            System.out.println("Group: " + key + ", Element: " + element);
                        });
                    });
                });
    }
}
