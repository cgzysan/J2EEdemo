package ysan.emp_sys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysan.emp_sys.entity.Employee;
import ysan.emp_sys.service.IEmployeeService;
import ysan.emp_sys.service.impl.EmployeeService;
import ysan.emp_sys.utils.PageBean;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IEmployeeService employeeService = new EmployeeService();
	private String uri;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入IndexServlet");
		try {
			// 获取”当前页“参数；（第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1";
			}
			// 转换
			int curPage = Integer.parseInt(currPage);
			
			// 创建PageBean对象，设置当前页参数
			PageBean<Employee> pageBean = new PageBean<Employee>();
			pageBean.setCurrentPage(curPage);
			employeeService.getAllEmployeeForPage(pageBean);
			
			//保存PageBean对象到request域中
			request.setAttribute("pageBean", pageBean);
			
			// 进入首页
			uri = "/index.jsp";
		} catch (Exception e) {
			// 测试用
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
