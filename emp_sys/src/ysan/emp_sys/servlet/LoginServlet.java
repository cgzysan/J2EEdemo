package ysan.emp_sys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ysan.emp_sys.entity.Admin;
import ysan.emp_sys.service.IAdminService;
import ysan.emp_sys.service.impl.AdminService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IAdminService adminService = new AdminService();
	private String uri;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("进入了LoginServlet");
		
		String method = request.getMethod();
		if ("POST".equals(method)) {
			login(request, response);
		} else if ("GET".equals(method)) {
			out(request, response);
		}
	}

	private void out(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		// 跳转登录
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取参数
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		Admin admin = new Admin();
		admin.setUserName(userName);
		admin.setPwd(pwd);
		
		try {
			//2.调用service处理业务
			Admin loginInfo = adminService.findByNameAndPwd(admin);
			if (null == loginInfo) {
				// 登录失败
				uri = "/login.jsp";
			} else {
				// 登录成功
				// 先保存数据到session
				request.getSession().setAttribute("loginInfo", loginInfo);
				System.out.println("loginInfo id = " + loginInfo.getId() + ", Name = " + loginInfo.getUserName());
				//【在线列表： 1. 先从servletContext中拿到在线列表集合;  (onLineUserList)
				//           2. 当前用户放入“在线列表集合中”】
				// 实现1：先得到servletContext对象
				ServletContext sc = getServletContext();
				// 实现2： 再获取在线列表集合
				List<Admin> onlineList = (List<Admin>) sc.getAttribute("onlineList");
				// 判断
				if (onlineList != null){
					System.out.println("添加在线用户 name = " + loginInfo.getUserName());
					// 实现3: 添加当前登陆者
					onlineList.add(loginInfo);
					//sc.setAttribute("onlineList", onlineList);  // 对象引用传递,不需要写也可以
				}
				// 再跳转到首页显示servlet
				uri = "/index";
			}
		} catch (Exception e) {
			// 测试
			e.printStackTrace();
			// 错误
			uri = "/error/error.jsp";
		}
		// 跳转
		request.getRequestDispatcher(uri).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
