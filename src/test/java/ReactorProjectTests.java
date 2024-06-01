import lombok.val;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.publisher.TestPublisher;
import reactor.util.context.Context;

import java.time.Duration;
import java.util.Objects;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReactorProjectTests {
    @Test
    void expectNextCount() {
        StepVerifier.create(Flux.range(1, 100))
                .expectNextCount(100)
                .verifyComplete();
    }

    @Test
    void expectNext() {
        StepVerifier.create(Mono.just(3))
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    void scenarioName() {
        StepVerifier.create(
                        Flux.just(3, 4, 5),
                        //will be printed when test fails
                        StepVerifierOptions.create().scenarioName("Flux with values")
                )
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }

    @Test
    void expectNextFew() {
        StepVerifier.create(Flux.just(3, 4, 5))
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }

    @Test
    void expectNextFewInARow() {
        StepVerifier.create(Flux.just(3, 4, 5))
                .expectNext(3, 4, 5)
                .verifyComplete();
    }

    @Test
    void expectError() {
        StepVerifier.create(Mono.error(RuntimeException::new))
                .verifyError(RuntimeException.class);
    }

    @Test
    void expectErrorMessage() {
        StepVerifier.create(Mono.error(new RuntimeException("Error")))
                .consumeErrorWith(ex -> {
                    assertEquals(RuntimeException.class, ex.getClass());
                    assertEquals(ex.getMessage(), "Error");
                })
                .verify();
    }

    @Test
    void expectEmpty() {
        StepVerifier.create(Mono.empty())
                .verifyComplete();
    }

    @Test
    void thenConsumeWhile() {
        StepVerifier.create(Flux.just(1, 2, 3))
                .assertNext(v -> assertEquals(1, v))
                .thenConsumeWhile(Objects::nonNull)
                .expectComplete()
                .verify();
    }

    @Test
    void virtualTimeTest1() {
        StepVerifier.withVirtualTime(this::getFlux)
                .thenAwait(Duration.ofSeconds(3))
                .expectNext(1, 2, 3, 4, 5)
                .verifyComplete();
    }

    @Test
    void virtualTimeTest2() {
        StepVerifier.withVirtualTime(this::getFlux)
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(100))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1, 2)
                .thenAwait(Duration.ofSeconds(3))
                .expectNext(3, 4, 5)
                .verifyComplete();
    }

    @Test
    void checkContext() {
        StepVerifier.create(
                        getContextMessage(),
                        StepVerifierOptions.create().withInitialContext(Context.of("user", "sam")))
                .expectNext("Welcome sam")
                .expectComplete()
                .verify();
    }

    @Test
    void unauthenticatedTest() {
        StepVerifier.create(
                        getContextMessage(),
                        StepVerifierOptions.create().withInitialContext(Context.empty()))
                .expectErrorMessage("unauthenticated")
                .verify();
    }

    @Test
    void publisherTest1() {
        val publisher = TestPublisher.<String>create();
        val flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("hi", "hello"))
                .expectNext("HI:2")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();
    }

    @Test
    void publisherTest2() {
        val publisher = TestPublisher.<String>create();
        val flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))
                .expectComplete()
                .verify();
    }

    @Test
    void timeoutTest() {
        StepVerifier.create(getFlux())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify(Duration.ofMillis(2600));//100 millis above expected
    }

    private Mono<String> getContextMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    private Flux<Integer> getFlux() {
        return Flux.range(1, 5).delayElements(Duration.ofMillis(500));
    }

    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> STR."\{s}:\{s.length()}");
    }
}
