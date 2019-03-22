package ysan.hotel_sys.dao;

import java.util.List;

import ysan.hotel_sys.entity.FoodType;

public interface IFoodTypeDao {
	
	void add(FoodType foodtype);
	
	void delete(int id);
	
	void update(FoodType foodtype);
	
	List<FoodType> query();

	FoodType findById(int id);

	List<FoodType> query(String keyword);
	
	Integer getFirstType();
}
