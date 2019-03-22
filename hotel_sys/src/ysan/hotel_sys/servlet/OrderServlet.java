package ysan.hotel_sys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysan.hotel_sys.entity.Food;
import ysan.hotel_sys.entity.FoodType;
import ysan.hotel_sys.entity.OrderDetail;
import ysan.hotel_sys.entity.Orders;
import ysan.hotel_sys.util.PageBean;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	List<FoodType> typeList = foodTypeService.query();
    	List<Food> foodList = foodService.query();
    	config.getServletContext().setAttribute("foodtypes", typeList);
    	config.getServletContext().setAttribute("food", foodList);
    }
    
    private Object getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String currPage = request.getParameter("currentPage");
    	
    	if (currPage == null || "".equals(currPage.trim())) {
    		currPage = "1";
    	}
    	
    	int currentPage = Integer.parseInt(currPage);
    	
    	PageBean<Orders> pageBean = new PageBean<>();
    	pageBean.setCurrentPage(currentPage);
    	pageBean.setPageCount(6);
    	
    	ordersService.getAll(pageBean);
    	request.setAttribute("pageBean", pageBean);
    	
    	return request.getRequestDispatcher("/sys/order/orderList.jsp");
    }
    
    private Object getOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("orderId");
		List<OrderDetail> list = null;
		if (id != null && !id.isEmpty()) {
			list = orderDetailService.findByOrderId(Integer.parseInt(id));
		}

		request.setAttribute("orderDetail", list);
		
		return request.getRequestDispatcher("/sys/order/orderDetail.jsp");
    }
}
