package net.dervism.paho.mqtt.publisher;

import net.dervism.paho.mqtt.client.Client;
import net.dervism.paho.mqtt.client.QualityOfService;
import net.dervism.paho.mqtt.client.SynchronousClient;
import net.dervism.paho.mqtt.subscriber.Subscriber;
import net.dervism.paho.mqtt.subscriber.SubscriberImpl;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.junit.Assert.*;

public class PublisherImplTest {
    public static final Logger log = Logger.getGlobal();

    @Test
    public void testPublish() {

    }

}