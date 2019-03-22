package ysan.emp_sys.dao;

import java.util.List;

import ysan.emp_sys.entity.Employee;
import ysan.emp_sys.utils.PageBean;

/**
 * 员工数据访问层接口设计
 * @author ysan
 *
 */
public interface IEmployeeDao {
	/**
	 * 查询所有的员工
	 * @return
	 */
	List<Employee> getAll();
	
	/**
	 * 分页查询所有的员工
	 * @param pb
	 */
	void getAllForPage(PageBean<Employee> pb);
}
