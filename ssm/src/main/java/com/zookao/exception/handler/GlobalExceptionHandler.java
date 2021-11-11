package com.zookao.exception.handler;

import com.zookao.exception.AgeException;
import com.zookao.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: zookao
 * Date: 2021-11-11
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //ex指抛过来的异常对象
    @ExceptionHandler(value = NameException.class)
    public ModelAndView nameException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message",ex.getMessage());
        mv.addObject("trace",ex.toString());
        mv.setViewName("error");
        return mv;
    }

    @ExceptionHandler(value = AgeException.class)
    public ModelAndView ageException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message",ex.getMessage());
        mv.addObject("trace",ex.toString());
        mv.setViewName("error");
        return mv;
    }

    //不匹配的异常走这个方法
    @ExceptionHandler
    public ModelAndView mostException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message",ex.getMessage());
        mv.addObject("trace",ex.toString());
        mv.setViewName("error");
        return mv;
    }
}
