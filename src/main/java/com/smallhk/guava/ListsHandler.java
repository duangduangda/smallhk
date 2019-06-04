package com.smallhk.guava;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @author dean
 * @since 2019-06-04
 */
public class ListsHandler {
    public static void main(String[] args) {
        List<Integer> integerList = Ints.asList(12,3,4,1,2);
        // 翻转list
        System.out.println(Lists.reverse(integerList));
        // 分区(2个元素作为一组)
        System.out.println(Lists.partition(integerList,2));
    }
}
