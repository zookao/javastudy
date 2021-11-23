package com.zookao.boot.controller.api;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zookao.boot.bean.Student;
import com.zookao.boot.service.StudentService;
import com.zookao.boot.util.JWTUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * User: zookao
 * Date: 2021-11-23
 */
@Api(tags = "swagger测试demo")
@RestController
@Slf4j
public class ApiTestController {

    @Autowired
    StudentService studentService;

    @ApiOperation("helloworld测试")
    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world";
    }

    @ApiOperation("测试查询")
    @PostMapping("/search")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name",value = "姓名",required = true,paramType = "query"),
        @ApiImplicitParam(name = "age",value = "年龄",required = true,paramType = "query",dataType = "Integer",defaultValue = "1")
    })
    public String search(String name,Integer age){
        return name+":"+age;
    }

    @ApiOperation("测试添加")
    @PostMapping("/add")
    public String add(Student student){
        return student.getEmail();
    }

    @GetMapping("load")
    @ApiOperation("根据id获取学生")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query",dataType = "Integer")
    )
    @ApiResponses({
        @ApiResponse(code = 401, message = "请授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "地址错误")
    })
    public Student load(@RequestParam("id") Integer id){
        return new Student(1L,"zookao","zookao@126.com",18);
    }

    @GetMapping("/student/login")
    public Map<String, Object> studentLogin(Student student) {

        log.info("用户名：{}", student.getName());
        log.info("password: {}", student.getEmail());

        Map<String, Object> map = new HashMap<>();

        try {
            Map<String, String> login = studentService.login(student);

            Map<String, String> payload = new HashMap<>();
            payload.put("id", login.get("id"));
            payload.put("name", login.get("name"));
            String token = JWTUtil.getToken(payload);

            map.put("state", true);
            map.put("msg", "登录成功");
            map.put("token", token);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
            map.put("token", "");
        }
        return map;
    }

    @PostMapping("/student/test")
    public Map<String, Object> test(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        String name = verify.getClaim("name").asString();
        log.info("用户id：{}", id);
        log.info("用户名: {}", name);

        //TODO 业务逻辑
        Map<String, Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }
}
