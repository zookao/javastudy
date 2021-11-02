package mappers;

import models.Star;
import org.apache.ibatis.annotations.Select;

/**
 * User: zookao
 * Date: 2021-10-18
 */
public interface StarMapperAnnotation {
    @Select("select * from star where id = #{id}")
    Star getStarById(Integer id);
}
