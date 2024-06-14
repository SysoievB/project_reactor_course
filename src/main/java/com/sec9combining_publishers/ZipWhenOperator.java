package com.sec9combining_publishers;

import lombok.AllArgsConstructor;
import lombok.ToString;
import reactor.core.publisher.Mono;

public class ZipWhenOperator {
    public static void main(String[] args) {
        Mono<String> userIds = Mono.just("user1");

        // Using zipWhen to fetch user details and combine with user ID
        userIds.zipWhen(userId -> fetchUserDetails(userId))
                .map(tuple -> new UserWithDetails(tuple.getT1(), tuple.getT2()))
                .subscribe(System.out::println);
    }

    // Simulated method to fetch user details
    private static Mono<UserDetails> fetchUserDetails(String userId) {
        // In a real-world scenario, this could be a call to a database or an external service
        return Mono.just(new UserDetails(userId, "Name for " + userId));
    }

    @ToString
    @AllArgsConstructor
    static class UserWithDetails {
        private final String userId;
        private final UserDetails userDetails;
    }

    @ToString
    @AllArgsConstructor
    static class UserDetails {
        private final String userId;
        private final String name;
    }
}
