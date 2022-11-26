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
    Menu latte = new Menu(2,"Latte","Coffee", 2000, 0, "src\\Image\\latte.jpg");
    Menu mocha = new Menu(3,"Mocha","Coffee", 3000, 0, "src\\Image\\mocha.jpg");
    Menu cappuccino = new Menu(4,"Cappuccino","Coffee", 4000, 0, "src\\Image\\cappuccino.jpg");
    ImageIcon image = new ImageIcon(americano.getMenuImage());

    Vector<Menu> selectMenu = new Vector<Menu>();
    int totalPrice = 0;
    //[]안에 DB에서 가져온 갯수만큼넣기
    Menu[] menu = new Menu[4];
    void setMenu(){
        //for문으로 넣기
        menu[0] = americano;
        menu[1] = latte;
        menu[2] = mocha;
        menu[3] = cappuccino;

    }
    UserGui(){
        setTitle("Kiosk");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setMenu();
        MenuPanel();
        BottomPanel();
    }
    void MenuPanel(){
        MenuPanel = new JPanel();
        MenuPanel.setLayout(new GridLayout(0, 3));
        add(MenuPanel, BorderLayout.CENTER);
        for(int i = 0; i < menu.length; i++){
            addMenu(menu[i]);
        }
    }

    void addMenu(Menu menu) {

        JButton button = new JButton();
        //label
        JLabel label = new JLabel();
        label.setText(menu.getMenuName());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        //image

        button.setIcon(image);
        MenuPanel.add(button);
        //Listener
        button.addActionListener(e -> {
            //selectMenu vector에 추가
            selectMenu.add(menu);
            //totalPrice에 가격 추가
            totalPrice += menu.getMenuPrice();
            menu.setCount(menu.getCount() + 1);
        });
    }

    void BottomPanel(){
        BottomPanel = new JPanel();
        BottomPanel.setSize(1080,300);
        //button
        JButton button = new JButton();
        //margin left 100
        button.setBounds(0, 0, 100, 200);
        button.setVisible(true);
        button.setText("Order");
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.addActionListener(e -> {
            System.out.println(totalPrice);
        });
        BottomPanel.add(button);
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

