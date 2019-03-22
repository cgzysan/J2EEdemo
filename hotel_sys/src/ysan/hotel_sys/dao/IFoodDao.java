package ysan.hotel_sys.dao;

import java.util.List;

import ysan.hotel_sys.entity.Food;
import ysan.hotel_sys.util.PageBean;

public interface IFoodDao {

	void add(Food food);
	
	void delete(int id);
	
	void update(Food food);
	
	List<Food> query();

	Food findById(int id);

	List<Food> query(String keyword);
	
	List<Food> findByType(int type);
	/**
	 * 分页查询数据
	 */
	void getAll(PageBean<Food> pb);
	
	
	/**
	 * 查询总记录数
	 */

	int getTotalCount(PageBean<Food> pb);
}
