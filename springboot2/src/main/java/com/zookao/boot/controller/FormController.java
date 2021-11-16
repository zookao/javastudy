package com.zookao.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * User: zookao
 * Date: 2021-11-16
 */
@Slf4j
@Controller
public class FormController {

    @GetMapping("/upload")
    public String form(){
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String form(String email, String username, @RequestPart("headImg") MultipartFile headImg,@RequestPart("photos") MultipartFile photos) throws IOException {
        log.info("邮箱：{}，用户名：{}，头像：{}，生活照：{}",email,username,headImg,photos);
        if(!headImg.isEmpty()){
            String originalFilename = headImg.getOriginalFilename();
            File folder = new File("springboot2/src/main/resources/static/uploads/").getAbsoluteFile();
            if(!folder.exists()){
                folder.mkdirs();
            }

            headImg.transferTo(new File(folder,originalFilename));
        }
        return "";
    }
}
