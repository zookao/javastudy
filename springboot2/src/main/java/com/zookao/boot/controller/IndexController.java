package com.zookao.boot.controller;

import com.zookao.boot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * User: zookao
 * Date: 2021-11-16
 */
@Controller
public class IndexController {

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
        if(session.getAttribute("user") != null){
            return "index";
        }else{
            model.addAttribute("error","请登录");
            return "redirect:/login";
        }
    }
}
