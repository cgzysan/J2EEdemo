package ysan.emp_sys.service.impl;

import ysan.emp_sys.dao.IAdminDao;
import ysan.emp_sys.dao.impl.AdminDao;
import ysan.emp_sys.entity.Admin;
import ysan.emp_sys.service.IAdminService;

public class AdminService implements IAdminService{

	// 创建dao对象
	private IAdminDao adminDao = new AdminDao();
	
	@Override
	public Admin findByNameAndPwd(Admin admin) {
		try {
			return adminDao.findByNameAndPwd(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
