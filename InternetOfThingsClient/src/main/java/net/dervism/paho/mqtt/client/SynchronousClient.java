package net.dervism.paho.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dervism on 15/11/14.
 */

public class SynchronousClient implements Client {

    private String brokerUrl;
    private String clientId;
    private MqttClient client;
    private MqttConnectOptions options;
    private final static Logger log = Logger.getLogger(SynchronousClient.class.getName());

    public SynchronousClient(String brokerUrl, String clientId, String userName, String password) {

        this.brokerUrl = brokerUrl;
        this.clientId = clientId;

        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // Construct the connection options object that contains connection parameters
            // such as cleanSession and LWT
            options = new MqttConnectOptions();
            options.setCleanSession(true);
            if(password != null ) {
                options.setPassword(password.toCharArray());
            }
            if(userName != null) {
                options.setUserName(userName);
            }

            // Construct an MQTT blocking mode client
            client = new MqttClient(brokerUrl,clientId, persistence);

        } catch (MqttException e) {
            log.severe(e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void publish(String topicName, int qos, byte[] payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload);
        message.setQos(qos);

        // Send the message to the server, control is not returned until
        // it has been delivered to the server meeting the specified
        // quality of service.
        client.publish(topicName, message);
        client.disconnect();
    }

    @Override
    public void subscribe(String topicName, int qos) throws MqttException {
        log.info("Subscribing to topic \""+topicName+"\" qos "+qos);
        client.subscribe(topicName, qos);
    }

    @Override
    public void connect() throws MqttException {
        client.connect(options);
        log.info("Connected to " + brokerUrl + " with client ID " + client.getClientId());

    }

    @Override
    public boolean isConnected() {
        return client.isConnected();
    }

    @Override
    public void disconnect() throws MqttException {
        client.disconnect();
        log.info("Disconnected");
    }

    @Override
    public void setCallBackHandler(MqttCallback callBackHandler) {
        // Set this wrapper as the callback handler
        client.setCallback(callBackHandler);
    }

    @Override
    public String getBrokerId() {
        return brokerUrl;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

}
