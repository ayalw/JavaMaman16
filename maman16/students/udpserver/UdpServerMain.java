package maman16.students.udpserver;

public class UdpServerMain {

    public static void main (String[] args) {

        StudentsUdpServer server = new StudentsUdpServer("StudentNames.txt");
        server.startRunning();
    }
}
