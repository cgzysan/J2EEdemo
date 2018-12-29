package com.ysan.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDefaultHandler extends DefaultHandler {
	
	private StringBuffer sb = new StringBuffer();

	private List<Contact> list = new ArrayList<>();
	
	public String getContent( ) {
		return sb.toString();
	}
	
	public List<Contact> getList() {
		return list;
	}
	
	private Contact contact;
	private String curTag;
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("startdocument");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("startelement " + qName);
		sb.append("<" + qName);
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				// 获取属性名称
				String attrName = attributes.getQName(i);
				// 得到属性值
				String attrValue = attributes.getValue(i);
				sb.append(" " + attrName + "= \"" + attrValue + "\"");
			}
		}
		
		curTag = qName;
		if ("contact".equals(qName)) {
			contact = new Contact();
			
			contact.setId(attributes.getValue("id"));
		}
		sb.append(">");
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		System.out.println("content = " + content);
		sb.append(content);
		
		if ("name".equals(curTag)) {
			contact.setName(content);
		}
		if ("age".equals(curTag)) {
			contact.setAge(content);
		}
		if ("phone".equals(curTag)) {
			contact.setPhone(content);
		}
		if ("email".equals(curTag)) {
			contact.setEmail(content);
		}
		if ("qq".equals(curTag)) {
			contact.setQq(content);
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("endelement = " + qName);
		sb.append("</" + qName + ">");
		curTag = null;
		if("contact".equals(qName)){
			list.add(contact);
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("enddocument");
	}
}
