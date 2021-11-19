package com.zookao.boot.controller;

import com.zookao.boot.bean.Student;
import com.zookao.boot.bean.User;
import com.zookao.boot.service.impl.StudentServiceOriginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * User: zookao
 * Date: 2021-11-16
 */
@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    StudentServiceOriginImpl studentServiceOrigin;

    @GetMapping("/get-student")
    @ResponseBody
    public Student getStudentById(@RequestParam("id") Long id){
        return studentServiceOrigin.getStudentById(id);
    }

    @GetMapping("/get-students")
    @ResponseBody
    public List<Student> getStudents(){
        return studentServiceOrigin.getStudents();
    }

    @GetMapping("/sql")
    @ResponseBody
    public List<Map<String, Object>> sql(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        return maps;
    }

    @GetMapping(value = {"/","/login"})
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model){
        if(user.getUsername().equalsIgnoreCase("admin") && user.getPassword().equalsIgnoreCase("111111")){
            session.setAttribute("user",user);
            //重定向防止重复提交
            return "redirect:/index";
        }else{
            model.addAttribute("error","账号密码错误");
            return "login";
        }
    }

    @GetMapping("/index")
    public String index(HttpSession session,Model model){
        // int i = 10/0; //测试500错误
        if(session.getAttribute("user") != null){
            return "index";
        }else{
            model.addAttribute("error","请登录");
            return "redirect:/login";
        }
    }
}
