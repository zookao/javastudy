package com.zookao.boot;

import com.zookao.boot.bean.Student;
import com.zookao.boot.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class Springboot2ApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        for (Map<String, Object> map : maps) {
            System.out.println("map = " + map);
        }

        log.info("数据源类型:{}",dataSource.getClass());
    }

    @Test
    public void testStudentMapper(){
        Student student = studentMapper.selectById(1L);
        System.out.println("student = " + student);
    }

    @Test
    public void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello","world");
        String hello = operations.get("hello");
        System.out.println("hello = " + hello);
    }
}
