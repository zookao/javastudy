package com.zookao.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zookao.boot.bean.Student;
import com.zookao.boot.bean.User;
import com.zookao.boot.exception.UserTooManyException;
import com.zookao.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-16
 */
@Controller
public class TableController {

    @Autowired
    StudentService studentService;

    @GetMapping("/basicT")
    public String basic(){
        return "table/basic_table";
    }

    @GetMapping("/dynamicT")
    public String dynamic(@RequestParam(value = "p",defaultValue = "1") Integer p, Model model){
        // ArrayList<User> users = new ArrayList<User>();
        // users.add(new User("zookao","111111"));
        // users.add(new User("chao","111111"));
        // users.add(new User("zongchao","111111"));
        // users.add(new User("caozongchao","111111"));
        //
        // if(users.size() > 3){
        //     throw new UserTooManyException();
        // }
        // model.addAttribute("users",users);


        // QueryWrapper<Student> wrapper = new QueryWrapper<>();
        // List<Student> list = studentService.list(null);
        // model.addAttribute("users",list);

        Page<Student> studentPage = new Page<>(p, 2);
        IPage<Student> page = studentService.page(studentPage, null);
        model.addAttribute("page",page);

        return "table/dynamic_table";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,
                             @RequestParam("p") Integer p,
                             RedirectAttributes ra){
        studentService.removeById(id);
        ra.addAttribute("p",p);
        return "redirect:/dynamicT";
    }

    @GetMapping("/editableT")
    public String editable(){
        return "table/editable_table";
    }

    @GetMapping("/pricingT")
    public String pricing(){
        return "table/pricing_table";
    }

    @GetMapping("/responsiveT")
    public String responsive(){
        return "table/responsive_table";
    }
}
