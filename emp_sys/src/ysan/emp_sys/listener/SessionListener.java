package ysan.emp_sys.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ysan.emp_sys.entity.Admin;

@WebListener
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sessionDestroyed");
		//1. 获取Session对象，ServletContext对象
		HttpSession session = se.getSession();
		ServletContext sc = session.getServletContext();
		
		//2. 获取session中存储的当前登录用户
		Object obj = session.getAttribute("loginInfo");
		
		//3. 获取ServletContext中存储的在线用户列表集合
		List<Admin> list = (List<Admin>) sc.getAttribute("onlineList");
		if (obj != null) {
			//4. 把“当前登陆用户”从在线列表集合中移除
			list.remove(obj);
		}
	}
}
