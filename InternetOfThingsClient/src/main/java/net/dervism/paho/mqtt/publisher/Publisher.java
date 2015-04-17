package net.dervism.paho.mqtt.publisher;



/**
 * Created by dervism on 13/11/14.
 */
public interface Publisher {

    public void publish(String topicName, Supplier<byte[]> supplier);

}
