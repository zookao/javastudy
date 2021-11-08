package com.zookao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pojo.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * User: zookao
 * Date: 2021-11-05
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = {"/some.c","/first.c"},method = RequestMethod.GET)
    public ModelAndView doSome(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        ModelAndView mv = new ModelAndView();
        //request域
        mv.addObject("name","welcome-other："+request.getParameter("name"));

        //forward操作
        mv.setViewName("welcome");//springmvc.xml的视图解析器
        return mv;
    }

    @RequestMapping(value = "/other.c",method = RequestMethod.POST)
    public ModelAndView doOther(@RequestParam(value = "rname",required = true) String name, @RequestParam(value = "rage",required = false)Integer age){
        ModelAndView mv = new ModelAndView();
        //request域
        mv.addObject("name",name);

        //forward操作
        mv.setViewName("welcome");
        return mv;
    }

    @RequestMapping(value = "/object.c",method = RequestMethod.POST)
    public ModelAndView doOther(Student s){

        ModelAndView mv = new ModelAndView();
        //request域
        mv.addObject("name",s.getName());
        mv.addObject("age",s.getAge());
        mv.addObject("s",s);
        //forward操作
        mv.setViewName("welcome");
        return mv;
    }

    @RequestMapping(value = {"/string.c"},method = RequestMethod.GET)
    public String doSome(HttpServletRequest request){
        request.setAttribute("name","zookao");
        request.setAttribute("age","18");
        return "welcome";
    }

    @RequestMapping(value = {"/json.c"},method = RequestMethod.GET)
    @ResponseBody
    public Student json(HttpServletRequest request){
        Student student = new Student();
        student.setAge(18);
        student.setName("zookao");
        return student;
    }
}
