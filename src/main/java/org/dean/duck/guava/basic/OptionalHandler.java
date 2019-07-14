package org.dean.duck.guava.basic;

import com.google.common.base.Optional;

/**
 * @description:
 * @author: dean
 * @create: 2019/03/10 18:48
 */
public class OptionalHandler {
    public static void main(String[] args) {
        // 创建指定引用的optional实例，若引用为null则快速失败
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());
        // 创建指定引用的optional实例，如果引用为null,则表示缺失
        Optional<Integer> nullPossible = Optional.fromNullable(null);
        System.out.println(nullPossible.isPresent());

        String status = "213";
        // 如果为空，取123，否则取status的值
        System.out.println(Optional.fromNullable(status).or("123"));
        status = null;
        // 返回optional所包含的引用，如果引用缺失，返回Null
        System.out.println(Optional.fromNullable(status).orNull());

        // 创建一个引用缺失的optional实例
        Optional<Integer> absentInteger = Optional.absent();
        System.out.println(absentInteger.isPresent());


    }
}
