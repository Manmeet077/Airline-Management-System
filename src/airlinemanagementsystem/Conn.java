package airlinemanagementsystem;

import java.sql.*;

public class Conn {
    static final String DB_URL = "jdbc:mysql://localhost:3306/detail";
    static final String username = "root";
    static final String password = "Pass@123";    
    Connection c;
    Statement s;
    
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  //registering mysql driver 
            c = DriverManager.getConnection(DB_URL, username, password);
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();  // to print exception
        }
    }
}