package com.wisezone.dao;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

/**
 * 通用的操作数据的类
 * 包括对数据库的建立连接，关闭连接，释放资源
 * 通用的增、删、改及通用的查询的功能
 */
public class DBUitls {
    //连接数据库的驱动类
    private static String DRIVER;
    //连接数据库的地址
    private static String URL;
    //数据库登录用户名
    private static String USER;
    //数据库登录密码
    private static String PASSWORD;

    static{
        Properties prop = new Properties();
        //   /代表根目录（src）
        //InputStream inputStream = DBUitls.class.getResourceAsStream("/jdbc.properties");

        //从ClassLoader获取的流相当于从根(src)目录下直接获取
        InputStream inputStream = DBUitls.class.getClassLoader().getResourceAsStream("jdbc.properties");

        //加载jdbc.properties
        try {
            prop.load(inputStream);

            //通过键取值
            DRIVER = prop.getProperty("jdbc.mysql.driver");
            URL = prop.getProperty("jdbc.mysql.url");
            USER = prop.getProperty("jdbc.mysql.user");
            PASSWORD = prop.getProperty("jdbc.mysql.password");

            Class.forName(DRIVER); //加载驱动
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开数据库连接
     * @return
     */
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接，释放资源
     * @param conn
     * @param pstmt
     * @param rs
     */
    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        //注意关闭时的顺序
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通用的增、删、改的方法
     * @param sql
     * @param params 可变长度的参数列表，当成一个数组用
     * @return
     */
    protected int executeUpdate(String sql,Object...params){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            //为sql语句的参数赋值
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1,params[i]);
                }
            }

            int row = pstmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{ //不管程序是否出现异常都会执行的代码块
            close(conn,pstmt,null);
        }
        return -1;
    }

    protected <T> List<T> queryList_relect(Class<T> tClass,String sql,Object...params){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null){
                for(int i = 0;i < params.length;i++){
                    pstmt.setObject(i + 1,params[i]);
                }
            }

            rs = pstmt.executeQuery();

            while (rs.next()){
                T t = tClass.newInstance();
                //为对象t的属性赋值
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();//获取列的个数
                for(int i = 0;i < columnCount;i++){
                    String c_name = metaData.getColumnLabel(i + 1); //获取列名
                    Field f = tClass.getDeclaredField(c_name);
                    f.setAccessible(true); //设置私有的属性可见
                    f.set(t,rs.getObject(c_name));
                }

                //将对象t插入到集合中
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return null;
    }

    /**
     * 通用的查询，sql中查询的列必须和javaBean中的属性名一致
     * @param tClass 查询的javaBean,必须有无参构造方法
     * @param sql    查询的sql语句
     * @param params sql语句的参数
     * @param <T>   泛型
     * @return  集合
     */
    protected <T> List<T> queryList(Class<T> tClass,String sql,Object...params){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null){
                for(int i = 0;i < params.length;i++){
                    pstmt.setObject(i + 1,params[i]);
                }
            }

            rs = pstmt.executeQuery();

            while (rs.next()){
                T t = tClass.newInstance();
                //为对象t的属性赋值
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();//获取列的个数
                for(int i = 0;i < columnCount;i++){
                    String c_name = metaData.getColumnLabel(i + 1); //获取列名
                    BeanUtils.setProperty(t,c_name,rs.getObject(c_name));
                }

                //将对象t插入到集合中
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return null;
    }

    /**
     * 查询并返回一条数据
     * @param tClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    protected <T> T queryOne(Class<T> tClass,String sql,Object...params){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            T t = tClass.newInstance();

            pstmt = conn.prepareStatement(sql);
            if (params != null){
                for (int i =0;i < params.length;i++){
                    pstmt.setObject(i + 1,params[i]);
                }
            }

            rs = pstmt.executeQuery();

            if (rs.next()){
                ResultSetMetaData metaData = rs.getMetaData();
                for(int j = 0;j < metaData.getColumnCount();j++){
                    String name = metaData.getColumnLabel(j + 1); //列名
                    Object value = rs.getObject(name); //根据列名取值
                    BeanUtils.setProperty(t,name,value);
                }
            }

            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return null;
    }

    /**
     * 这种查询适合用于多表连查
     * @param sql
     * @param params
     * @return
     */
    protected List<Map> queryMap(String sql,Object...params){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null)
                for (int i = 0;i < params.length;i++)
                    pstmt.setObject(i + 1,params[i]);
            rs = pstmt.executeQuery();
            while (rs.next()){
                ResultSetMetaData metaData = rs.getMetaData();
                Map map = new HashMap();//通过map存一行数据
                for (int i = 0;i < metaData.getColumnCount();i++){
                    String key = metaData.getColumnLabel(i + 1);
                    Object value = rs.getObject(key);
                    map.put(key,value);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return list;
    }
}
