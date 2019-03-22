package ysan.hotel_sys.service.impl;

import java.util.List;

import ysan.hotel_sys.dao.IFoodDao;
import ysan.hotel_sys.entity.Food;
import ysan.hotel_sys.factory.BeanFactory;
import ysan.hotel_sys.service.IFoodService;
import ysan.hotel_sys.util.PageBean;


public class FoodService implements IFoodService{
	IFoodDao dao = BeanFactory.getInstance("foodDao", IFoodDao.class);
	
	@Override
	public void add(Food food) {
		dao.add(food);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
		
	}

	@Override
	public void update(Food food) {
		dao.update(food);
		
	}

	@Override
	public List<Food> query() {
		return dao.query();
	}

	@Override
	public Food findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Food> query(String keyword) {
		return dao.query(keyword);
	}
	
	@Override
	public void getAll(PageBean<Food> pb) {
		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Food> findByType(int type) {
		return dao.findByType(type);
	}
}
