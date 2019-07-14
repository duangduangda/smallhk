package org.dean.duck.fp;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: dean
 * @create: 2019/04/25 16:05
 */
public class Chapter6 {
    public static void main(String[] args) {
        parallelInitialize();
        simpleMovingAverage();
    }

    /**
     * 计算滑动平均数
     */
    private static void simpleMovingAverage() {
        int n = 6;
        double[] sums = {0, 1, 2, 3, 4, 3.5};
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        Arrays.stream(IntStream.range(start, sums.length)
                .mapToDouble(index -> {
                    double prefix = index == start ? 0 : sums[index - n];
                    return (sums[index] - prefix) / n;
                }).toArray()).forEach(System.out::println);
    }

    /**
     * 并行化数组操作初始化数组
     *
     * @return
     */
    private static void parallelInitialize() {
        double[] values = new double[6];
        Arrays.parallelSetAll(values, x -> x + 1);
        Arrays.stream(values).forEach(System.out::println);
    }
}
