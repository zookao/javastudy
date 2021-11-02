import java.sql.Connection;
import java.util.List;

/**
 * UserDAO的实现类
 * User: zookao
 * Date: 2021-10-18
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public List<User> getList(Connection conn, String sql, Object... args) {
        List<User> list = getList(conn, User.class, sql, args);
        return list;
    }
}
