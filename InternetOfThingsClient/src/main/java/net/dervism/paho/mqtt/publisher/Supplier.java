package net.dervism.paho.mqtt.publisher;

import java.io.IOException;

/**
 * Based on the java.util.function.Supplier interface, however, this Supplier also makes it
 * possible to construct lambdas that throw exceptions (such as io).
 *
 * Created by dervism on 18/11/14.
 */

@FunctionalInterface
public interface Supplier<R> {
    R get() throws Exception;
}
