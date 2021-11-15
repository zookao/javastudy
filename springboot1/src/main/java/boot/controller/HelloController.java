package boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zookao
 * Date: 2021-11-12
 */
@Slf4j
@RestController
public class HelloController {
    @RequestMapping(value = "/index")
    public String index(){
        log.info("lombok的slf4j日志记录功能");
        return "Hello world333";
    }
}
