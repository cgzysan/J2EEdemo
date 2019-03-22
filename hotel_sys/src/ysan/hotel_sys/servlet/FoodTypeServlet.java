package ysan.hotel_sys.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import ysan.hotel_sys.entity.FoodType;

/**
 * Servlet implementation class FoodTypeServlet
 */
@WebServlet("/foodType")
public class FoodTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoodTypeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodType> list = foodTypeService.query();
		request.setAttribute("list", list);
		return request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
	}

	private Object add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeName = request.getParameter("foodtype");
		if (typeName != null) {
			FoodType type = new FoodType();
			type.setTypeName(typeName);
			foodTypeService.add(type);
			return list(request, response);
		}
		return null;
	}

	private Object show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		FoodType foodType = foodTypeService.findById(Integer.parseInt(id));
		request.setAttribute("type", foodType);
		return request.getRequestDispatcher("/sys/foodtype/updateCuisine.jsp");
	}

	private Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		foodTypeService.delete(Integer.parseInt(id));
		return list(request, response);
	}

	private Object search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("keyword");
		System.out.println("搜索的关键字：" + key);
		if (key != null) {
			List<FoodType> list = foodTypeService.query(key);
			request.setAttribute("list", list);
			return request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
		}
		return null;
	}

	private Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		FoodType type = new FoodType();
		Map<String, String[]> map = request.getParameterMap();
		BeanUtils.populate(type, map);
		System.out.println(type);
		foodTypeService.update(type);
		return list(request, response);
	}

}
