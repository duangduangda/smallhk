package com.smallhk.core;

import org.junit.Test;

public class IfHandlerTest {
	
	public IfHandlerTest(){
		
	}

	 static class inner{
		public inner(){}
	}
	 
	 class ub{
		 
	 }
	
	@Test
	public void testElseIfErrorHandler() {
		int age = 45;
		if(age > 20){
			System.out.println("It is a younger");
		}else if(age > 40){
			System.out.println("It is a teenager");
		}else if(age > 60){
			System.out.println("It is an old person");
		}
	}
	
	@Test
	public void testElseIfRightHandler() {
		int age = 45;
		if(age > 60){
			System.out.println("It is an old person");
		}else if(age > 40){
			System.out.println("It is a teenager");
		}else if(age > 20){
			System.out.println("It is a younger");
		}
	}
	
	@Test
	public void testBlankStatementHandler(){
		double price = 99.0;
		if(price < 50);{
			System.out.println("This book is cheap");
		}
	}

}
