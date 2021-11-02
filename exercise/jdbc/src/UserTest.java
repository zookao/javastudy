import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * User: zookao
 * Date: 2021-10-15
 */
public class UserTest {

    public Db db;

    {
        db = new Db();
    }

    @Test
    public void test1(){
        Connection conn = db.getConn();
        QueryRunner runner = new QueryRunner();
        String sql = "select * from user where name like ? and age<?";
        BeanListHandler<User> handler = new BeanListHandler<User>(User.class);
        try {
            List<User> list = runner.query(conn,sql,handler,"%雨%",40);
            for (User user : list) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConn(conn);
        }
    }

    @Test
    public void test2(){
        Connection conn = db.getConn();
        QueryRunner runner = new QueryRunner();
        String sql = "select * from user where name like ? and age between ? and ? and email is not null";
        BeanListHandler<User> handler = new BeanListHandler<User>(User.class);
        try {
            List<User> list = runner.query(conn,sql,handler,"%雨%",20,40);
            for (User user : list) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConn(conn);
        }
    }

    @Test
    public void test3(){
        Connection conn = db.getConn();
        QueryRunner runner = new QueryRunner();
        String sql = "select * from user where name like ? or age>=? order by age desc,id asc";
        BeanListHandler<User> handler = new BeanListHandler<User>(User.class);
        try {
            List<User> list = runner.query(conn,sql,handler, "王%",25);
            for (User user : list) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConn(conn);
        }
    }

    @Test
    public void test4(){
        Connection conn = db.getConn();
        QueryRunner runner = new QueryRunner();
        String sql = "select * from user where date_format(create_time,'%Y-%m-%d')=? and manager_id in (select id from user where name like ?)";
        BeanListHandler<User> handler = new BeanListHandler<User>(User.class);
        try {
            List<User> list = runner.query(conn,sql,handler, "2019-02-14","王%");
            for (User user : list) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConn(conn);
        }
    }

    @Test
    public void test5(){
        Connection conn = db.getConn();
        QueryRunner runner = new QueryRunner();
        String sql = "select * from user where name like ? and (age<? or email is not null)";
        BeanListHandler<User> handler = new BeanListHandler<User>(User.class);
        try {
            List<User> list = runner.query(conn,sql,handler,"王%",40);
            for (User user : list) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConn(conn);
        }
    }
}
