package mappers;

import models.Star;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-10-20
 */
public interface StarMapperDynamicSql {
    List<Star> getStarsByConditionIf(Star star);
    List<Star> getStarsByConditionTrim(Star star);
    List<Star> getStarsByConditionChoose(Star star);
    void updateStar(Star star);
    List<Star> getStarsByConditionForeach(@Param("ids")List<Integer> ids);
    void addStars(@Param("stars") List<Star> stars);
}
