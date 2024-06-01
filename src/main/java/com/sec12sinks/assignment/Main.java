package com.sec12sinks.assignment;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        var room = new SlackRoom("reactor");
        var sam = new SlackMember("sam");
        var jake = new SlackMember("jake");
        var mike = new SlackMember("mike");

        // add 2 members
        room.addMember(sam);
        room.addMember(jake);

        sam.says("Hi all..");
        Thread.sleep(Duration.ofSeconds(4));

        jake.says("Hey!");
        sam.says("I simply wanted to say hi..");
        Thread.sleep(Duration.ofSeconds(4));

        room.addMember(mike);
        mike.says("Hey guys..glad to be here...");
    }
}
