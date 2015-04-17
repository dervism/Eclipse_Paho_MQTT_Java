package net.dervism.paho.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by dervism on 15/11/14.
 */
public interface Client {

    public void publish(String topicName, int qos, byte[] payload) throws MqttException;
    public void subscribe(String topicName, int qos) throws MqttException;
    public void connect() throws MqttException;
    public boolean isConnected();
    public void disconnect() throws MqttException;
    public void setCallBackHandler(MqttCallback callBackHandler);
    public String getBrokerId();
    public String getClientId();

}
