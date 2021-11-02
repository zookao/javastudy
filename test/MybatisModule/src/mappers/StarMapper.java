package mappers;

import models.Star;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User: zookao
 * Date: 2021-10-18
 */
public interface StarMapper {
    Star getStarById(Integer id);

    Star getStarByNameAndCompany(@Param("name") String name, @Param("company") String company);

    Star getStarByMap(Map<String,Object> map);

    List<Star> getStarsByNameLike(String name);

    Map<String,Object> getStarByIdReturnMap(Integer id);

    @MapKey("id")
    Map<Integer,Star> getStarsMapByNameLikeReturnMap(String name);

    public void addStar(Star star);

    public void updateStar(Star star);

    public void deleteStarById(Integer id);
}
