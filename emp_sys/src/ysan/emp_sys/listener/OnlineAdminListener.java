package ysan.emp_sys.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ysan.emp_sys.entity.Admin;

@WebListener
public class OnlineAdminListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
		ServletContext sc = sce.getServletContext();
		Object obj = sc.getAttribute("onlineList");
		if (obj != null) {
			sc.removeAttribute("onlineList");
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		// 创建集合，存放在线用户
		List<Admin> onlineList = new ArrayList<>();
		sce.getServletContext().setAttribute("onlineList", onlineList);
	}

}
