package test2;

import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class StarDAOImplTest {

	private StarDAOImpl dao = new StarDAOImpl();
	
	@Test
	public void testInsert() {
		Connection conn = null;
		try {
			conn = Db.getConn();
			Star star = new Star(0, "宗超", new BigDecimal(2000));
			dao.insert(conn, star);
			System.out.println("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Db.close(conn, null,null);
		}
	}

	@Test
	public void testDeleteById() {
		Connection conn = null;
		try {
			conn = Db.getConn();
			dao.deleteById(conn, 2);
			System.out.println("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Db.close(conn, null,null);
		}
	}

	@Test
	public void testUpdateConnectionStar() {
		Connection conn = null;
		try {
			conn = Db.getConn();
			Star star = new Star(3,"李彦宏",new BigDecimal(600));
			dao.update(conn, star);
			System.out.println("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Db.close(conn, null,null);
		}
	}

	@Test
	public void testGetStarById() {
		Connection conn = null;
		try {
			conn = Db.getConn();
			Star star = dao.getStarById(conn, 3);
			System.out.println(star);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Db.close(conn, null,null);
		}
	}

	@Test
	public void testGetAll() {
		Connection conn = null;
		try {
			conn = Db.getConn();
			List<Star> list = dao.getAll(conn);
			list.forEach(System.out::println);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Db.close(conn, null,null);
		}
	}

	@Test
	public void testGetCount() {
		Connection conn = null;
		try {
			conn = Db.getConn();
			Long count = dao.getCount(conn);
			System.out.println("表中的记录数为：" + count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Db.close(conn, null,null);
		}
	}
}
