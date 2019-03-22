package ysan.hotel_sys.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysan.hotel_sys.factory.BeanFactory;
import ysan.hotel_sys.service.IDinnerTableService;
import ysan.hotel_sys.service.IFoodService;
import ysan.hotel_sys.service.IFoodTypeService;
import ysan.hotel_sys.service.IOrderDetailService;
import ysan.hotel_sys.service.IOrdersService;
import ysan.hotel_sys.service.impl.FoodService;
import ysan.hotel_sys.util.WebUtils;

public abstract class BaseServlet extends HttpServlet {
	
	// 创建Service
	protected IDinnerTableService tableService = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	protected IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	protected IFoodService foodService = BeanFactory.getInstance("foodService", FoodService.class);
	protected IOrdersService ordersService = BeanFactory.getInstance("ordersService",
			IOrdersService.class);
	protected IOrderDetailService orderDetailService = BeanFactory.getInstance("orderDetailService",
			IOrderDetailService.class);

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// （保存跳转的资源） 方法返回值
		Object returnValue = null;
		
		// 获取操作类型;  【约定 > 俗成： 操作类型的值，必须对应servlet中的方法名称】
		String methodName = request.getParameter("method");
		System.out.println("执行方法 ：" + methodName);
		try {
			// 1.获取当前运行类的字节码
			Class clazz = this.getClass();
			// 2.获取当前执行的方法的Method类型
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 3.执行方法
			returnValue = method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		}
		
		// 跳转
		WebUtils.goTo(request, response, returnValue);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
