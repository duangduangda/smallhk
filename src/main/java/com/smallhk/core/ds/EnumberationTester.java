package com.smallhk.core.ds;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @description using enumeration
 * @author dongyh
 * @since 2018年2月8日 下午5:40:11
 * @version 1.0
 *
 */
public class EnumberationTester {
    public static void main(String[] args) {
        Enumeration<String> days;
        Vector<String> dayNames = new Vector<String>();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thurday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();
        while (days.hasMoreElements()){
            System.out.println(days.nextElement());
        }
    }
}
