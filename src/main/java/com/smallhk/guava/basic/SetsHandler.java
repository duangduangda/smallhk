package com.smallhk.guava.basic;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @description: guava集合：Sets
 * @author: dean
 * @create: 2019/06/05 11:58
 */
public class SetsHandler {
    public static void main(String[] args) {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 2, 3, 41, 3444);
        Set<Integer> set2 = Sets.newHashSet(21, 3, 4122, 2);
        // 交集
        System.out.println(Sets.intersection(set1, set2));
        // 并集
        System.out.println(Sets.union(set1, set2));
        // 差集,获取set1中除与set2交集之外的元素
        System.out.println(Sets.difference(set1, set2));
        // 差集,获取set2中除与set1交集之外的元素
        System.out.println(Sets.difference(set2, set1));
    }
}
