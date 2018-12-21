package demo1.junit.test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class BeanTest3 {

	public static void main(String[] args) throws Exception, InvocationTargetException {
		String id = "911";
		String name = "李四";
		String age = "3";
		String date = "2013 12 12";
		Student s = new Student();

		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class type, Object value) {
				try {
					if (null != value) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
						Date d = dateFormat.parse((String) value);
						return d;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}, Date.class);
		BeanUtils.setProperty(s, "id", id);
		BeanUtils.setProperty(s, "name", name);
		BeanUtils.setProperty(s, "age", age);
		BeanUtils.setProperty(s, "birthday", date);
		System.out.println(s);
	}
}
