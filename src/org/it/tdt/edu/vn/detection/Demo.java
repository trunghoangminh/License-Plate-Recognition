package org.it.tdt.edu.vn.detection;

public class Demo {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Demo(String name) {
		this.name = name;
	}

	public Demo(Demo demo) {
		this.name = demo.name;
	}

	public static void main(String[] args) throws CloneNotSupportedException {

		String b = "123123";
		Demo demo = new Demo(b);
		b = "tesst";
		System.out.println(demo.getName());
		System.out.println(b);

	}
}
