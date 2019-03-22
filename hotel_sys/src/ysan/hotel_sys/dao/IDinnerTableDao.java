package ysan.hotel_sys.dao;

import java.util.List;

import ysan.hotel_sys.entity.DinnerTable;

/**
 * 
 * @author ysan
 * 餐桌操作接口
 *
 */
public interface IDinnerTableDao {
	
	void add(DinnerTable dt);
	
	void delete(int id);

	void update(DinnerTable dt);
	
	List<DinnerTable> query();
	
	DinnerTable findById(int id);
	
	List<DinnerTable> query(String key);
	
	void quitTable(int id);
}
