package com.zookao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: zookao
 * Date: 2021-11-05
 */
@Controller
public class TestController {
    @RequestMapping(value = "/some.czc")
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        //request域
        mv.addObject("message","welcome");

        //forward操作
        mv.setViewName("/welcome.jsp");
        return mv;
    }
}
