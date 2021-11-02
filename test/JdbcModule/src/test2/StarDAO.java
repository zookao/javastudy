package test2;

import java.sql.Connection;
import java.util.List;

public interface StarDAO {
	void insert(Connection conn,Star star);
	void deleteById(Connection conn,int id);
	void update(Connection conn,Star star);
	Star getStarById(Connection conn,int id);
	List<Star> getAll(Connection conn);
	Long getCount(Connection conn);
}	
