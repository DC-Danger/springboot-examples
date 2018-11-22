package com.hz.learnboot.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/** 控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 22:54
 */
@Controller
public class IndexController {

    // MessageSource类可以获取messages的内容
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/")
    public String index(ModelMap model){
        Locale locale = LocaleContextHolder.getLocale();
        model.put("world", messageSource.getMessage("world", null, locale));
        return "index";
    }

}
