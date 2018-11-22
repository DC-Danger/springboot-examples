package com.hz.learnboot.freemarker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * 处理静态资源URL:资源名称md5方式
 * <p/>
 * 页面引用方式：<code>${urls.getForLookupPath('/js/jquery.js') }</code>
 * <p/>
 * application.myl文件中添加如下配置：
 *      spring.resources.chain.strategy.content.enabled=true
 *      spring.resources.chain.strategy.content.paths=/**
 * <p/>
 */
@ControllerAdvice
public class ResourceUrlProviderController {

    @Autowired
    private ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("urls")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }
}