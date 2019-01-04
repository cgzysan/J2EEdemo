package ysan.contact;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class ContactOperatorImp implements ContactOperator{

	@Override
	public void addContact(Contact contact) {
		try {
			File file = new File("~/Users/ysan/Documents/contact.xml");
			Document doc = null;
			Element rootElem = null;
			if (!file.exists()) {
				doc = DocumentHelper.createDocument();
				doc.getRootElement();
			} else {
				doc = XMLUtil.getDocument();
				rootElem = doc.getRootElement();
			}
			
			Element contactElem = rootElem.addElement("contact");
			contactElem.addAttribute("id", contact.getId());
			contactElem.addElement("name").setText(contact.getName());
			contactElem.addElement("gender").setText(contact.getGender());
			contactElem.addElement("age").setText(contact.getAge()+"");
			contactElem.addElement("phone").setText(contact.getPhone());
			contactElem.addElement("email").setText(contact.getEmail());
			contactElem.addElement("qq").setText(contact.getQq());
			
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateContact(Contact contact) {
		try {
			Document doc = XMLUtil.getDocument();
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='" +contact.getId() +"']");
			
			contactElem.element("name").setText(contact.getName());
			contactElem.element("gender").setText(contact.getGender());
			contactElem.element("age").setText(contact.getAge()+"");
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("email").setText(contact.getEmail());
			contactElem.element("qq").setText(contact.getQq());
			
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteContact(String id) {
		try {
			//1.读取xml文件
			Document doc = XMLUtil.getDocument();
			//2.查询需要删除id的contact
			Element contactElem = (Element)doc.selectSingleNode("//contact[@id='"+id+"']");
			//删除标签
			contactElem.detach();
			
			//3.把Document写出到xml文件
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Contact> findAll() {
		//1.读取xml文件
		Document doc = XMLUtil.getDocument();
		
		//2.创建List对象
		List<Contact> list = new ArrayList<Contact>();
		//3.读取contact标签
		List<Element> conList = (List<Element>)doc.selectNodes("//contact");
		for(Element e:conList){
			//创建COntact对象
			Contact c = new Contact();
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
			//把Contact放入list中
			list.add(c);
		}
		return list;
	}

}
