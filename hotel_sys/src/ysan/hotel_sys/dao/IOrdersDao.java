package ysan.hotel_sys.dao;

import java.util.List;

import ysan.hotel_sys.entity.Orders;
import ysan.hotel_sys.util.PageBean;


public interface IOrdersDao {

	void update(Orders orders);
	
	List<Orders> query();

	void add(Orders orders);
	
	int getCount();

	void getAll(PageBean<Orders> pb);

	int getTotalCount();
}
