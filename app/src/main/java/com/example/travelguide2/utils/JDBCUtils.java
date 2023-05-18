package com.example.travelguide2.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {
    private static final String TAG = "mysql-travelGuide-JDBCUtils";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String dbName = "travelguide";
    private static String user = "root";
    private static String password = "root";
    public static Connection gerConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            String ip = "10.0.2.2";
            connection = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+dbName,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
