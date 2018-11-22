package com.hz.learnboot.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用@WebListener注解，实现ServletContextListener接口
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("MyServletContextListener2通过注解实现的监听器 - ServletContext 初始化...");
		logger.info("Tomcat版本： " + sce.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("MyServletContextListener2通过注解实现的监听器 - ServletContext 被销毁...");
	}

}
