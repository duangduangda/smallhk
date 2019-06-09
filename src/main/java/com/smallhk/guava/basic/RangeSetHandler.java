package com.smallhk.guava.basic;

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
        rangeSet.add(Range.closed(1, 10));
        System.out.println(rangeSet);
        // 添加左开右闭区间,由于和之前的区间发生了重合，重合的部分将会合并
        rangeSet.add(Range.openClosed(10, 26));
        System.out.println(rangeSet);
        // 添加空区间，将会被忽略
        rangeSet.add(Range.openClosed(0, 0));
        System.out.println(rangeSet);
        // 分割操作
        rangeSet.remove(Range.closed(5, 10));
        System.out.println(rangeSet);
        // 返回补集
        System.out.println(rangeSet.complement());
        // 遍历RangeSet中的Range
        System.out.println(rangeSet.asRanges());
        // 是否包含某个元素
        System.out.println(rangeSet.contains(6));
        System.out.println(rangeSet.contains(2));
        // 返回存在给定元素的区间
        System.out.println(rangeSet.rangeContaining(2));
        // 返回最小区间
        System.out.println(rangeSet.span());
    }
}
