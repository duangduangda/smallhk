package org.dean.duck.ut;

import java.util.Locale;

public class HelloJMockit {
	public String sayHello() {
		Locale locale = Locale.getDefault();
		if (locale.equals(Locale.CHINA)) {
			return "你好世界";
		} else {
			return "hello,world";
		}
	}
}
