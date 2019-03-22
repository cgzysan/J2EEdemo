package ysan.hotel_sys.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.sun.net.httpserver.Filter;
import com.sun.xml.internal.ws.client.RequestContext;

import ysan.hotel_sys.entity.Food;
import ysan.hotel_sys.entity.FoodType;
import ysan.hotel_sys.util.PageBean;

/**
 * Servlet implementation class FoodServlet
 */
@WebServlet("/food")
public class FoodServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currPage = request.getParameter("currentPage");
		if (currPage == null || "".equals(currPage.trim())) {
			currPage = "1";
		}
		int currentPage = Integer.parseInt(currPage);

		PageBean<Food> pageBean = new PageBean<Food>();
		pageBean.setCurrentPage(currentPage);

		// 获取所有数据
		foodService.getAll(pageBean);

		List<Food> list = pageBean.getPageData();

		List<FoodType> types = new ArrayList<>();

		if (list != null) {
			for (Food food : list) {
				FoodType foodtype = foodTypeService.findById(food.getFoodType_id());
				types.add(foodtype);
			}
		}

		request.setAttribute("types", types);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("list", list);

		return request.getRequestDispatcher("/sys/food/foodList.jsp");
	}

	private Object add(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(10 * 1024 * 1024);
		upload.setSizeMax(50 * 1024 * 1024);
		upload.setHeaderEncoding("UTF-8");

		if (ServletFileUpload.isMultipartContent(request)) {
			Food food = new Food();
			ServletRequestContext ctx = new ServletRequestContext(request);
			List<FileItem> list = upload.parseRequest(ctx);

			for (FileItem fileItem : list) {
				// 普通文件内容
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString();
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					BeanUtils.setProperty(food, name, value);
				}
				// 上传内容
				else {
					String fieldName = fileItem.getFieldName();
					String path = getServletContext().getRealPath("/upload");
					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					// a. 获取文件名称
					String name = fileItem.getName();
					BeanUtils.setProperty(food, fieldName, "upload/" + name);
					// b. 上传
					File file = new File(path, name);
					if (!file.isDirectory()) {
						fileItem.write(file);
					}
					fileItem.delete(); // 删除组件运行时产生的临时文件
				}
			}
			foodService.add(food);
		}
		return list(request, response);
	}
	
	private Object findFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		List<FoodType> foodtypes = foodTypeService.query();
		request.setAttribute("foodtypes", foodtypes);
		return request.getRequestDispatcher("/sys/food/saveFood.jsp");
	}
	
	private Object show(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String id = request.getParameter("id");
		System.out.println("需要寻找的id = " + id);
		Food food = foodService.findById(Integer.parseInt(id));
		
		List<FoodType> foodtypes = foodTypeService.query();
		request.setAttribute("foodtypes", foodtypes);
		
		request.setAttribute("food", food);
		int foodType_id = food.getFoodType_id();
		
		FoodType type = foodTypeService.findById(foodType_id);
		request.setAttribute("type", type);
		
		return request.getRequestDispatcher("/sys/food/updateFood.jsp");
	}
	
	private Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(10 * 1024 * 1024);
		upload.setSizeMax(50 * 1024 * 1024);
		upload.setHeaderEncoding("UTF-8");
		
		if (ServletFileUpload.isMultipartContent(request) ) {
			Food food = new Food();
			ServletRequestContext ctx = new ServletRequestContext(request);
			List<FileItem> list = upload.parseRequest(ctx);
			
			for (FileItem fileItem : list) {
				// 普通文本内容
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString();
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					BeanUtils.setProperty(food, name, value);
				}
				// 上传内容
				else {
					String fieldName = fileItem.getFieldName();
					String path = getServletContext().getRealPath("/upload");
					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					String name = fileItem.getName();
					if (name != null && !"".equals(name.trim())) {
						BeanUtils.setProperty(food, fieldName, ("upload/" + name));
						
						File file = new File(path, name);
						if (!file.isDirectory()) {
							fileItem.write(file);
						}
						fileItem.delete();// 删除组件运行时产生的临时文件
					} else {
						int id = food.getId();
						String img = foodService.findById(id).getImg();
						BeanUtils.setProperty(food, "img", img);
					}
				}
			}
			foodService.update(food);
		}
		return list(request, response);
	}
}
