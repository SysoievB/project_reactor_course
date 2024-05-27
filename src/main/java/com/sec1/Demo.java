package com.sec1;

import com.sec1.publisher.PublisherImpl;
import com.sec1.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        demo2();
    }

    private static void demo1() {
        // 1. publisher does not produce data unless subscriber requests for it.
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        // 2. publisher will produce only <= subscriber requested items. publisher can also produce 0 items!
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }

    private static void demo3() throws InterruptedException {
        // 3. subscriber can cancel the subscription. producer should stop at that moment as subscriber is no longer interested in consuming the data

        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }

    private static void demo4() throws InterruptedException {
        //4. producer can send the error signal

        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}
