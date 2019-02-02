package maman16.students.udpclient;

public interface IUdpMessageHandler {
    void onUdpMessageArrived(String message);
}
