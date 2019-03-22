package ysan.hotel_sys.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ysan.hotel_sys.dao.IFoodTypeDao;
import ysan.hotel_sys.entity.FoodType;
import ysan.hotel_sys.util.JdbcUtils;

public class FoodTypeDao implements IFoodTypeDao {
	private QueryRunner qr = JdbcUtils.getQueryRunner();

	@Override
	public void add(FoodType foodtype) {
		String sql = "INSERT INTO foodType (typeName) VALUES(?)";
		try {
			qr.update(sql, foodtype.getTypeName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM foodType WHERE id=?";
		try {
			qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(FoodType foodtype) {
		String sql = "UPDATE foodType SET typeName=? WHERE id=?";
		try {
			qr.update(sql, foodtype.getTypeName(), foodtype.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> query() {
		String sql = "SELECT * FROM foodType";
		try {
			return qr.query(sql, new BeanListHandler<FoodType>(FoodType.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public FoodType findById(int id) {
		String sql = "SELECT * FROM foodType WHERE id=?";
		try {
			return qr.query(sql, new BeanHandler<FoodType>(FoodType.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> query(String keyword) {
		String sql = "SELECT * FROM foodType WHERE typeName LIKE ?";
		try {
			return qr.query(sql, new BeanListHandler<FoodType>(FoodType.class), "%" + keyword + "%");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Integer getFirstType() {
		String sql = "SELECT * FROM foodType";
		try {
			return qr.query(sql, new ScalarHandler<Integer>());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
