package net.dervism.paho.mqtt.app;

import net.dervism.paho.mqtt.client.Client;
import net.dervism.paho.mqtt.client.QualityOfService;
import net.dervism.paho.mqtt.client.SynchronousClient;
import net.dervism.paho.mqtt.publisher.Publisher;
import net.dervism.paho.mqtt.publisher.PublisherImpl;
import net.dervism.paho.mqtt.sensors.SensorReader;
import net.dervism.paho.mqtt.sensors.Simulator;
import net.dervism.paho.mqtt.subscriber.Subscriber;
import net.dervism.paho.mqtt.subscriber.SubscriberImpl;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by dervism on 15/11/14.
 */
public class Application {

    //public static final Logger log = Logger.getGlobal();
    public static final Logger log = Logger.getLogger("Main");

    public static void main(String[] args) throws IOException {
        log.setLevel(Level.ALL);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(Level.ALL);
        log.addHandler(handler);

        String topic        = "/raspberry/sensor/camera";
        String topic_sub        = "/raspberry/sensor/#";
        String broker       = "tcp://iot.eclipse.org:1883";
        String clientId     = "MqttRaspberrySampleDm";
        int qos             = QualityOfService.EXACTLY_ONCE;

        boolean subscriberMode = false;
        boolean publisherMode = true;

        if (subscriberMode) {
            Thread subscriberThread = new Thread(() -> {
                Client subscriberClient = new SynchronousClient(broker, clientId + "Sub", null, null);
                Subscriber subscriber = new SubscriberImpl(subscriberClient, qos);
                subscriber.subscribe(topic_sub, (message) -> {
                    log.info("(Subscriber) \t" +
                            "  Topic:\t" + topic +
                            "  Message:\t" + message.toString() +
                            "  QoS:\t" + message.getQos());
                });
            });
            subscriberThread.start();
        }

        if (publisherMode) {
            Thread publisherThread = new Thread(() -> {
                Client publisherClient = new SynchronousClient(broker, clientId+"Pub", null, null);
                Publisher publisher = new PublisherImpl(publisherClient, qos);

                SensorReader reader = new SensorReader(publisher, new Simulator(), topic, 10000);
                reader.run();

            });
            publisherThread.start();
        }

    }

}
