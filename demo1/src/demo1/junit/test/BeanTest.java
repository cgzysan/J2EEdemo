package demo1.junit.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class BeanTest {
	
	public static void main(String[] args) throws Exception {
		Person p = (Person) getInstance();
		System.out.println(p);
	}
	
	public static Object getInstance() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("obj.txt"));
		String className = bufferedReader.readLine();
		Class clazz = Class.forName(className);
		Constructor cons = clazz.getConstructor();
		Object o = cons.newInstance();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			String[] datas = line.split("=");
			Field field = clazz.getDeclaredField(datas[0]);
			if (field.getType() == int.class) {
				field.set(o, Integer.parseInt(datas[1]));
			} else {
				field.set(o, datas[1]);
			}
		} 
		return o;
	}
}
