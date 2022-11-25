package GUi;

import Kiosk.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class UserGui extends JFrame {
    private JPanel MenuPanel;
    private JPanel BottomPanel;
    private JPanel TopPanel;

    Menu americano = new Menu(1,"Americano","Coffee", 1000, 0, "src\\Image\\americano.jpg");
    ImageIcon image = new ImageIcon(americano.getMenuImage());


    Vector<Menu> selectMenu = new Vector<Menu>();


    UserGui(){
        setTitle("Kiosk");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        MenuPanel();
    }
    void MenuPanel(){
        MenuPanel = new JPanel();
        MenuPanel.setLayout(new GridLayout(0, 3));
        add(MenuPanel, BorderLayout.CENTER);
        addMenu(americano);
    }

    void addMenu(Menu menu) {
        JButton button = new JButton();
        //label
        JLabel label = new JLabel();
        label.setText(menu.getMenuName());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        //image
        button.setIcon(image);
        MenuPanel.add(button);
        //Listener
        button.addActionListener(e -> {
            menu.setCount(menu.getCount() + 1);
            System.out.println(menu.getCount());
        });
    }

    void BottomPanel(){
        BottomPanel = new JPanel();
        add(BottomPanel, BorderLayout.SOUTH);
    }

    void TopPanel(){
        TopPanel = new JPanel();
        add(TopPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new UserGui();
    }

}

