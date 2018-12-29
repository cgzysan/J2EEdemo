package com.ysan.xml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SaxDemo {
	
	public static void main(String[] args) throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		MyDefaultHandler myhandler = new MyDefaultHandler();
		parser.parse(new File("./src/contact.xml"), myhandler);
		String content = myhandler.getContent();
		System.out.println("Content = " + content);
		
		List<Contact> list = myhandler.getList();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}
