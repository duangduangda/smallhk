package org.dean.duck.guava.basic;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;

/**
 * guava-集合类型(这里的set不是实现Set接口，而是与Collection类似，允许出现重复元素)
 *
 * @author dean
 * @since 2019-06-03
 */
public class MultiSetHandler {
    public static void main(String[] args) {
        // 创建一个hash multiset
        Multiset multiset = HashMultiset.create();
        multiset.add("12");
        multiset.add("23");
        multiset.add("12");
        // 对元素"12"进行计数
        System.out.println(multiset.count("12"));
        // 对元素"23"进行计数
        System.out.println(multiset.count("23"));
        // 计算集合中的总个数，包含重复的元素
        System.out.println(multiset.size());

        // 去重，转化为Set类型
        Set<String> stringSet = multiset.elementSet();
        System.out.println(stringSet);

        // 使用迭代器
        Iterator<String> stringIterator = multiset.iterator();
        while (stringIterator.hasNext()) {
            System.out.printf("%s\t", stringIterator.next());
        }

        // 不重复打印元素，并显示出现次数
        Set<Multiset.Entry> entries = multiset.entrySet();
        for (Multiset.Entry entry : entries) {
            System.out.printf("\nElement:%s,Occurences:%d", entry.getElement(), entry.getCount());
        }

        System.out.println();

        // 删除元素
        multiset.remove("12");
        System.out.println(multiset);
        multiset.remove("10");
        System.out.println(multiset);
    }
}
