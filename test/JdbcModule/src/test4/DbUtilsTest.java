package test4;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;
import test2.Star;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * User: czc
 * Date: 2021-10-14
 */
public class DbUtilsTest {
    @Test
    public void test1() {
        Properties properties = new Properties();
        Connection conn = null;
        try {
            FileInputStream fis = new FileInputStream("druid.properties");
            properties.load(fis);
            DataSource source = DruidDataSourceFactory.createDataSource(properties);
            conn = source.getConnection();

            QueryRunner qr = new QueryRunner();
            String sql = "insert into star(name,salary) values(?,?)";
            int count = qr.update(conn, sql, "曹扬", new BigDecimal(4000));
            System.out.println("功插入了"+count+"条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() {
        Properties properties = new Properties();
        Connection conn = null;
        try {
            FileInputStream fis = new FileInputStream("druid.properties");
            properties.load(fis);
            DataSource source = DruidDataSourceFactory.createDataSource(properties);
            conn = source.getConnection();

            // QueryRunner qr = new QueryRunner();
            // String sql = "select * from star where id=?";
            // BeanHandler<Star> handler = new BeanHandler<Star>(Star.class);
            // Star query = qr.query(conn, sql, handler,3);
            // System.out.println(query);

            // QueryRunner qr = new QueryRunner();
            // String sql = "select * from star";
            // BeanListHandler<Star> starBeanListHandler = new BeanListHandler<Star>(Star.class);
            // List<Star> query = qr.query(conn, sql, starBeanListHandler);
            // query.forEach(System.out::println);

            // QueryRunner qr = new QueryRunner();
            // String sql = "select * from star";
            // MapListHandler mapListHandler = new MapListHandler();
            // List<Map<String, Object>> query = qr.query(conn, sql, mapListHandler);
            // System.out.println(query);

            // QueryRunner qr = new QueryRunner();
            // String sql = "select count(*) from star";
            // ScalarHandler<Long> integerScalarHandler = new ScalarHandler<>();
            // Long query = qr.query(conn, sql, integerScalarHandler);
            // System.out.println(query);

            QueryRunner qr = new QueryRunner();
            String sql = "select id,name,salary from star";
            ResultSetHandler<Star> starResultSetHandler = new ResultSetHandler<Star>(){
                @Override
                public Star handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        BigDecimal salary = resultSet.getBigDecimal("salary");
                        Star star = new Star(id,name,salary);
                        return star;
                    }
                    return null;
                }
            };
            Star query = qr.query(conn, sql, starResultSetHandler);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
