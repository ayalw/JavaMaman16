package maman16.students.udpclient;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window
 */
public class StudentClientFrame extends JFrame {

    /**
     * Backend logic for connecting to server
     */
    private UdpClientController m_controller;

    /**
     * Panel with user controls
     */
    private StudentClientPanel m_panel;

    /**
     * Constructor
     * @param controller
     */
    public StudentClientFrame(UdpClientController controller) {
        super(controller.getStudentName() + " ---> " + controller.getServerAddress() + ":" + controller.getServerPort());
        setSize(400,400);
        m_controller = controller;
        setLayout(new GridLayout());
        m_panel = new StudentClientPanel(controller);
        add(m_panel);
        setVisible(true);
        m_panel.repaint();
    }
}
