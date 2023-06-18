package com.example.travelguide2.dao;

import static com.example.travelguide2.utils.JDBCUtils.getConnection;

import android.util.Log;

import com.example.travelguide2.entity.Article;
import com.example.travelguide2.utils.ImageHelper;
import com.example.travelguide2.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    private static final String TAG = "mysql-travelGuide-ArticleDao";

    //增加一篇文章
    public boolean addArticle(Article article){
        Connection connection = JDBCUtils.getConnection();
        try {
            String sql = "insert into article(title,author,type,release_date,content,views,cover_picture) values(?,?,?,?,?,?,?)";
            if (connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    ps.setString(1,article.getTitle());
                    ps.setString(2,article.getAuthor());
                    ps.setString(3,article.getType());
                    ps.setString(4,article.getRelease_date());
                    ps.setString(5,article.getContent());
                    ps.setInt(6,article.getViews());
                    ps.setString(7,article.getCover_picture());

                    int rs = ps.executeUpdate();
                    if (rs > 0)
                        return true;
                    else
                        return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"异常AddArticle:"+e.getMessage());
            return false;
        }
        return false;
    }

    //查找数据库所有文章
    public List<Article> getAllInfo(){
        List<Article> articleList=new ArrayList<>();//存放文章的数组
        Article article=new Article();//初始化数组
        Connection connection = getConnection();
        try {
            String sql = "select * from article";
//            String sql = "select * from article where id=?";
            if (connection!=null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps!=null){
//                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if(rs!=null){
                        int count = rs.getMetaData().getColumnCount();//如果结果集不为空，先获取列的总数
//                        Log.e("prx","列总数：" + count);
                        while (rs.next()){
                            for (int i=1;i<=count;i++){
                                //获取数据表的列的名称
                                String field = rs.getMetaData().getColumnName(i);
//                                Log.e("prx","值:  "+rs.getString(field));
                                //通过键值来赋值给对应的article的元素
                                switch (field){
                                    case "id":
                                        article.id=rs.getInt(field);
                                        break;
                                    case "author":
                                        article.author=rs.getString(field);
                                        break;
                                    case "release_date":
                                        article.release_date=rs.getString(field);
                                        break;
                                    case "title":
                                        article.title=rs.getString(field);
                                        break;
                                    case "content":
                                        article.content=rs.getString(field);
                                        break;
                                    case "views":
                                        article.views=rs.getInt(field);
                                        break;
                                    //图片获得
                                    case "cover_picture":
                                        article.cover_picture=rs.getString(field);
                                }
                            }
                            //保存到数组中
                            articleList.add(article);
                            article=new Article();//重新实例化article
                        }
                        connection.close();
                        ps.close();
//                        Log.e("sqllist","值:  "+articleList);
                        return articleList;
                    }else {
                        return null;
                    }
                }
            }else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

    public Article getInfoById(int id){
        Article article=new Article();//初始化数组
        Connection connection = getConnection();
        try {
            String sql = "select * from article where id=?";
            if (connection!=null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps!=null){
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if(rs!=null){
                        int count = rs.getMetaData().getColumnCount();//如果结果集不为空，先获取列的总数
//                        Log.e("prx","列总数：" + count);
                        while (rs.next()){
                            for (int i=1;i<=count;i++){
                                //获取数据表的列的名称
                                String field = rs.getMetaData().getColumnName(i);
//                                Log.e("prx","值:  "+rs.getString(field));
                                //通过键值来赋值给对应的article的元素
                                switch (field){
                                    case "id":
                                        article.id=rs.getInt(field);
                                        break;
                                    case "author":
                                        article.author=rs.getString(field);
                                        break;
                                    case "release_date":
                                        article.release_date=rs.getString(field);
                                        break;
                                    case "title":
                                        article.title=rs.getString(field);
                                        break;
                                    case "content":
                                        article.content=rs.getString(field);
                                        break;
                                    case "views":
                                        article.views=rs.getInt(field);
                                        break;
                                    //图片获得
                                    case "cover_picture":
                                        article.cover_picture=rs.getString(field);
                                }
                            }
                        }
                        connection.close();
                        ps.close();
                        Log.e("sqllist","值:  "+article);
                        return article;
                    }else {
                        return null;
                    }
                }
            }else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

}
