package demo1.junit.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBUtil {

	static Properties properties;
	
	static {
		try {
			properties = new Properties();
			Class clazz = DBUtil.class;
			InputStream is = clazz.getResourceAsStream("/db.properties");
			if (is == null) {
				System.out.println("is null");
			} else {
				properties.load(is);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("name = " + properties.getProperty("userName") + ", password = " + properties.getProperty("password"));
	}
}
