package ysan.hotel_sys.service.impl;

import java.util.List;

import ysan.hotel_sys.dao.IOrderDetailDao;
import ysan.hotel_sys.entity.OrderDetail;
import ysan.hotel_sys.factory.BeanFactory;
import ysan.hotel_sys.service.IOrderDetailService;


public class OrderDetailService implements IOrderDetailService{

	IOrderDetailDao dao = BeanFactory.getInstance("orderDetailDao", IOrderDetailDao.class);

	@Override
	public void add(OrderDetail od) {
		dao.add(od);
	}

	@Override
	public List<OrderDetail> query() {
		return dao.query();
	}
	@Override
	public List<OrderDetail> findByOrderId(int id) {
		return dao.findByOrderid(id);
	}
}
