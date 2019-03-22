package ysan.emp_sys.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import ysan.emp_sys.dao.IAdminDao;
import ysan.emp_sys.entity.Admin;
import ysan.emp_sys.utils.JdbcUtils;

public class AdminDao implements IAdminDao {

	@Override
	public Admin findByNameAndPwd(Admin admin) {
		try {
			String sql = "select * from admin where userName=? and pwd=?";
			return JdbcUtils.getQueryRuner()
					.query(sql,
							new BeanHandler<Admin>(Admin.class),
							admin.getUserName(),
							admin.getPwd());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
