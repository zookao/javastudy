package mappers;

import models.Nation;

/**
 * User: zookao
 * Date: 2021-10-19
 */
public interface NationMapper {
    Nation getNationById(Integer id);
    Nation getNationByIdPlus(Integer id);
    Nation getNationByIdStep(Integer id);
}
