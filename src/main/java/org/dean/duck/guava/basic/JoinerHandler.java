package org.dean.duck.guava.basic;

import com.google.common.base.Joiner;

/**
 * @description: 字符串拼接
 * @author: dean
 * @create: 2019/06/07 23:18
 */
public class JoinerHandler {
    public static void main(String[] args) {
        Joiner joiner = Joiner.on(",").skipNulls();
        System.out.println(joiner.join("Happy", null, "Holiday"));
    }
}
