package ysan.hotel_sys.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ysan.hotel_sys.dao.IOrderDetailDao;
import ysan.hotel_sys.entity.OrderDetail;
import ysan.hotel_sys.util.JdbcUtils;


public class OrderDetailDao implements IOrderDetailDao {

	private QueryRunner qr = JdbcUtils.getQueryRunner();
	@Override
	public void add(OrderDetail od) {
		String sql =" INSERT orderdetail(orderId,food_id,foodCount) VALUES(?,?,?)";
		try {
			qr.update(sql,od.getOrderId(),od.getFood_id(),od.getFoodCount());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<OrderDetail> query() {
		try {
			String sql ="SELECT * FROM orderdetail";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<OrderDetail> findByOrderid(int id) {
		try {
			String sql ="SELECT * FROM orderdetail where orderId=?";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
