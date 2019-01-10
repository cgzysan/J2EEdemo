package ysan.contactSys_web.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.sun.xml.internal.ws.util.xml.XmlUtil;

import ysan.contactSys_web.dao.ContactDao;
import ysan.contactSys_web.entity.Contact;
import ysan.contactSys_web.util.XMLUtil;

public class ContactDaoImpl implements ContactDao{

	public ContactDaoImpl() {
		super();
		String home = System.getProperty("user.home");
		String path = home + File.separator + "Documents" + File.separator + "contact.xml";
		File file = new File(path);

		if (!file.exists()) {
			System.out.println("文件不存在 创建");
			Document doc = DocumentHelper.createDocument();
			doc.addElement("contactList");
			XMLUtil.write2xml(doc);
		}
	}

	/**
	 * 添加联系人
	 */
	@Override
	public void addContact(Contact contact) {
		try {
			String home = System.getProperty("user.home");
			String path = home + File.separator + "Documents" + File.separator + "contact.xml";

			//如果有xml文件，则读取xml文件
			Document doc = XMLUtil.getDocument();
			//如果有xml文件，读取根标签
			Element rootElem = doc.getRootElement();
			
			Element contactElem = rootElem.addElement("contact");
			/**
			 * 由系统自动生成随机且唯一的ID值，赋值给联系人
			 */
			String uuid = UUID.randomUUID().toString().replace("-", "");

			contactElem.addAttribute("id", uuid);
			contactElem.addElement("name").setText(contact.getName());
			contactElem.addElement("gender").setText(contact.getGender());
			contactElem.addElement("age").setText(contact.getAge() + "");
			contactElem.addElement("phone").setText(contact.getPhone());
			contactElem.addElement("email").setText(contact.getEmail());
			contactElem.addElement("qq").setText(contact.getQq());

			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除联系人
	 */
	@Override
	public void deleteContact(String id) {
		try {
			Document doc = XMLUtil.getDocument();
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='" + id + "']");
			if (null != contactElem) {
				contactElem.detach();
			}
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询所有联系人
	 */
	@Override
	public List<Contact> findAll() {
		Document doc = XMLUtil.getDocument();

		List<Contact> list = new ArrayList<>();
		List<Element> conList = doc.selectNodes("//contact");
		for (Element e : conList) {
			Contact c = new Contact();
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
			list.add(c);
		}
		return list;
	}

	/**
	 * 根据编号查询联系人
	 */
	@Override
	public Contact findById(String id) {
		Document doc = XMLUtil.getDocument();
		Element e = (Element) doc.selectSingleNode("//contact[@id='" + id + "']");

		Contact c = null;
		if (null != e) {
			c = new Contact();
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
		}
		return c;
	}

	/**
	 * 修改联系人
	 */
	@Override
	public void updateContact(Contact contact) {
		/**
		 * 需求： 修改id值为2的联系人 1）查询id值为2的contact标签 2）修改contact标签的内容
		 */
		try {
			// 1.读取xml文件
			Document doc = XMLUtil.getDocument();

			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='" + contact.getId() + "']");

			// 2.修改contact标签内容
			contactElem.element("name").setText(contact.getName());
			contactElem.element("gender").setText(contact.getGender());
			contactElem.element("age").setText(contact.getAge() + "");
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("email").setText(contact.getEmail());
			contactElem.element("qq").setText(contact.getQq());

			// 3.把Document写出到xml文件
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
