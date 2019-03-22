package ysan.emp_sys.service;

import java.util.List;

import ysan.emp_sys.entity.Employee;
import ysan.emp_sys.utils.PageBean;

/**
 * 员工业务逻辑层
 * @author ysan
 *
 */
public interface IEmployeeService {
	List<Employee> getAllEmployee();
	void getAllEmployeeForPage(PageBean<Employee> pb);
}
