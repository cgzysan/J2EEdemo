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
		response.setContentType("text/html;charset=utf-8");
		ContactDao dao = new ContactDaoImpl();
		List<Contact> list = dao.findAll();

		PrintWriter writer = response.getWriter();
		String html = "";

		html += "<!DOCTYPE html>";
		html += "<html lang='en'>";
		html += "";
		html += "<head>";
		html += "    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>";
		html += "    <title>查询所有联系人</title>";
		html += "    <style type='text/css'>";
		html += "    table td {";
		html += "        /*文字居中*/";
		html += "        text-align: center;";
		html += "    }";
		html += "";
		html += "    table {";
		html += "        border-collapse: collapse;";
		html += "    }";
		html += "    </style>";
		html += "</head>";
		html += "";
		html += "<body>";
		html += "    <center><h3>查询所有联系人</h3>";
		html += "    </center>";
		html += "    <table align='center' border='1' width='900px'>";
		html += "        <tr>";
		html += "            <th>编号</th>";
		html += "            <th>姓名</th>";
		html += "            <th>性别</th>";
		html += "            <th>年龄</th>";
		html += "            <th>电话</th>";
		html += "            <th>邮箱</th>";
		html += "            <th>QQ</th>";
		html += "            <th>操作</th>";
		html += "        </tr>";
		if (null != list) {
			for (Contact contact : list) {
				html += "        <tr>";
				html += "            <td>" + contact.getId() + "</td>";
				html += "            <td>" + contact.getName() + "</td>";
				html += "            <td>" + contact.getGender() + "</td>";
				html += "            <td>" + contact.getAge() + "</td>";
				html += "            <td>" + contact.getPhone() + "</td>";
				html += "            <td>" + contact.getEmail() + "</td>";
				html += "            <td>" + contact.getQq() + "</td>";
				html += "            <td><a href='" + request.getContextPath() + "/QueryContactServlet?id="
						+ contact.getId() + "'>修改</a>&nbsp;<a href='" + request.getContextPath()
						+ "/DeleteContactServlet?id=" + contact.getId() + "'>删除</a></td>";
				html += "        </tr>";
			}
		}
		html += "        <tr>";
		html += "            <td colspan='8' align='center'><a href='" + request.getContextPath() + "/addContact.html"
				+ "'>[添加联系人]</a></td>";
		html += "        </tr>";
		html += "    </table>";
		html += "</body>";
		html += "";
		html += "</html>";
		
		writer.write(html);
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
