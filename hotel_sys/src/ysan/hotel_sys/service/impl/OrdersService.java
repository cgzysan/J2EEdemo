package ysan.hotel_sys.service.impl;

import java.util.List;

import ysan.hotel_sys.dao.IOrdersDao;
import ysan.hotel_sys.entity.Orders;
import ysan.hotel_sys.factory.BeanFactory;
import ysan.hotel_sys.service.IOrdersService;
import ysan.hotel_sys.util.PageBean;


public class OrdersService implements IOrdersService{

	IOrdersDao dao = BeanFactory.getInstance("ordersDao", IOrdersDao.class);
	@Override
	public void update(Orders orders) {
		dao.update(orders);
	}

	@Override
	public List<Orders> query() {
		return dao.query();
	}

	@Override
	public void add(Orders orders) {
		dao.add(orders);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}
	
	@Override
	public void getAll(PageBean<Orders> pb) {
		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
