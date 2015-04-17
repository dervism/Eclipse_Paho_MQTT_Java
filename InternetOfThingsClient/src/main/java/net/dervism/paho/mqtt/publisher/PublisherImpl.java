package net.dervism.paho.mqtt.publisher;

import net.dervism.paho.mqtt.client.Client;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Logger;

/**
 * MQTT Publisher implementation.
 * Based on the Eclipse Paho default implementation found here:
 * http://git.eclipse.org/c/paho/org.eclipse.paho.mqtt.java.git/tree/
 *
 * Created by dervism on 13/11/14.
 */

public class PublisherImpl implements Publisher, MqttCallback {

    private Client client;
    private Supplier<byte[]> supplier;
    private int qos;
    private final static Logger log = Logger.getLogger(PublisherImpl.class.getName());

    public PublisherImpl(Client client, int qos) {
        this.client = client;
        this.qos = qos;
        client.setCallBackHandler(this);
    }

    @Override
    public void publish(String topicName, Supplier<byte[]> supplier) {
        byte[] payload = new byte[0];
        try {
            payload = supplier.get();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }

        if (!client.isConnected()) {
            try {
                client.connect();
            } catch (MqttException e) {
                log.severe(e.getMessage());
            }
        }

        try {
            client.publish(topicName, qos, payload);
        } catch (MqttException e) {
            log.severe(e.getMessage());
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.warning("Publisher disconnected from client " + client.getBrokerId());
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        log.info("(Publisher) \t" +
                "  Topic:\t" + topic +
                "  Message:\t" + new String(mqttMessage.getPayload()) +
                "  QoS:\t" + mqttMessage.getQos());
    }

    /**
     * For a QoS of 0, when the message has been written to the network, for a QoS of 1, when the message
     * publication has been acknowledged and for a QoS of 2 when the message publication has not only been
     * acknowledged but confirmed to have been the only copy of the message delivered.
     *
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("Message delivered.");
    }
}
