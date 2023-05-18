package com.example.travelguide2.dao;

import android.util.Log;

import com.example.travelguide2.entity.User;
import com.example.travelguide2.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class UserDao {

    private static final String TAG = "mysql-travelGuide-UserDao";
    //登陆
    public int login(String userName,String userPw){
        HashMap<String,Object> map = new HashMap<>();
        //建立连接
        Connection connection = JDBCUtils.gerConnection();
        int msg = 0;
        try {
            String sql = "select * from user where userName = ?";
            if (connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    Log.e(TAG,"用户名:"+ userName);
                    //根据账号进行查询
                    ps.setString(1,userName);
                    ResultSet rs = ps.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    //将查到的内容存储在map里
                    while (rs.next()){
                        for (int i = 1;i <= count;i++){
                            String field = rs.getMetaData().getColumnName(i);
                            map.put(field,rs.getString(field));
                        }
                    }
                    connection.close();
                    ps.close();
                    if (map.size() != 0){
                        StringBuilder s = new StringBuilder();
                        //密码是否匹配
                        for (String key : map.keySet()){
                            if (key.equals("userPw")){
                                if (userPw.equals(map.get(key))){
                                    msg = 1; //密码正确
                                }else
                                    msg = 2; //密码错误
                                break;
                            }
                        }
                    }else {
                        Log.e(TAG,"查询结果为空");
                        msg = 3;
                    }
                }else{
                    msg = 0;
                }
            }else {
                msg = 0;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"异常login:"+e.getMessage());
            msg = 0;
        }
        return msg;
    }

    //注册
    public boolean register(User user){
        HashMap<String,Object> map = new HashMap<>();
        Connection connection = JDBCUtils.gerConnection();
        try {
            String sql = "insert into user(userName,userPw) values(?,?)";
            if (connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    //将数据插入数据库
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getUserPw());

                    int rs = ps.executeUpdate();
                    if (rs > 0)
                        return true;
                    else
                        return false;
                }else {
                    return false;
                }
            }else {
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"异常register:"+ e.getMessage());
            return false;
        }
    }

    //根据用户名查找该用户是否存在
    public User findUser(String userName){
        Connection connection = JDBCUtils.gerConnection();
        User user = null;
        try {
            String sql = "select * from user where userName = ?";
            if (connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    ps.setString(1,userName);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        int id = rs.getInt(1);
                        String userName1 = rs.getString(2);
                        String userPw = rs.getString(3);
                        user = new User(id,userName1,userPw);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"异常findUser:"+ e.getMessage());
            return null;
        }
        return user;
    }


}
