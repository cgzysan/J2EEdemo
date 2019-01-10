package ysan.product.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysan.product.dao.ProductDao;
import ysan.product.entity.Product;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
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
		// 1.读取数据库，查询商品列表
		ProductDao dao = new ProductDao();
		List<Product> list = dao.findAll();

		// 2.展示到浏览器
		PrintWriter writer = response.getWriter();
		String html = "";

		html += "<html>";
		html += "<head>";
		html += "<title>显示商品列表</title>";
		html += "</head>";
		html += "<body>";
		html += "<table border='1' align='center' width='500px'>";
		html += "<tr>";
		html += "<th>编号</th><th>商品名称</th><th>商品型号</th><th>商品价格</th>";
		html += "</tr>";
		// 2.1 遍历数据添加
		if (null != list) {
			for (Product p : list) {
				html += "<tr>";
				html += "<td>" + p.getId() + "</td><td><a href='" + request.getContextPath() + "/DetailServlet?id="
						+ p.getId() + "'>" + p.getProName() + "</a></td><td>" + p.getProType() + "</td><td>"
						+ p.getPrice() + "</td>";
				html += "</tr>";
			}
		}

		html += "</table>";
		
		// 3. 显示浏览过的商品
		html += "最近浏览过的商品：<br/>";
		// 3.1 取出prodhist的cookie
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("prodhist")) {
					String prodhist = cookie.getValue();
					System.out.println("收到的值 = "+ prodhist);
					String[] ids = prodhist.split("#");
					// 遍历历史商品id
					for (String id : ids) {
						Product p = dao.findById(id);
						html += ""+p.getId()+"&nbsp;"+p.getProName()+"&nbsp;"+p.getPrice()+"<br/>";
					}
				}
			}
		}
		html += "</body>";
		html += "</html>";

		writer.write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
