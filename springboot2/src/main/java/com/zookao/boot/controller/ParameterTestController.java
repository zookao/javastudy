package com.zookao.boot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: zookao
 * Date: 2021-11-20
 */
@RestController
public class ParameterTestController {

    @GetMapping("/car/{id}/owner/{user}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("user") String user,
                                     @PathVariable Map<String,String> pathVars,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String,String> headers,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters") List<String> inters,
                                     @RequestParam Map<String,String> params,
                                     @CookieValue("_ga") String _ga,
                                     @CookieValue("_ga") Cookie cookie){
        HashMap<String, Object> hashmap = new HashMap<>();

        hashmap.put("id",id);
        hashmap.put("user",user);
        hashmap.put("pathVars",pathVars);
        hashmap.put("userAgent",userAgent);
        hashmap.put("headers",headers);
        hashmap.put("age",age);
        hashmap.put("inters",inters);
        hashmap.put("params",params);
        hashmap.put("_ga",_ga);
        hashmap.put("cookie",cookie); //cookie.getName(),cookie.getValue()
        return hashmap;
    }

    @PostMapping("/save")
    public Map<String,Object> getCar(@RequestBody String body){
        HashMap<String, Object> hashmap = new HashMap<>();

        hashmap.put("body",body);

        return hashmap;
    }
}
