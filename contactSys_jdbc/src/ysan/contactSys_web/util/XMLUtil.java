package ysan.contactSys_web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtil {

	/**
	 * 用于读取xml文件
	 * @return
	 */
	public static Document getDocument() {
		try {
			String home = System.getProperty("user.home");
			String path = home + File.separator + "Documents" + File.separator + "contact.xml";
			Document doc = new SAXReader().read(new File(path));
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 写出xml文件
	 */
	public static void write2xml(Document doc) {
		try {
			String home = System.getProperty("user.home");
			String path = home + File.separator + "Documents" + File.separator + "contact.xml";
			FileOutputStream fos = new FileOutputStream(path);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(fos, format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
}
