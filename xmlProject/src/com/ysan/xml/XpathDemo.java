package com.ysan.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XpathDemo {

	/**
	 * xpath 模拟用户登录效果
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名:");
		String name = br.readLine();
		System.out.println("请输入密码：");
		String password = br.readLine();

		Document doc = new SAXReader().read(new File("./src/user.xml"));
		Element userElem = (Element) doc.selectSingleNode("//user[@name = '" + name + "' and @password = '" + password + "']");
		
		if (null != userElem) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
	}
}
