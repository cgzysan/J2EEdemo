package ysan.contactSys_web.dao;


import java.util.List;

import ysan.contactSys_web.entity.Contact;

/**
 * 联系人的DAO接口
 * @author ysan
 *
 */
public interface ContactDao {
	public void addContact(Contact contact);//添加联系人
	public void updateContact(Contact contact);//修改联系人
	public void deleteContact(String id);//删除联系人
	public List<Contact> findAll();  //查询所有联系人
	public Contact findById(String id);//根据编号查询联系人
	public boolean checkContact(String name);//根据姓名查询是否重复
}