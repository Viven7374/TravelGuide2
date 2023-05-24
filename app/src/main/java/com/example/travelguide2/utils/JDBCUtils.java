package com.example.travelguide2.utils;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    private static String TAG="mysql-travelGuide-JDBCUtils";
    private static final String dbName="travelguide";
    private static final String ip="192.168.0.106";//tcp连接或者本机iPv4地址  192.168.0.106 .natapp.cc
    private static final String port = "3306";//端口，默认是3306
    private static String url = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String user = "root";//数据库用户名
    private static final String password = "root";//数据库密码
    private static Connection connection =null;

    public static Connection getConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    try{
                        connection =DriverManager.getConnection(url,user,password);
                        Log.d(TAG,"run:"+connection);
                    }catch (SQLException throwables){
                        throwables.printStackTrace();
                    }
                }catch (ClassNotFoundException e){
                    e.printStackTrace();;
                    Log.d(TAG,"数据库连接失败");
                }
            }
        }).start();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"getConnect: "+connection);
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try{
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
