package ysan.hotel_sys.dao;

import java.util.List;

import ysan.hotel_sys.entity.OrderDetail;


public interface IOrderDetailDao {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderid(int id);
}
