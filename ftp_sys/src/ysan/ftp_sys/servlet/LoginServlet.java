package ysan.ftp_sys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ysan.ftp_sys.entity.Admin;
import ysan.ftp_sys.service.IAdminService;
import ysan.ftp_sys.service.impl.AdminService;


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
		login(request, response);
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
