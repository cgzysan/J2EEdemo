package ysan.contact;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 联系人操作实现类的测试类
 * 
 * @author ysan
 *
 */
public class TestContactOperatorImpl {
	ContactOperator operator = null;

	@Before
	public void init() {
		operator = new ContactOperatorImp();
	}

	@Test
	public void testAddContact() {
		System.out.println("添加");
		Contact contact = new Contact();
		contact.setId("3");
		contact.setName("jack");
		contact.setGender("男");
		contact.setAge(20);
		contact.setPhone("134222233333");
		contact.setEmail("eric@qq.com");
		contact.setQq("33334444");
		operator.addContact(contact);
	}

	@Test
	public void testUpdateContact() {
		System.out.println("更新");
		Contact contact = new Contact();
		contact.setId("3"); // 修改的ID
		contact.setName("zhangsan");
		contact.setGender("女");
		contact.setAge(30);
		contact.setPhone("135222233333");
		contact.setEmail("zhangsan@qq.com");
		contact.setQq("33334444");
		operator.updateContact(contact);
	}

	@Test
	public void testDeleteContact() {
		System.out.println("删除");
		operator.deleteContact("3");
	}

	@Test
	public void testFindAll() {
		List<Contact> list = operator.findAll();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}

	@Test
	public void showFilePath() {
		String home = System.getProperty("user.home");
		String separator = File.separator;
		System.out.println("Home = " + home + ", separator = " + separator);
	}
}
