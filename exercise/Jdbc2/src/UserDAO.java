import java.sql.Connection;
import java.util.List;

/**
 * user表的操作接口
 * User: zookao
 * Date: 2021-10-18
 */
public interface UserDAO {
	List<User> getList(Connection conn,String sql,Object ...args);
}	
