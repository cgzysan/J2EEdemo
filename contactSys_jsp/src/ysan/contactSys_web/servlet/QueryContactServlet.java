package ysan.contactSys_web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysan.contactSys_web.dao.ContactDao;
import ysan.contactSys_web.dao.impl.ContactDaoImpl;
import ysan.contactSys_web.entity.Contact;
import ysan.contactSys_web.service.ContactService;
import ysan.contactSys_web.service.impl.ContactServiceImpl;

/**
 * Servlet implementation class QueryContactServlet
 */
@WebServlet("/QueryContactServlet")
public class QueryContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryContactServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 获取查询用户id
		String id = request.getParameter("id");
		//2. 根据id查询用户数据
		ContactService dao = new ContactServiceImpl();
		Contact contact = dao.findById(id);
		//3. 把结果保存到域对象中
		request.setAttribute("contact", contact);
		//4. 转发跳转到修改联系人的页面
		request.getRequestDispatcher("/updateContact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
