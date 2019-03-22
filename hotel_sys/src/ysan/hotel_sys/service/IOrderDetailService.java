package ysan.hotel_sys.service;

import java.util.List;

import ysan.hotel_sys.entity.OrderDetail;


public interface IOrderDetailService {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderId(int id);
}
