import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * User: zookao
 * Date: 2021-10-18
 */
public class UserDAOTest {

    private UserDAOImpl dao = new UserDAOImpl();

    @Test
    public void test1(){
        Connection conn = Db.getConn();
        String sql = "select * from user where name like ? and age<?";
        List<User> list = dao.getList(conn,sql,"%雨%",40);
        for (User user : list) {
            System.out.println(user);
        }
        Db.close(conn,null,null);
    }

    @Test
    public void test2(){
        Connection conn = Db.getConn();
        String sql = "select * from user where name like ? and age between ? and ? and email is not null";
        List<User> list = dao.getList(conn,sql,"%雨%",20,40);
        for (User user : list) {
            System.out.println(user);
        }
        Db.close(conn,null,null);
    }

    @Test
    public void test3(){
        Connection conn = Db.getConn();
        String sql = "select * from user where name like ? or age>=? order by age desc,id asc";
        List<User> list = dao.getList(conn,sql,"王%",25);
        for (User user : list) {
            System.out.println(user);
        }
        Db.close(conn,null,null);
    }

    @Test
    public void test4(){
        Connection conn = Db.getConn();
        String sql = "select * from user where date_format(create_time,'%Y-%m-%d')=? and manager_id in (select id from user where name like ?)";
        List<User> list = dao.getList(conn,sql,"2019-02-14","王%");
        for (User user : list) {
            System.out.println(user);
        }
        Db.close(conn,null,null);
    }

    @Test
    public void test5(){
        Connection conn = Db.getConn();
        String sql = "select * from user where name like ? and (age<? or email is not null)";
        List<User> list = dao.getList(conn,sql,"王%",40);
        for (User user : list) {
            System.out.println(user);
        }
        Db.close(conn,null,null);
    }
}
