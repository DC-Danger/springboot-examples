package com.hz.learnboot.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 使用ServletListenerRegistrationBean注解，实现ServletContextListener接口
 */
public class MyServletContextListener2 implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(MyServletContextListener2.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("MyServletContextListener2通过SpringBean实现的监听器 - ServletContext 初始化...");
		logger.info("Tomcat版本： " + sce.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("MyServletContextListener2通过SpringBean实现的监听器 - ServletContext 被销毁...");
	}

}
