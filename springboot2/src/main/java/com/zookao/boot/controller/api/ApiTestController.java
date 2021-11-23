package com.zookao.boot.controller.api;

import com.zookao.boot.bean.Student;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zookao
 * Date: 2021-11-23
 */
@Api(tags = "swagger测试demo")
@RestController
public class ApiTestController {

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
}
