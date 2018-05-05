package com.telecom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;



    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        //model.addAttribute("message", "Hello Spring MVC Framework!");
        model.addAttribute("message", msg);
        return "hello";
    }

}