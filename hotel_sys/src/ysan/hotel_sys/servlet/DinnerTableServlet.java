package ysan.hotel_sys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysan.hotel_sys.entity.DinnerTable;
import ysan.hotel_sys.factory.BeanFactory;
import ysan.hotel_sys.service.IDinnerTableService;

/**
 * Servlet implementation class DinnerTableServlet
 */
@WebServlet("/table")
public class DinnerTableServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinnerTableServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Object add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tableName");
		System.out.println("餐桌名字：" + tableName);
		if (tableName != null) {
			DinnerTable dt = new DinnerTable();
			dt.setTableName(tableName);
			tableService.add(dt);
			return list(request, response);
		}
		return null;
	}
	
	private Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DinnerTable> list = tableService.query();
		request.setAttribute("list", list);
		return request.getRequestDispatcher("/sys/board/boardList.jsp");
	}
	
	private Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		tableService.changeState(Integer.parseInt(id));
		return list(request, response);
	}
	
	private Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		tableService.delete(Integer.parseInt(id));
		return list(request, response);
	}
	
	private Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		System.out.println("获取到的关键字：" + keyword);
		Object uri = null;
		if (keyword != null) {
			List<DinnerTable> list = tableService.query(keyword);
			request.setAttribute("list", list);
			uri = request.getRequestDispatcher("/sys/board/boardList.jsp");
		}
		return uri;
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
