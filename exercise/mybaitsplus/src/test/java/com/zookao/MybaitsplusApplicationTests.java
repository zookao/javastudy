package com.zookao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zookao.mapper.UserCaozongchaoMapper;
import com.zookao.pojo.UserCaozongchao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MybaitsplusApplicationTests {

    @Autowired
    private UserCaozongchaoMapper mapper;

    @Test
    public void test01() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.like("name","雨").le("age",40).isNotNull("email");
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test02() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.like("name","雨").between("age",20,40);
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test03() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","王").or().ge("age",25).orderByDesc("age").orderByAsc("id");
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test04() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.apply("date_format(create_time,'%Y-%m-%d')='2019-02-14'").inSql("manager_id", "select id from user_caozongchao where name like '王%'");
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test05() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name","王").and(i -> i.lt("age", 40).or().isNotNull("email"));
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test06() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name","王").or(i -> i.lt("age", 40).gt("age",20).isNotNull("email"));
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test07() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.nested(i -> i.lt("age", 40).or().isNotNull("email")).likeRight("name","王");
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test08() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.in("age",30,31,34,35);
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test09() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.last("limit 1");
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test10_1() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name").like("name","雨").lt("age",40);
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test10_2() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name","age","email").like("name","雨").lt("age",40);
        List<UserCaozongchao> users = mapper.selectList(wrapper);
        for (UserCaozongchao user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test11() {
        QueryWrapper<UserCaozongchao> wrapper = new QueryWrapper<>();
        wrapper.select("avg(age) as avg_age","min(age) as min_age","max(age) as max_age").groupBy("manager_id").having("sum(age) <500");
        List<Map<String, Object>> maps = mapper.selectMaps(wrapper);
        System.out.println(maps);
    }
}
