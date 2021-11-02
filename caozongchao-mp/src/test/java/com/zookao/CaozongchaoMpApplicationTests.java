package com.zookao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zookao.mapper.UserMapper;
import com.zookao.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class CaozongchaoMpApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("马云");
        user.setAge(50);
        user.setEmail("mayun@alibaba.com");
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(6L);
        user.setName("马云2");
        user.setAge(51);
        int i = userMapper.updateById(user);
        System.out.println(user);
    }

    @Test
    public void testOptimisticLockerSuccess(){
        User user = userMapper.selectById(1L);
        user.setName("马化腾");
        user.setAge(49);
        user.setEmail("mahuateng@qq.com");
        userMapper.updateById(user);
    }

    @Test
    public void testOptimisticLockerFail(){
        User user = userMapper.selectById(1L);
        user.setName("马化腾1");
        user.setAge(49);
        user.setEmail("mahuateng@qq.com");

        User user2 = userMapper.selectById(1L);
        user2.setName("马化腾2");
        user2.setAge(49);
        user2.setEmail("mahuateng@qq.com");
        userMapper.updateById(user2);

        userMapper.updateById(user);
    }

    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","马云2");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testPage(){
        Page<User> userPage = new Page<>(1,2);
        userMapper.selectPage(userPage,null);
        userPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testDeleteById(){
        userMapper.deleteById(2L);
    }

    @Test
    public void testDeleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(2, 3, 4));
    }

    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",3);
        int i = userMapper.deleteByMap(map);
    }
}
