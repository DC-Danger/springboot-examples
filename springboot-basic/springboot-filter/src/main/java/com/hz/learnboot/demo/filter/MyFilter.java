package com.hz.learnboot.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * 
 */
@WebFilter(filterName="myFilter", urlPatterns="/book/*")
@Order(1) // 定义执行的优先级，数字越小优先级越高
public class MyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig config) {
        logger.info("MyFilter 过滤器初始化...");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        logger.info("MyFilter 执行过滤操作...");

        HttpServletRequest request = (HttpServletRequest) req;
        logger.info("MyFilter - Request URL: {}", request.getRequestURL().toString());
        logger.info("MyFilter - Request port：{}", request.getServerPort());
        logger.info("MyFilter - Request Method: {}", request.getMethod());
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Current-Path", request.getServletPath());
        response.setHeader("My-Name", "MeiNanzi");

		ParameterMap<String, String[]> paramMap = (ParameterMap<String, String[]>)request.getParameterMap();
		paramMap.setLocked(false);
		paramMap.put("name2", new String[]{"张"});
//		paramMap.setLocked(true);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("MyFilter 过滤器销毁...");
    }

}
