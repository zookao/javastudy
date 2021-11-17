package com.zookao.boot.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: zookao
 * Date: 2021-11-17
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler
    // public String globalHandler(Exception ex){
    //     log.info("异常是：{}",ex);
    //     return "login";
    // }

    @ExceptionHandler
    public ModelAndView globalHandler(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",ex.getMessage());
        modelAndView.addObject("trace",ex.getStackTrace());
        modelAndView.setViewName("error/5xx");
        return modelAndView;
    }
}
