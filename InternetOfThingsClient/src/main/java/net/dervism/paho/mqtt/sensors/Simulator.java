package net.dervism.paho.mqtt.sensors;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by dervism on 19/11/14.
 */
public class Simulator implements Sensor {

    private Random random;

    public Simulator() {
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public String read() {
        return "" + random.nextInt(1000);
    }

}
