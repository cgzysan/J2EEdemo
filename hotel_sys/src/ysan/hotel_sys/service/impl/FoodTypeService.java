package ysan.hotel_sys.service.impl;

import java.util.List;

import ysan.hotel_sys.dao.IFoodTypeDao;
import ysan.hotel_sys.entity.FoodType;
import ysan.hotel_sys.factory.BeanFactory;
import ysan.hotel_sys.service.IFoodTypeService;


public class FoodTypeService implements IFoodTypeService{

	IFoodTypeDao dao = BeanFactory.getInstance("foodTypeDao", IFoodTypeDao.class) ;
	
	@Override
	public void add(FoodType foodtype) {
		dao.add(foodtype);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void update(FoodType foodtype) {
		dao.update(foodtype);
	}

	@Override
	public List<FoodType> query() {
		return dao.query();
	}

	@Override
	public FoodType findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<FoodType> query(String keyword) {
		return dao.query(keyword);
	}

	@Override
	public Integer getFirstType() {
		return dao.getFirstType();
	}
}
