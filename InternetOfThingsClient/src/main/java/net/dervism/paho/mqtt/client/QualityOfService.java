package net.dervism.paho.mqtt.client;

import sun.security.provider.PolicySpiFile;

/**
 * Defines QoS settings for the MQTT protocol version 3.1.
 *
 * The descriptions here is copied form:
 * http://www.infoq.com/articles/practical-mqtt-with-paho
 *
 * Created by dervism on 15/11/14.
 */
public class QualityOfService {

    /**
     * A QoS of 0, "At Most Once", is the fastest mode, where the client doesn't wait
     * for an acknowledgement. This means, of course, that if there’s a disconnection
     * or server failure, a message may be lost.
     */
    public static final int AT_MOST_ONCE = 0;

    /**
     * By default, a new message instance is set to "At Least Once", a Quality of Service (QoS)
     * of 1, which means the sender will deliver the message at least once and, if there's no
     * acknowledgement of it, it will keep sending it with a duplicate flag set until an acknowledgement
     * turns up, at which point the client removes the message from its persisted set of messages.
     */
    public static final int AT_LEAST_ONCE = 1;

    /**
     * QoS of 2, "Exactly Once", uses two pairs of exchanges, first to transfer the message and
     * then to ensure only one copy has been received and is being processed. This does make Exactly Once
     * the slower but most reliable QoS setting.
     */
    public static final int EXACTLY_ONCE = 2;


}
