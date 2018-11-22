package com.hz.learnboot.tomcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hezhao on 2018-07-10 09:06
 */
@Controller
public class PageController {

    @GetMapping(value = {"/", "/index"})
    public String index(ModelMap model) {
        model.put("title", "Hello，SpringBoot!");
        model.put("time", new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
        return "index";
    }
}
