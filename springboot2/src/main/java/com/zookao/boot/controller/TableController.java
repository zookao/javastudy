package com.zookao.boot.controller;

import com.zookao.boot.bean.User;
import com.zookao.boot.exception.UserTooManyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

/**
 * User: zookao
 * Date: 2021-11-16
 */
@Controller
public class TableController {

    @GetMapping("/basicT")
    public String basic(){
        return "table/basic_table";
    }

    @GetMapping("/dynamicT")
    public String dynamic(Model model){
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("zookao","111111"));
        users.add(new User("chao","111111"));
        users.add(new User("zongchao","111111"));
        users.add(new User("caozongchao","111111"));

        if(users.size() > 3){
            throw new UserTooManyException();
        }

        model.addAttribute("users",users);
        return "table/dynamic_table";
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
