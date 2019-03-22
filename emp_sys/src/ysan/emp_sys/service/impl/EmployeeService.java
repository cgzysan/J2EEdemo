package ysan.emp_sys.service.impl;

import java.util.List;

import ysan.emp_sys.dao.IEmployeeDao;
import ysan.emp_sys.dao.impl.EmployeeDao;
import ysan.emp_sys.entity.Employee;
import ysan.emp_sys.service.IEmployeeService;
import ysan.emp_sys.utils.PageBean;

public class EmployeeService implements IEmployeeService {

	private IEmployeeDao employeeDao = new EmployeeDao();
	
	@Override
	public List<Employee> getAllEmployee() {
		try {
			return employeeDao.getAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAllEmployeeForPage(PageBean<Employee> pb) {
		try {
			employeeDao.getAllForPage(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
