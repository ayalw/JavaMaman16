package maman16.students.udpclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentClientPanel extends JPanel implements IUdpMessageHandler {

    private UdpClientController m_controller;
    private JButton m_btnConnect;
    private JButton m_btnDisconnect;
    private JTextArea m_txtAreaStudents;

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

    @Override
    public void onUdpMessageArrived(String message) {
        m_txtAreaStudents.setText("[ONLINE] " + message);
        repaint();
    }
}
