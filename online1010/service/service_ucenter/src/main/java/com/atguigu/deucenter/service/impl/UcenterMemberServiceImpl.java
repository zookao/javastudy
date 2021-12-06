package com.atguigu.deucenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.deucenter.entity.UcenterMember;
import com.atguigu.deucenter.entity.vo.RegisterVo;
import com.atguigu.deucenter.mapper.UcenterMemberMapper;
import com.atguigu.deucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-14
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember member) { //用户登录
        System.out.println(member);
        //获取登录的手机号 密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)) {
            System.out.println("登陆失败");
            return "";
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if(ucenterMember==null||ucenterMember.getIsDisabled()){
            System.out.println("登陆失败");
            return "";
        }
        //判断密码
        //因为存储到数据库的密码都是经过加密的
        //把我们输入的密码进行加密 再与数据库的密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            System.out.println("登陆失败");
            return "";
        }
        System.out.println("登录成功");
        //登录成功
        //生成token字符串 使用jwt工具类
        return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) { //用户注册
        //获取注册的数据
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)){
            System.out.println("注册失败 1");
            return;
        }
        //判断验证码是否正确
        String s = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(s)){
            System.out.println("注册失败 2");
            return;
        }
        //判断手机号是否已经注册
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if(ucenterMember!=null){
            System.out.println("注册失败 3");
            return;
        }
        //数据插入数据库
        UcenterMember ucenterMember1 = new UcenterMember();
        BeanUtils.copyProperties(registerVo,ucenterMember1);
        ucenterMember1.setPassword(MD5.encrypt(password));
        baseMapper.insert(ucenterMember1);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) { //根据openid查询
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public int countRegister(String day) { //查询某一天注册人数
        return baseMapper.countRegister(day);
    }
}
