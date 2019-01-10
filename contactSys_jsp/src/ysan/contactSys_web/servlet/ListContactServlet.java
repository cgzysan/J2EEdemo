package ysan.contactSys_web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class ListContactServlet
 */
@WebServlet("/ListContactServlet")
public class ListContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListContactServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.从xml读取出联系人数据
		ContactService dao = new ContactServiceImpl();
		List<Contact> list = dao.findAll();
		// 2.把结果保存到域对象中
		request.setAttribute("contacts", list);
		// 3.转发跳转到jsp页面
		request.getRequestDispatcher("/listContact.jsp").forward(request, response);
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
