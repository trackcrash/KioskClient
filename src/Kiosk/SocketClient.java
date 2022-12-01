package Kiosk;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SocketClient {
    private Socket socket;
    private int port = 5050;
    private String address = "localhost";

//    public void ConnectToServer(){
////
////        try {
////            socket = new Socket(address, port);
////            if(socket.isConnected()){
////                System.out.println("Connected to Server");
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
    public void SendMenu(ArrayList<Menu> selectMenu,int totalPrice, JPanel BottomPanel){
        try{
            //socket
            socket = new Socket(address, port);
            if(socket.isConnected()){
                System.out.println("Connected to Server");
            }
            //input, output
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            byte[] buffer = new byte[1024];
            Boolean isend = false;
            //메뉴 전송
            for(Menu menu : selectMenu){
                String data = menu.getMenuID() + " " +  menu.getCount() + " ";
                output.write(data.getBytes(StandardCharsets.UTF_8));
                output.flush();
                System.out.println("Send Menu : " + data);
            }
            //결제 완료 후 selectMenu 초기화
            for(Menu menu : selectMenu){
                menu.setCount(0);
            }
            selectMenu.clear();
            BottomPanel.removeAll();
            BottomPanel.revalidate();
            BottomPanel.repaint();
            //message
            JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
            //close
            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}


