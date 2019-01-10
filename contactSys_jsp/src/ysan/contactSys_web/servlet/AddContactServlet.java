package ysan.contactSys_web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysan.contactSys_web.dao.ContactDao;
import ysan.contactSys_web.dao.impl.ContactDaoImpl;
import ysan.contactSys_web.entity.Contact;
import ysan.contactSys_web.exception.NameRepeatException;
import ysan.contactSys_web.service.ContactService;
import ysan.contactSys_web.service.impl.ContactServiceImpl;

/**
 * Servlet implementation class AddContactServlet
 */
@WebServlet("/AddContactServlet")
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.接收参数
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		
		//封装成Contact对象
		Contact contact = new Contact();
		contact.setName(name);
		contact.setGender(gender);
		contact.setAge(Integer.parseInt(age));
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setQq(qq);
		
		//2.调用dao类的添加联系人的方法
		ContactService dao = new ContactServiceImpl();
		try {
			dao.addContact(contact);
		} catch (NameRepeatException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/addContact.jsp").forward(request, response);
			return;
		}
		
		//3.跳转到查询联系人的页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
