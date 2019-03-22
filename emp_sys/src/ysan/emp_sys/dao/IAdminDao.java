package ysan.emp_sys.dao;

import ysan.emp_sys.entity.Admin;

/**
 * 管理员数据访问层接口设计
 * @author ysan
 *
 */
public interface IAdminDao {
	
	/**
	 * 根据用户名密码查询
	 * @param admin
	 * @return
	 */
	Admin findByNameAndPwd(Admin admin);
}
