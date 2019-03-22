package ysan.hotel_sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ysan.hotel_sys.dao.IFoodDao;
import ysan.hotel_sys.entity.Food;
import ysan.hotel_sys.util.Condition;
import ysan.hotel_sys.util.JdbcUtils;
import ysan.hotel_sys.util.PageBean;

public class FoodDao implements IFoodDao {
	private QueryRunner qr = JdbcUtils.getQueryRunner();

	@Override
	public void add(Food food) {
		String sql = "INSERT INTO food (foodName, foodType_id, price, mprice, remark, img) VALUES (?,?,?,?,?,?)";
		System.out.println("type id = " + food.getFoodType_id());
		try {
			qr.update(sql, food.getFoodName(), food.getFoodType_id(), food.getPrice(), food.getMprice(),
					food.getRemark(), food.getImg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			String sql = "DELETE FROM food WHERE id=?";
			qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Food food) {
		try {
			String sql = "UPDATE food SET foodName=?,foodType_id=?,price=?,mprice=?,remark=?,img=? WHERE id =?";
			qr.update(sql, food.getFoodName(), food.getFoodType_id(), food.getPrice(), food.getMprice(),
					food.getRemark(), food.getImg(), food.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> query() {
		String sql = "SELECT * FROM food";
		try {
			return qr.query(sql, new BeanListHandler<Food>(Food.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Food findById(int id) {
		try {
			String sql = "SELECT * FROM food where id =?";
			return qr.query(sql, new BeanHandler<Food>(Food.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> query(String keyword) {
		try {
			String sql = "SELECT * FROM food WHERE foodName LIKE ?";
			return qr.query(sql, new BeanListHandler<Food>(Food.class), "%" + keyword + "%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> findByType(int type) {
		try {
			// 根据食物类型找到食物
			String sql = "SELECT * FROM food WHERE foodType_id =?";
			return qr.query(sql, new BeanListHandler<Food>(Food.class), type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAll(PageBean<Food> pb) {
		int totalCount = this.getTotalCount(pb);
		pb.setTotalCount(totalCount);

		List<Object> list = new ArrayList();

		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage());
		}

		int currentPage = pb.getCurrentPage();
		int count = pb.getPageCount();
		int index = (currentPage - 1) * count;

		Condition condition = pb.getCondition();

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		sb.append(" f.id,");
		sb.append(" f.foodName,");
		sb.append(" f.foodType_id,");
		sb.append(" f.price,");
		sb.append(" f.mprice,");
		sb.append(" f.remark,");
		sb.append(" f.img,");
		sb.append(" ft.typeName");
		sb.append(" FROM");
		sb.append(" food f,");
		sb.append(" foodType ft");
		sb.append(" WHERE 1=1");
		sb.append(" AND f.foodType_id=ft.id");

		if (condition != null) {
			String foodName = condition.getFoodName();
			if (foodName != null && !foodName.isEmpty()) {
				sb.append(" ADD f.foodName LIKE ?");
				list.add(foodName);
			}

			int type_id = condition.getFoodType_id();
			if (type_id > 0) {
				sb.append(" ADD f.foodType=?");
				list.add(type_id);
			}
		}

		sb.append(" limit ?,?");
		list.add(index);
		list.add(count);

		try {
			if (index >= 0) {
				List<Food> pageData = qr.query(sb.toString(), new BeanListHandler<Food>(Food.class), list.toArray());
				pb.setPageData(pageData);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalCount(PageBean<Food> pb) {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<>();
		sb.append(" SELECT");
		sb.append(" count(*)");
		sb.append(" FROM");
		sb.append(" food f,");
		sb.append(" foodType ft");
		sb.append(" WHERE 1=1");
		sb.append(" AND f.foodType_id=ft.id");

		Condition condition = pb.getCondition();
		if (condition != null) {
			String foodName = condition.getFoodName();
			if (foodName != null && !foodName.isEmpty()) {
				sb.append(" ADD f.foodName LIKE ?");
				list.add("%" + foodName + "%");
			}

			int type_id = condition.getFoodType_id();
			if (type_id > 0) {
				sb.append(" ADD f.foodType_id = ?");
				list.add(type_id);
			}
		}

		try {
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>(), list.toArray());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
