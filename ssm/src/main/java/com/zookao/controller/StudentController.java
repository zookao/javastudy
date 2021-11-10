package com.zookao.controller;

import com.zookao.pojo.Student;
import com.zookao.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-10
 */
@Controller
@RequestMapping("/s")
public class StudentController {
    @Resource
    private StudentService studentService;

    //注册学生
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String insertStudent(){
        return "student/register";
    }

    //注册学生
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView insertStudent(Student student){
        String name = student.getName();
        String message = name+"注册失败";
        int i = studentService.addStudent(student);
        if(i > 0){
            message = name+"注册成功";
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("message",message);
        mv.setViewName("student/register-result");
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "student/list";
    }

    @RequestMapping(value = "/list-ajax",method = RequestMethod.GET)
    @ResponseBody
    public List<Student> listAjax(){
        List<Student> students = studentService.findStudents();
        return students;
    }
}
