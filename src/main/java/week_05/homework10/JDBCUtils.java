package week_05.homework10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String drivername;
    private static String url;
    private static String user;
    private static String password;

    /**
     * 通过静态代码块，初始化数据库连接配置数据，并且注册数据库驱动
     */
    static {
        try {
            Properties pr = new Properties();
            //通过读取Properties文件给属性赋值，即每次使用该工具类都会读取最新配置进行连接
            pr.load(new FileInputStream(new File("jdbc.properties")));
            drivername = pr.getProperty("drivername");
            url = pr.getProperty("url");
            user = pr.getProperty("user");
            password = pr.getProperty("password");
            Class.forName(drivername);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
    }
    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
        return con;
    }
    /**
     * 关闭JDBC相关资源
     * @param con
     * @param sta
     * @param rs
     */
    public static void closeResource(Connection con, Statement sta, ResultSet rs) {
        try {
            if(con!=null) {
                con.close();
            }
            if(sta!=null) {
                sta.close();
            }
            if(rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     * @param sta
     * @param sql
     * @param rs
     * @throws SQLException
     */
    private static void qry(Statement sta,String sql,ResultSet rs) throws SQLException {
        rs = sta.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getObject("deptno"));
        }
    }
    /**
     * 增删改
     * @param sta
     * @param sql
     * @return
     * @throws SQLException
     */
    private static int update(Statement sta,String sql) throws SQLException {
        return sta.executeUpdate(sql);
    }

    /**
     * 查询
     * @param sta
     * @param sql
     * @param rs
     * @throws SQLException
     */
    private static void qry(PreparedStatement sta,ResultSet rs) throws SQLException {
        rs = sta.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getObject("deptno"));
        }
    }
    /**
     * 增删改
     * @param sta
     * @param sql
     * @return
     * @throws SQLException
     */
    private static int update(PreparedStatement sta,String sql) throws SQLException {
        return sta.executeUpdate();
    }

    public void useStatement() throws SQLException {
        //通过工具类获取数据库连接对象
        Connection con = JDBCUtils.getConnection();
        //通过连接创建数据库执行对象
        Statement sta = con.createStatement();
        //为查询的结果集准备接收对象
        ResultSet rs = null;
        //查询
        String sqlStatement = "SELECT * FROM USER";
        qry(sta,sqlStatement,rs);
        //增加
        sqlStatement = "INSERT INTO USER VALUES('50')";
        System.out.println("插入执行结果:"+update(sta,sqlStatement));
        //更新
        sqlStatement = "UPDATE USER SET NAME='LWH' WHERE ID = '50'";
        System.out.println("更新执行结果:"+update(sta,sqlStatement));
        //删除
        sqlStatement = "DELETE FROM USER WHERE ID = '50'";
        System.out.println("删除执行结果:"+update(sta,sqlStatement));
        JDBCUtils.closeResource(con, sta, rs);
    }

    public void usePreparedStatement() throws SQLException {
        //通过工具类获取数据库连接对象
        Connection con = JDBCUtils.getConnection();
        //通过连接创建数据库执行对象
        PreparedStatement ps = null;
        //为查询的结果集准备接收对象
        ResultSet rs = null;
        //查询
        String sqlStatement = "SELECT * FROM USER WHERE ID = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "10");
        qry(ps,rs);
        //增加
        sqlStatement = "INSERT INTO USER VALUES(?,?)";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "50");
        ps.setObject(2, "LWH");
        System.out.println("插入执行结果:"+update(ps,sqlStatement));
        //更新
        sqlStatement = "UPDATE USER SET NAME=? WHERE ID = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "DDD");
        ps.setObject(2, "50");
        System.out.println("更新执行结果:"+update(ps,sqlStatement));
        //删除
        sqlStatement = "DELETE FROM USE WHERE ID = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "50");
        System.out.println("删除执行结果:"+update(ps,sqlStatement));
        JDBCUtils.closeResource(con, ps, rs);
    }

//    public static void main(String[] args) throws SQLException {
//        //通过工具类获取数据库连接对象
//        Connection con = JDBCUtils.getConnection();
//        //通过连接创建数据库执行对象
//        Statement sta = con.createStatement();
//        //为查询的结果集准备接收对象
//        ResultSet rs = null;
//        //查询
//        String sqlStatement = "SELECT * FROM USER";
//        qry(sta,sqlStatement,rs);
//        //增加
//        sqlStatement = "INSERT INTO USER VALUES('50')";
//        System.out.println("插入执行结果:"+update(sta,sqlStatement));
//        //更新
//        sqlStatement = "UPDATE USER SET NAME='LWH' WHERE ID = '50'";
//        System.out.println("更新执行结果:"+update(sta,sqlStatement));
//        //删除
//        sqlStatement = "DELETE FROM USER WHERE ID = '50'";
//        System.out.println("删除执行结果:"+update(sta,sqlStatement));
//        JDBCUtils.closeResource(con, sta, rs);
//    }
}
