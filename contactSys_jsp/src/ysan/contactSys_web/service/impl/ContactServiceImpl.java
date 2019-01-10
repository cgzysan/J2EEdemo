package ysan.contactSys_web.service.impl;

import java.util.List;

import ysan.contactSys_web.dao.ContactDao;
import ysan.contactSys_web.dao.impl.ContactDaoImpl;
import ysan.contactSys_web.entity.Contact;
import ysan.contactSys_web.exception.NameRepeatException;
import ysan.contactSys_web.service.ContactService;

/**
 * 业务逻辑层
 * 处理项目中出现的业务逻辑
 * @author ysan
 *
 */
public class ContactServiceImpl implements ContactService {
	ContactDao dao = new ContactDaoImpl();

	@Override
	public void addContact(Contact contact) throws NameRepeatException {
		// 执行业务逻辑判断
		if (dao.checkContact(contact.getName())) {
			//重复
			/**
			 * 注意： 如果业务层方法出现任何业务异常，则返回标记（自定义异常）到servlet
			 */
			throw new NameRepeatException("姓名重复，不可使用");
		} 
		dao.addContact(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		dao.updateContact(contact);
	}

	@Override
	public void deleteContact(String id) {
		dao.deleteContact(id);
	}

	@Override
	public List<Contact> findAll() {
		return dao.findAll();
	}

	@Override
	public Contact findById(String id) {
		return dao.findById(id);
	}
	
}
