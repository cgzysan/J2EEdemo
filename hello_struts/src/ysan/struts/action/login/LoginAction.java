package ysan.struts.action.login;

import com.opensymphony.xwork2.ActionSupport;

import ysan.struts.bean.User;

public class LoginAction extends ActionSupport {
	
	private User user;
	public void setUser (User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	
//	private String userName;
//	private String pwd;
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public void setPwd(String pwd) {
//		this.pwd = pwd;
//	}
	
	public String login() {
		System.out.println(user.getName());
		System.out.println(user.getPwd());
		
		return "login";
	}
	
}
