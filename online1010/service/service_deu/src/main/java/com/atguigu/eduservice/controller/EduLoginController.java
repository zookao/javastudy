package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/eduservice/user")
public class EduLoginController {
    //login
    @PostMapping(value = "login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    //info
    @GetMapping(value = "info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2406824311,76577852&fm=11&gp=0.jpg");
    }
}
