package GUi;

import Kiosk.DAO;
import Kiosk.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserGui extends JFrame {
    private JPanel MenuPanel;
    private JPanel BottomPanel;
    private JPanel TopPanel;
    private JLabel TotalPrice;
    ArrayList<Menu> selectMenu = new ArrayList<Menu>();
    int totalPrice = 0;
    UserGui(){
        setTitle("Kiosk");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        MenuPanel();
        setVisible(true);

    }
    void MenuPanel(){
        MenuPanel = new JPanel();
        MenuPanel.setLayout(new GridLayout(0, 3));
        getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add(MenuPanel, BorderLayout.CENTER);
        DAO dao = new DAO();
        List<Menu> menuList = dao.GetMenu();
        for(Menu menu : menuList){
            addMenu(menu);
        }
        BottomPanel();
        TopPanel();
        setVisible(true);
    }

    void addMenu(Menu menu) {
        //메뉴 담기용 패널
        JPanel panel = new JPanel();
        //margin bottom 10
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        //button & image
        JButton button = new JButton();
        ImageIcon image = new ImageIcon(menu.getMenuImage());
        ImageIcon resizeimage = new ImageIcon(image.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH));
        button.setIcon(resizeimage);
        panel.add(button, BorderLayout.CENTER);
        //name label
        JLabel name = new JLabel();
        name.setText(menu.getMenuName());
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setVerticalAlignment(JLabel.CENTER);
        name.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        panel.add(name, BorderLayout.NORTH);
        //price label
        JLabel price = new JLabel();
        price.setText(menu.getMenuPrice() + "원");
        price.setHorizontalAlignment(JLabel.CENTER);
        price.setVerticalAlignment(JLabel.CENTER);
        price.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        price.setVisible(true);
        panel.add(price, BorderLayout.SOUTH);
        //Listener
        button.addActionListener(e -> {
            //selectMenu vector에 추가
            if(!selectMenu.contains(menu)){
            selectMenu.add(menu);
            }
            //totalPrice에 가격 추가
            totalPrice += menu.getMenuPrice();
            UpdateTotalPrice();

            //BottomPanel에 추가
            menu.setCount(menu.getCount() + 1);
            BottomMenu(menu);
            BottomPanel.revalidate();
            BottomPanel.repaint();
            System.out.println(selectMenu);
        });
        MenuPanel.add(panel);
    }

    void BottomMenu(Menu menu){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //button & image
        JButton button = new JButton();
        ImageIcon image = new ImageIcon(menu.getMenuImage());
        ImageIcon resizeimage = new ImageIcon(image.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        button.setIcon(resizeimage);
        panel.add(button, BorderLayout.CENTER);
        //count label
        JLabel count = new JLabel();
        count.setText(menu.getCount() + "개");
        count.setHorizontalAlignment(JLabel.CENTER);
        count.setVerticalAlignment(JLabel.CENTER);
        count.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        panel.add(count, BorderLayout.NORTH);
        //Listener
        button.addActionListener(e -> {
            //totalPrice에 가격 추가
            totalPrice -= menu.getMenuPrice();
            UpdateTotalPrice();

            if(menu.getCount() == 1){
                BottomPanel.remove(panel);
                selectMenu.remove(menu);
                menu.setCount(0);
            }
            else{
                menu.setCount(menu.getCount() - 1);
                count.setText(menu.getCount() + "개");
            }
            BottomPanel.revalidate();
            BottomPanel.repaint();
        });
        System.out.println(menu.getMenuName() + " " + menu.getCount());
        if (menu.getCount() == 1) {
            BottomPanel.add(panel);
        } else if (menu.getCount() == 1 && selectMenu.contains(menu)) {
            BottomPanel.add(panel,selectMenu.indexOf(menu) + 1);
            selectMenu.remove(menu);
        } else {
            BottomPanel.remove(selectMenu.indexOf(menu) + 1);
            BottomPanel.add(panel, selectMenu.indexOf(menu) + 1);
        }

        System.out.println(selectMenu.indexOf(menu)+1);
        BottomPanel.revalidate();
        BottomPanel.repaint();
    }
    void UpdateTotalPrice(){
        TotalPrice = new JLabel();
        TotalPrice.setText("총 가격 : " + totalPrice + "원");
        TotalPrice.setHorizontalAlignment(JLabel.CENTER);
        TotalPrice.setVerticalAlignment(JLabel.CENTER);
        TotalPrice.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        if(TopPanel.getComponentCount() == 4){
            TopPanel.remove(3);
        }
        TopPanel.add(TotalPrice, 3);

    }

    void BottomPanel(){
        BottomPanel = new JPanel();
        BottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        BottomPanel.setSize(1080,400);
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
        TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TopPanel.setSize(1080, 300);
        //button
        JButton button[] = new JButton[3];
        for(int i = 0; i < 3; i++){
            button[i] = new JButton("버튼");
            TopPanel.add(button[i]);
        }
        add(TopPanel, BorderLayout.NORTH);
        //addTotalPrice
        UpdateTotalPrice();
    }

    public static void main(String[] args) {
        new UserGui();
    }

}

