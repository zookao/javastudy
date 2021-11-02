import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接关闭封装
 * User: czc
 * Date: 2021-10-18
 */
public class Db {
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConn(){
        Connection conn = null;
        FileInputStream fi = null;
        try {
            Properties p = new Properties();
            fi = new FileInputStream("jdbc.properties");
            p.load(fi);
            String uri = p.getProperty("uri");
            String user = p.getProperty("user");
            String password = p.getProperty("password");
            String driver = p.getProperty("driver");
            Class.forName(driver);//静态代码块自动DriverManager.registerDriver();
            conn = DriverManager.getConnection(uri,user,password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return conn;
    }

    /**
     * 关闭数据库相关的各种连接
     * @param connection
     * @param ps
     * @param rs
     */
    public static void close(Connection connection, PreparedStatement ps, ResultSet rs){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
