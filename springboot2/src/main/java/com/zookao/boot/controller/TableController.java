package com.zookao.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String dynamic(){
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
