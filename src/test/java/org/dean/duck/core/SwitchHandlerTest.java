package org.dean.duck.core;

import org.junit.Test;

public class SwitchHandlerTest {

	@Test
	public void testNoBreak() {
		char score = 'C';
		switch(score){
			case 'A':
				System.out.println("Get an A");
			case 'B':
				System.out.println("Get an B");
			case 'C':
				System.out.println("Get an C");
			case 'D':
				System.out.println("Get an D");
			default:
				System.out.println("Error Input");
		}
	}
	
	public enum Season{
		SPRING,SUMMER,FALL,WINTER
	}
	
	@Test
	public void testCaseEnumType(){
		Season s = Season.SPRING;
		switch(s){
			case SPRING:
				System.out.println("It is Spring now");
				break;
			case SUMMER:
				System.out.println("It is SUMMER now");
				break;
			case FALL:
				System.out.println("It is FALL now");
				break;
			case WINTER:
				System.out.println("It is WINTER now");
				break;
		}
	}

}
