package maman16.students.udpclient;

import javax.swing.*;

public class StudentClientButtonPanel extends JPanel {

    private UdpClientController m_controller;
    private JButton m_btnConnect;
    private JButton m_btnDisconnect;

    public StudentClientButtonPanel(UdpClientController controller) {
        super();
        m_controller = controller;
        m_btnConnect = new JButton("Connect");
        m_btnDisconnect = new JButton("Disconnect");
        add(m_btnConnect);
        add(m_btnDisconnect);
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
}
