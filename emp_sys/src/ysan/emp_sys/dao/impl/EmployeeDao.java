package ysan.emp_sys.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ysan.emp_sys.dao.IEmployeeDao;
import ysan.emp_sys.entity.Employee;
import ysan.emp_sys.utils.JdbcUtils;
import ysan.emp_sys.utils.PageBean;

public class EmployeeDao implements IEmployeeDao {

	@Override
	public List<Employee> getAll() {
//		String sql = "select * from employee";
		String sql = "SELECT e.empId, e.empName, d.deptName\n" + 
				" FROM employee e\n" + 
				" INNER JOIN dept d\n" + 
				" ON e.deptId=d.id";
		try {
			return JdbcUtils.getQueryRuner().query(sql, new BeanListHandler<Employee>(Employee.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAllForPage(PageBean<Employee> pb) {
		int totalCount = getTotalCount();
		pb.setTotalCount(totalCount);
		
		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！
		 *              如果当前页为末页，再点下一页显示有问题！
		 * 解决：
		 * 	   1. 如果当前页 <= 0;       当前页设置当前页为1;
		 * 	   2. 如果当前页 > 最大页数；  当前页设置为最大页数
		 */
		// 判断
		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalCount()) {
			pb.setCurrentPage(pb.getTotalPage());
		}
		
		// 获取当前页，计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage - 1) * pb.getPageCount();
		int count = pb.getPageCount();
		
		// 分页查询数据
		String sql = "SELECT e.empId, e.empName, d.deptName\n" + 
				" FROM employee e\n" + 
				" INNER JOIN dept d\n" + 
				" ON e.deptId=d.id\n" + 
				" LIMIT ?, ?";
		
		try {
			List<Employee> pageData = JdbcUtils.getQueryRuner().query(sql, new BeanListHandler<Employee>(Employee.class), index, count);
			pb.setPageData(pageData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private int getTotalCount() {
		String sql = "select count(*) from employee";
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = JdbcUtils.getQueryRuner().query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
