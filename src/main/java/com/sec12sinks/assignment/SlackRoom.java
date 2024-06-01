package com.sec12sinks.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class SlackRoom {

    private final String name;
    private final Sinks.Many<SlackMessage> sink;
    private final Flux<SlackMessage> flux;

    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    public void addMember(SlackMember slackMember) {
        log.info("{} joined the room {}", slackMember.getName(), this.name);
        this.subscribeToRoomMessages(slackMember);
        slackMember.setMessageConsumer(msg -> this.postMessage(slackMember.getName(), msg));
    }

    private void subscribeToRoomMessages(SlackMember slackMember) {
        this.flux
                .filter(sm -> !sm.sender().equals(slackMember.getName()))
                .map(sm -> sm.formatForDelivery(slackMember.getName()))
                .subscribe(slackMember::receives);
    }

    private void postMessage(String sender, String message) {
        this.sink.tryEmitNext(new SlackMessage(sender, message));
    }
}