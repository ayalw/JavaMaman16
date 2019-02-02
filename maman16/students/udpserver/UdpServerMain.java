package maman16.students.udpserver;

public class UdpServerMain {

    /**
     * Main class
     * @param args
     */
    public static void main (String[] args) {

        StudentsUdpServer server = new StudentsUdpServer("StudentNames.txt");
        server.startRunning();
    }
}
