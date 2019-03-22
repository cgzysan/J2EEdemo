package ysan.hotel_sys.service;

import java.util.List;

import ysan.hotel_sys.entity.Orders;
import ysan.hotel_sys.util.PageBean;


public interface IOrdersService {
	
	void update(Orders orders);

	List<Orders> query();

	void add(Orders orders);
	
	int getCount();
	
	public void getAll(PageBean<Orders> pb);
}
