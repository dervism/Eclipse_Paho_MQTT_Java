package net.dervism.paho.mqtt.subscriber;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.function.Consumer;

/**
 * Created by dervism on 13/11/14.
 */
public interface Subscriber {

    public void subscribe(String topicName, Consumer<MqttMessage> consumer);

}
