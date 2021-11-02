package mappers;

import models.Star;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-10-19
 */
public interface StarMapperPlus {
    Star getStarById(Integer id);

    Star getStarAndNationById(Integer id);

    Star getStarByIdStep(Integer id);

    List<Star> getStarsByNationId(Integer id);
}
