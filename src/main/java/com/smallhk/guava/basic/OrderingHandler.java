package com.smallhk.guava.basic;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

/**
 * @description: 排序器
 * @author: dean
 * @create: 2019/06/03 17:38
 */
public class OrderingHandler {
    public static void main(String[] args) {
        List<Integer> integers = Lists.newArrayList(2, 1, 34, 1, 31, 341);
        // 创建排序器
        Ordering<Integer> naturalOrder = Ordering.natural();
        // 获取可迭代对象中最大的K个元素
        System.out.println(naturalOrder.greatestOf(integers, 2));
        // 正排序
        System.out.println(naturalOrder.sortedCopy(integers));
        // 逆排序
        System.out.println(naturalOrder.reverse().sortedCopy(integers));
        // 是否严格有序(不允许出现重复的值)
        System.out.println(naturalOrder.isStrictlyOrdered(naturalOrder.sortedCopy(integers)));
        // 返回最大值，最小值
        System.out.println(naturalOrder.max(integers));
        System.out.println(naturalOrder.min(integers));

    }
}
