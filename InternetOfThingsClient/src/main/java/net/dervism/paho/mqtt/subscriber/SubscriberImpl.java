package net.dervism.paho.mqtt.subscriber;

import net.dervism.paho.mqtt.client.Client;
import net.dervism.paho.mqtt.client.QualityOfService;
import net.dervism.paho.mqtt.client.SynchronousClient;
import org.eclipse.paho.client.mqttv3.*;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * MQTT Subscriber implementation.
 * Based on the Eclipse Paho default implementation found here:
 * http://git.eclipse.org/c/paho/org.eclipse.paho.mqtt.java.git/tree/
 *
 * Created by dervism on 13/11/14.
 */

public class SubscriberImpl implements Subscriber, MqttCallback {

    private Client client;
    private Consumer<MqttMessage> consumer;
    private int qos;
    private final static Logger log = Logger.getLogger(SubscriberImpl.class.getName());

    public SubscriberImpl(Client client, int qos) {
        this.client = client;
        this.qos = qos;
        client.setCallBackHandler(this);
    }

    /**
     * The QoS specified is the maximum level that messages will be sent to the client at.
     * For instance if QoS 1 is specified, any messages originally published at QoS 2 will
     * be downgraded to 1 when delivering to the client but messages published at 1 and 0
     * will be received at the same level they were published at.
     *
     * @param topicName The topic to listen to.
     * @param consumer A functional interface used to control message handling
     */
    @Override
    public void subscribe(String topicName, Consumer<MqttMessage> consumer) {
        if (!client.isConnected()) {
            try {
                client.connect();
            } catch (MqttException e) {
                log.severe(e.getMessage());
            }
        }
        this.consumer = consumer;
        try {
            client.subscribe(topicName, qos);
        } catch (MqttException e) {
            log.severe(e.getMessage());
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.warning("Subscriber disconnected from client " + client.getBrokerId());
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        consumer.accept(mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
