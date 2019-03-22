package ysan.hotel_sys.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ysan.hotel_sys.dao.IDinnerTableDao;
import ysan.hotel_sys.entity.DinnerTable;
import ysan.hotel_sys.util.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao {

	private QueryRunner qr = JdbcUtils.getQueryRunner();

	@Override
	public void add(DinnerTable dt) {
		String sql = "INSERT INTO dinnerTable (tableName) VALUES(?)";
		try {
			qr.update(sql, dt.getTableName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM dinnerTable WHERE id=?";
		try {
			qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(DinnerTable dt) {
		String sql = "UPDATE dinnertable SET tableStatus=?, orderDate=? WHERE id=?";
		Date date = dt.getOrderDate();
		try {
			qr.update(sql, dt.getTableStatus(), date, dt.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> query() {
		String sql = "SELECT * FROM dinnerTable";
		try {
			return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public DinnerTable findById(int id) {
		String sql = "SELECT * FROM dinnerTable WHERE id=?";
		try {
			return qr.query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> query(String key) {
		String sql = "SELECT * FROM dinnerTable WHERE tableName LIKE ?";
		try {
			return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), "%" + key + "%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void quitTable(int id) {
		String sql = "UPDATE dinnertable SET tableStatus=?,orderDate=? WHERE id=?";

		try {
			qr.update(sql, 0, null, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
