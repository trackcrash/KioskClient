package GUi;

import javax.swing.*;

public class UserGui extends JFrame {

    private JPanel MainPanel;

    public UserGui() {
        super("UserGui");
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1024, 720);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UserGui();
    }
}
