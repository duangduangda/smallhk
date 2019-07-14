package org.dean.duck.core;

/**
 * @description this class will lead to stackoverflow error
 * @author eric
 * @since 2016年4月28日 上午10:58:42
 * @version 1.0
 *
 */
public class InstanceTest {

	private InstanceTest instance;
	private String name;
	
	public InstanceTest(){
		System.out.println("no parameter constructor");
	}
	
	public InstanceTest(String name) {
		instance = new InstanceTest();
		this.name = name;
		System.out.println("one parameter constructor");
	}
	
	public String toString(){
		return "InstanceTest[instance=" + instance + "]";
	}

	public static void main(String[] args) {
		InstanceTest in1 = new InstanceTest();
		InstanceTest in2 = new InstanceTest("test");
//		in1.instance = in2;
//		in2.instance = in1;
		System.out.println(in1);
		System.out.println(in2);
	}

}
