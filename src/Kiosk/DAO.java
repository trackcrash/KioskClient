package Kiosk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    //Sql
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/kiosk?serverTimezone=UTC";
    private static final String ID = "root";
    private static final String PW = "root";
    public List<Menu> GetMenu(){
        //메뉴 가져오기
        List<Menu> menuList = new ArrayList<Menu>();
        //DB연결
        Connection conn = null;
        //SQL문
        String sql = "select * from menu";
        //쿼리 실행
        PreparedStatement pstmt = null;
        //결과
        ResultSet rs = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, ID, PW);
            System.out.println("DB 연결 성공");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Menu menu = new Menu();
                menu.setMenuID(rs.getInt("ID"));
                menu.setMenuName(rs.getString("Name"));
                menu.setMenuDescription(rs.getString("Description"));
                menu.setMenuType(rs.getInt("Type"));
                menu.setMenuPrice(rs.getInt("Price"));
                menu.setCount(rs.getInt("Count"));
                menu.setMenuImage(rs.getString("Image"));
                menuList.add(menu);
            }
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
        finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return menuList;
    }

}
