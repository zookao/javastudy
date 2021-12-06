package com.atguigu.deucenter.mapper;

import com.atguigu.deucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-02-14
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int countRegister(String day);/*查询某一天注册人数*/
}
