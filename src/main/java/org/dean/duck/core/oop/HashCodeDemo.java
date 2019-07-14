package org.dean.duck.core.oop;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/27
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class HashCodeDemo {
    public static void main(String[] args) {
        School school1 = new School();
        school1.setName("eric");
        School school2 = new School();
        school2.setName("eric");
        System.out.println(school1.hashCode());
        System.out.println(school1.hashCode() == school2.hashCode());
    }
}
