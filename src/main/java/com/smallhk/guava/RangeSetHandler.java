package com.smallhk.guava;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * @description:guava集合：rangeset 用于描述一组不相连的，非空的区间，当把区间加入到可变的rangeset中，所有相连接的区间会被合并，空区间会被忽略
 * @author: dean
 * @create: 2019/06/04 14:27
 */
public class RangeSetHandler {
    public static void main(String[] args) {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        // 闭区间[1-10]
        rangeSet.add(Range.open(1, 10));
        System.out.println(rangeSet);
        System.out.println();
    }
}
