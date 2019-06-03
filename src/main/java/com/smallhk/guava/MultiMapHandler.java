package com.smallhk.guava;

import com.google.common.collect.ArrayListMultimap;
import scala.collection.mutable.MultiMap;

import java.util.Collection;

/**
 * guava map：一兼多值的map,可以替换类似Map\<String,List\>
 *
 * @author dean
 * @since 2019-06-03
 */
public class MultiMapHandler {
    public static void main(String[] args) {
        // 创建MultiMap
        MultiMap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("Course", "Computer");
        multiMap.put("Course", "Math");
        multiMap.put("Sports", "Basketball");
        // 打印Map的大小
        System.out.println(multiMap.size());
        //获取Course所有值
        Collection<String> courses = multiMap.get("Course");
        System.out.println(courses);
        // 获取所有值
        System.out.println(multiMap.values());
    }
}
