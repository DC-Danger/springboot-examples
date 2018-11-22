package com.hz.learnboot.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hz.learnboot.demo.interceptor.MyInterceptor1;
import com.hz.learnboot.demo.interceptor.MyInterceptor2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

// 在Spring Boot 2.0后用自己的的配置类继承WebMvcConfigurerAdapter时，idea提示这个类已经过时了
// 建议自己写的配置类实现WebMvcConfigurer接口，这个接口的方法都加了jdk1.8的 default方法修饰，不强制实现所有的方法，可以根据实际实现相关的方法。
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(MyWebAppConfigurer.class);

	@Autowired
	private MyInterceptor1 myInterceptor1;

	@Autowired
	private MyInterceptor2 myInterceptor2;

	// 拦截器配置
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(myInterceptor1).addPathPatterns("/book/**");
		registry.addInterceptor(myInterceptor2).addPathPatterns("/book/list");
	}

	// 静态资源路径配置
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/static/**")){
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}

		/*
		// 访问myres根目录下的fengjing.jpg 的URL为 http://localhost:8080/myres/fengjing.jpg 不影响Spring Boot的默认的 /** 映射，可以同时使用。
		registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
		// 访问myres根目录下的fengjing.jpg 的URL为 http://localhost:8080/fengjing.jpg （/** 会覆盖系统默认的配置）
		// registry.addResourceHandler("/**").addResourceLocations("classpath:/myres/").addResourceLocations("classpath:/static/");
		
		// 可以直接使用addResourceLocations 指定磁盘绝对路径，同样可以配置多个位置，注意路径写法需要加上file:
		registry.addResourceHandler("/myimgs/**").addResourceLocations("file:H:/myimgs/");
		addResourceHandlers(registry);
		*/
	}

	// 设置允许跨域的路径
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// registry.addMapping("/**")：为根目录的全部请求，也可以设置为"/demo/**"，这意味着是demo目录下的所有请求。
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("http://127.0.0.1:3000");
	}

	// 使用阿里 FastJson 作为JSON MessageConverter
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		logger.info("正在执行configureMessageConverters(), 使用阿里 FastJson 作为JSON MessageConverter.");
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.WriteMapNullValue); // 保留空的字段

		//SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
		//SerializerFeature.WriteNullNumberAsZero//Number null -> 0

		// 按需配置，更多参考FastJson文档
		converter.setFastJsonConfig(config);
		converter.setDefaultCharset(Charset.forName("UTF-8"));
		converters.add(converter);
	}

}
