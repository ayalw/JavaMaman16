package maman16.students.udpclient;

/**
 * Interface for classes who want to respond to incoming UDP packets.
 */
public interface IUdpMessageHandler {
    void onUdpMessageArrived(String message);
}
