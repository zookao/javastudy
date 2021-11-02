package test2;

import java.sql.Connection;
import java.util.List;

public class StarDAOImpl extends BaseDAO implements StarDAO {

	@Override
	public void insert(Connection conn, Star star) {
		String sql = "insert into star(name,salary)values(?,?)";
		update(conn, sql,star.getName(),star.getSalary());
	}

	@Override
	public void deleteById(Connection conn, int id) {
		String sql = "delete from star where id = ?";
		update(conn, sql, id);
	}

	@Override
	public void update(Connection conn, Star star) {
		String sql = "update star set name = ?,salary = ? where id = ?";
		update(conn, sql,star.getName(),star.getSalary(),star.getId());
	}

	@Override
	public Star getStarById(Connection conn, int id) {
		String sql = "select id,name,salary from star where id = ?";
		Star star = getInstance(conn,Star.class,sql,id);
		return star;
	}

	@Override
	public List<Star> getAll(Connection conn) {
		String sql = "select id,name,salary from star";
		List<Star> list = getForList(conn, Star.class, sql);
		return list;
	}

	@Override
	public Long getCount(Connection conn) {
		String sql = "select count(*) from star";
		return getValue(conn, sql);
	}
}
