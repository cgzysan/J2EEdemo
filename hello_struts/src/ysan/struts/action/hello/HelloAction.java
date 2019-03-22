package ysan.struts.action.hello;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
	
	public String execute() throws Exception {
		System.out.println("访问到了Hello action，正在处理请求");
		System.out.println("调用Service");
		return "success";
	}
}
