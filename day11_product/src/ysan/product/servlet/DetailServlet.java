package ysan.product.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.ha.backend.CollectedInfo;

import ysan.product.dao.ProductDao;
import ysan.product.entity.Product;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
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
		// 1. 获取商品编号
		String id = request.getParameter("id");
		// 2. 根据id从数据库获取商品数据
		ProductDao dao = new ProductDao();
		Product product = dao.findById(id);
		// 3.显示在浏览器
		PrintWriter writer = response.getWriter();
		String html = "";

		html += "<html>";
		html += "<head>";
		html += "<title>显示商品详细</title>";
		html += "</head>";
		html += "<body>";
		html += "<table border='1' align='center' width='400px'>";
		if (product != null) {
			html += "<tr>";
			html += "<th>编号:</th><td>" + product.getId() + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<th>商品名称:</th><td>" + product.getProName() + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<th>商品型号:</th><td>" + product.getProType() + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<th>商品价格:</th><td>" + product.getPrice() + "</td>";
			html += "</tr>";
		}

		html += "</table>";
		html += "<center><a href='" + request.getContextPath() + "/ListServlet'>[返回列表]</a></center>";
		html += "</body>";
		html += "</html>";

		writer.write(html);

		// 创建 cookie
		Cookie cookie = new Cookie("prodhist", createValue(request, id));
		cookie.setMaxAge(1 * 30 * 24 * 60 * 60);
		response.addCookie(cookie);
	}


	/**
	 * 生成cookie的值
	 * 分析：
	 * 			当前cookie值                     传入商品id               最终cookie值
	 *      null或没有prodHist          1                     1    （算法： 直接返回传入的id ）
	 *             1                  2                     2,1 （没有重复且小于3个。算法：直接把传入的id放最前面 ）
	 *             2,1                1                     1,2（有重复且小于3个。算法：去除重复id，把传入的id放最前面 ）
	 *             3,2,1              2                     2,3,1（有重复且3个。算法：去除重复id，把传入的id放最前面）
	 *             3,2,1              4                     4,3,2（没有重复且3个。算法：去最后的id，把传入的id放最前面）
	 * @return
	 */
	private String createValue(HttpServletRequest request, String id) {
		Cookie[] cookies = request.getCookies();
		String prodhist = null;
		if (null != cookies) {
			System.out.println("1111");
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("prodhist")) {
					System.out.println("2222");
					prodhist = cookie.getValue();
					break;
				}
			}
		}
		System.out.println("3333");
		// null 或者没有prodhist
		if (null == cookies || null == prodhist) {
			System.out.println("4444");
			return id;
		}
		// String -> String[] -> Collection :为了方便判断重复id
		String[] ids = prodhist.split("#");
		Collection colls = Arrays.asList(ids);
		// LinkedList 方便地操作（增删改元素）集合
		// Collection -> LinkedList
		LinkedList<String> list = new LinkedList<>(colls);

		// 不超过3个
		if (list.size() < 3) {
			// id重复
			System.out.println("小于3个");
			if (list.contains(id)) {
				list.remove(id);
				list.addFirst(id);
			} else {
				// 直接把传入的id放最前面
				list.addFirst(id);
			}
		} else {
			// 等于3个
			// id重复
			if (list.contains(id)) {
				list.remove(id);
				list.addFirst(id);
			} else {
				// 去最后的id，把传入的id放最前面
				list.removeLast();
				list.addFirst(id);
			}
		}

		StringBuffer sb = new StringBuffer();
		System.out.println("拼接字符串的长度 = " + list.size());
		for (String str : list) {
			sb.append(str + "#");
		}
		String result = sb.toString();
		result = result.substring(0, result.length() - 1);
		System.out.println("str = " + result);
		return result;
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
