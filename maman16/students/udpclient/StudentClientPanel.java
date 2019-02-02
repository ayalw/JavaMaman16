package maman16.students.udpclient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel with connect\disconnect buttons and text area to display list of connected students.
 */
public class StudentClientPanel extends JPanel implements IUdpMessageHandler {

    /**
     * reference to the backend controller module.
     */
    private UdpClientController m_controller;

    /**
     * Connect button
     */
    private JButton m_btnConnect;

    /**
     * Disconnect button
     */
    private JButton m_btnDisconnect;

    /**
     * Text area for list of students received from server.
     */
    private JTextArea m_txtAreaStudents;

    /**
     * Constructor
     * @param controller
     */
    public StudentClientPanel(UdpClientController controller) {
        super();
        m_controller = controller;
        m_btnConnect = new JButton("Connect");
        m_btnDisconnect = new JButton("Disconnect");
        m_txtAreaStudents = new JTextArea(20,30);
        m_txtAreaStudents.setText("[OFFLINE]");
        m_txtAreaStudents.setEditable(false);
        add(m_btnConnect);
        add(m_btnDisconnect);
        add(new JScrollPane( m_txtAreaStudents ));
        m_btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentClientPanel parentPanel = StudentClientPanel.this;
                m_controller.connect((IUdpMessageHandler) parentPanel);
                repaint();
            }
        });

        m_btnDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_controller.disconnect();
                m_txtAreaStudents.setText("[OFFLINE]");
                repaint();
            }
        });
        setVisible(true);
    }

    /**
     * Update UI buttons
     */
    @Override
    public void repaint() {
        super.repaint();
        if (m_controller == null) return;
        if (m_controller.isConnected()) {
            m_btnConnect.setEnabled(false);
            m_btnDisconnect.setEnabled(true);
        }
        else {
            m_btnConnect.setEnabled(true);
            m_btnDisconnect.setEnabled(false);
        }
    }

    /**
     * Print incoming server message - list of connected students.
     * @param message
     */
    @Override
    public void onUdpMessageArrived(String message) {
        m_txtAreaStudents.setText("[ONLINE] " + message);
        repaint();
    }
}
