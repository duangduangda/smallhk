package org.dean.duck.guava.basic;

import com.google.common.base.Strings;

/**
 * @description: 字符串处理
 * @author: dean
 * @create: 2019/06/08 22:03
 */
public class StringsHandler {
    public static void main(String[] args) {
        // 在单词foo后加上x,补充起长度为6
        System.out.println(Strings.padEnd("foo", 6, 'x'));

    }
}
