package ysan.emp_sys.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 分析：
 * 
	1. 先指定放行的资源，哪些资源不需要拦截：
	      login.jsp   +    /login  (request对象可以获取)
	2. 获取session，从session中获取登陆用户
	3. 判断是否为空：
	      为空， 说明没有登陆， 跳转到登陆
	       不为空， 已经登陆，放行！
 */
@WebFilter(urlPatterns = {"/index", "/index.jsp"}, filterName = "loginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("进入login过滤器");
		//0. 转换
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//1. 获取请求资源，截取
		String uri = req.getRequestURI(); // /emp_sys/login.jsp
		String requestPath = uri.substring(uri.lastIndexOf("/") + 1);
		
		//2. 判断，先放行一些资源
		if ("login".equals(requestPath) || "login.jsp".equals(requestPath)) {
			chain.doFilter(req, res);
		} else {
			HttpSession session = req.getSession(false);
			System.out.println(session);
			if (session != null) {
				Object obj = session.getAttribute("loginInfo");
				if (obj != null) {
					chain.doFilter(req, res);
					return;
				} else {
					uri = "/login.jsp";
				}
			} else {
				uri = "/login.jsp";
			}
			req.getRequestDispatcher(uri).forward(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
