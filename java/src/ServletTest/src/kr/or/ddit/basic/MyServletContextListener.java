package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] contextDestroyed => " + sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] contextInitialized => " + sce);
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("[MyServletContextListener] attributeAdded => " + event.getName() + " 추가됨.");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("[MyServletContextListener] attributeRemoved => " + event.getName() + " 삭제됨.");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("[MyServletContextListener] attributeReplaced => " + event.getName() + " 변경됨.");
	}

}
