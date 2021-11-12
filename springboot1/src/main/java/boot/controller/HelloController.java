package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zookao
 * Date: 2021-11-12
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/index")
    public String index(){
        return "Hello world";
    }
}
