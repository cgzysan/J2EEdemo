package demo1.junit.test;

import java.util.Date;

public class Student {
	
	private int id;
	
	private String name;
	
	private int age;

	private Date birthday;
	
	public Student() {
		super();
	}

	public Student(int id, String name, int age, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "id = " + this.id + ", name = " + this.name + ", age = " + this.age + ", birthday = " + birthday;
	}
}
