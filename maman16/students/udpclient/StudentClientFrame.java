package maman16.students.udpclient;

import javax.swing.*;
import java.awt.*;

public class StudentClientFrame extends JFrame {

    private UdpClientController m_controller;
    private StudentClientPanel m_panel;

    public StudentClientFrame(UdpClientController controller) {
        super(controller.getStudentName() + " ---> " + controller.getServerAddress() + ":" + controller.getServerPort());
        setSize(400,400);
        m_controller = controller;
        //m_controller.startListening(this);
        setLayout(new GridLayout());
        m_panel = new StudentClientPanel(controller);
        add(m_panel);
        setVisible(true);
        m_panel.repaint();
    }
}
