## Eclipse Paho MQTT (Java)

This project is a Java client implementation of Eclipse Paho MQTT simple messaging system for M2M-devices (Internet of Things).
The code demonstrates how to use MQTT to publish sensor data from devices like Raspberry PI and how to subscribe to a broker
to listen to information from other devices.

To test the code, simply run the application on to different JVM's and set the subscriberMode / publisherMode flags to true. The
publisher will then start broadcasting some random simulation data that you should be able to read on the second device.
