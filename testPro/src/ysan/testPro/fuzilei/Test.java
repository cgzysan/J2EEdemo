package ysan.testPro.fuzilei;

public class Test {
	String s = "父类";

	public Test() {
		System.out.println(1+s);
		System.out.println(2+getS());
	}

	public String getS() {
		System.out.println(3+"父类方法");
		return s;
	}
	
	public static void main(String[] args) {
		new son();
	}
}

class son extends Test{
	String s = "子类";

	public son() {
		System.out.println(4+s);
		System.out.println(5+getS());
	}
	
	@Override
	public String getS() {
		System.out.println(6+"子类方法");
		return s;
	}
	
	public static void main(String[] args) {
		new son();
	}
}


