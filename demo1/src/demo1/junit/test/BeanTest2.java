package demo1.junit.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;

public class BeanTest2 {
	
	@Test
	public void getAllProperty() throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor p : propertyDescriptors) {
			System.out.println(p.getReadMethod());
		}
	}
	
	@Test
	public void testPropertyDescriptor() throws Exception {
		Person p = new Person();
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", Person.class);
		Method writeMethod = propertyDescriptor.getWriteMethod();
		Method readMethod = propertyDescriptor.getReadMethod();
		writeMethod.invoke(p, 911);
		System.out.println(readMethod.invoke(p, null));
	}
}
