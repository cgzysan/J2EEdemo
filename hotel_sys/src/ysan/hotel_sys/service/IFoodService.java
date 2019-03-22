package ysan.hotel_sys.service;

import java.util.List;

import ysan.hotel_sys.entity.Food;
import ysan.hotel_sys.util.PageBean;


public interface IFoodService {
	
void add(Food food);
	
	void delete(int id);
	
	void update(Food food);
	
	List<Food> query();

	Food findById(int id);

	List<Food> query(String keyword);
	
	public void getAll(PageBean<Food> pb);
	
	List<Food> findByType(int type);
}
