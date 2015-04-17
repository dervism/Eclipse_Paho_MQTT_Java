package net.dervism.paho.mqtt.app;

import java.util.stream.Stream;

/**
 * Created by dervism on 22/11/14.
 */
public class Settings {

    private boolean subscriber;
    private boolean publisher;
    private String topic;
    private String subscriberTopic;
    private String host;
    private String clientId;

    public Settings(String[] args) {
        subscriber = false;
        publisher = false;
        topic = "";
        subscriberTopic = "";
        host = "";
        clientId = "";

        Stream.of(args).forEach(s -> {
            if (s.equalsIgnoreCase("-s")) subscriber = true;
            if (s.equalsIgnoreCase("-p")) subscriber = true;
            if (s.startsWith("-host:")) {
                host = split(s);
            }
            if (s.startsWith("-topic:")) {
                topic = split(s);
            }
            if (s.startsWith("-subtopic:")) {
                subscriberTopic = split(s);
            }
            if (s.startsWith("-clientid:")) {
                clientId = split(s);
            }
        });

    }

    private String split(String s) {
        String[] split = s.split(":");
        if (split.length != 2) throw new IllegalArgumentException("Invalid number of arguments for " + s);
        return split[1];
    }


}
