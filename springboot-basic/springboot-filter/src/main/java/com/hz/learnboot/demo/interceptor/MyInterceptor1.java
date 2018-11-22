package com.hz.learnboot.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器1
 *
 * HandlerInterceptor 的功能跟Servlet开发中的过滤器Filter类似，但是提供更精细的的控制能力：
 * 在request被响应之前、request被响应之后、视图渲染之前以及request全部结束之后。我们不能通过拦截器修改request内容，但是可以通过抛出异常（或者返回false）来暂停request的执行。
 *
 * 常见应用场景
 * 1、权限检查：如检测请求是否具有登录权限，如果没有直接返回到登陆页面。
 * 2、性能监控：用请求处理前和请求处理后的时间差计算整个请求响应完成所消耗的时间。
 * 3、日志记录：可以记录请求信息的日志，以便进行信息监控、信息统计等。
 *
 */
@Component
public class MyInterceptor1 implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MyInterceptor1.class);

	/*
	实现 HandlerInterceptor 的拦截器有：
	ConversionServiceExposingInterceptor
	CorsInterceptor
	LocaleChangeInterceptor
	PathExposingHandlerInterceptor
	ResourceUrlProviderExposingInterceptor
	ThemeChangeInterceptor
	UriTemplateVariablesHandlerInterceptor
	UserRoleAuthorizationInterceptor

	其中 LocaleChangeInterceptor 和 ThemeChangeInterceptor 比较常用。

	配置拦截器也很简单，Spring 为什么提供了基础类WebMvcConfigurerAdapter ，我们只需要重写addInterceptors 方法添加注册拦截器。
	实现自定义拦截器只需要3步：
		1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口。
		2、创建一个Java类继承WebMvcConfigurerAdapter，并重写 addInterceptors 方法。
		2、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。

	最后强调一点：只有经过DispatcherServlet 的请求，才会走拦截器链，我们自定义的Servlet 请求是不会被拦截的，比如我们自定义的Servlet地址http://localhost:8080/myServlet1 是不会被拦截器拦截的。并且不管是属于哪个Servlet 只要复合过滤器的过滤规则，过滤器都会拦截。
	*/

	/*
	执行顺序
	1、单个实现类的执行顺序
	preHandler -> Controller -> postHandler -> model渲染-> afterCompletion

	2、多个实现类的执行顺序
	———————preHandler1——————-
	———————preHandler2——————-
	———————preHandler3——————-
	———————–Controller———————
	———————postHandler3——————
	———————postHandler2——————
	———————postHandler1——————
	———————postHandler1——————
	——————afterCompletion3—————-
	——————afterCompletion2—————-
	——————afterCompletion1—————-
	*/

	/* 该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller，当返回值为true 时就会继续调用下一个Interceptor的preHandle 方法，如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法；*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
		return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	}

	/* 该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
	}

	/* 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。用于进行资源清理。*/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}

}
