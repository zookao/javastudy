package test3;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;
import test2.Star;
import test2.StarDAOImpl;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Properties;

/**
 * User: czc
 * Date: 2021-10-14
 */
public class DruidTest {
    @Test
    public void test1() throws Exception {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("druid.properties");
        properties.load(fis);
        DataSource source = DruidDataSourceFactory.createDataSource(properties);
        Connection conn = source.getConnection();
        System.out.println(conn);

        Star star = new Star(0, "超", new BigDecimal(3000));
        StarDAOImpl dao = new StarDAOImpl();
        dao.insert(conn, star);
        System.out.println("添加成功");
    }
}
