package com.smallhk.guava;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

/**
 * @author dean
 * @create 2019-06-04
 */
public class IterableHandler {
    public static void main(String[] args) {
        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6
        // 打印迭代器中的元素
        System.out.println(concatenated);
        // 打印迭代器中的元素的个数
        System.out.println(Iterables.size(concatenated));

    }
}
