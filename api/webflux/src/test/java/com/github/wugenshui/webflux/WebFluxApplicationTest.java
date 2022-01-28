package com.github.wugenshui.webflux;

import com.github.wugenshui.webflux.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * @author : chenbo
 * @date : 2022-01-28
 */
@Slf4j
class WebFluxApplicationTest {

    @Test
    void apiTest() {
        StepVerifier.create(Flux.just("a", "b"))
                .expectNext("a")
                .expectNext("b")
                .verifyComplete();
    }

    @Test
    void fluxTest() {
        // 创建1-10的序列
        Flux.range(1, 2)
                // 永不超时
                .timeout(Flux.never(), v -> Flux.never())
                // 订阅消费
                .subscribe(System.out::println);

        // 指定序列中所有元素
        Flux.just(3, 4)
                // 订阅消费
                .subscribe(System.out::println);

        Integer[] numAray = new Integer[]{5, 6};
        Flux.fromArray(numAray)
                .subscribe(System.out::println);

        Flux.never()
                .subscribe(System.out::println);

        Flux.empty()
                .subscribe(System.out::println);

        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add("random:" + value);
            sink.next("random sink:" + value);
            if (list.size() == 3) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);

        Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                sink.next("create:" + i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

    @Test
    void monoTest() {
        User u = new User(1L, "zhangsan", "lisi");
        Mono.just(u).subscribe(System.out::println);

        Mono.fromCallable(() -> "9999").subscribe(System.out::println);

        Mono.delay(Duration.ofSeconds(2)).doOnNext(System.out::println).block();

        Mono.defer(() -> Mono.error(new RuntimeException())).subscribe();

        Mono.fromSupplier(() -> "Hello fromSupplier").subscribe(System.out::println);

        Mono.justOrEmpty(Optional.of("Hello justOrEmpty")).subscribe(System.out::println);

        Mono.create(sink -> sink.success("Hello create")).subscribe(System.out::println);
    }

    @Test
    void takeTest() {
        Flux.range(1, 1000).take(2).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(2).subscribe(System.out::println);
        Flux.range(1, 1000).takeWhile(i -> i < 2).subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i == 2).subscribe(System.out::println);
    }

    @Test
    void buffTest() {
        log.info("20个作为一次缓冲");
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        log.info("Flux.interval(Duration.ofMillis(100)).buffer(Duration.ofMillis(1001)).take(2)");
        Flux.interval(Duration.ofMillis(100)).buffer(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);
        log.info("bufferUntil");
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        log.info("bufferWhile");
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }

    @Test
    void windowTest() {
        Flux.range(1, 100).window(20).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(100)).window(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);
    }

    @Test
    void zipWithTest() {
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"))
                .subscribe(System.out::println);
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);
    }

    @Test
    void mergeTest() {
        Flux.merge(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100))
                        .take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);
        Flux.mergeSequential(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);

    }

    @Test
    void flatMapTest() {
        Flux.just(5, 10)
                .flatMap(x -> Flux.interval(Duration.ofMillis(x * 10), Duration.ofMillis(100)).take(x))
                .toStream()
                .forEach(System.out::println);
    }

}