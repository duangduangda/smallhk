package org.dean.duck.guava.basic;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @description: guava集合：Table
 * 使用多个键做索引，可以替换类似于Map<String,Map<String,Integer></String,Integer>
 * ,可以按行和列来定位最终的值，其中一个索引位置标识行，第二个标识列，最后一位标识实际的字典值
 * @author: dean
 * @create: 2019/06/04 12:00
 */
public class TableHandler {
    public static void main(String[] args) {
        Table<String, String, Integer> matchResult = HashBasedTable.create();
        matchResult.put("CHN", "Basketball", 95);
        matchResult.put("CHN", "Football", 90);
        matchResult.put("END", "Basketball", 99);
        matchResult.put("USA", "Basketball", 100);
        // 输出CN关联信息
        System.out.println(matchResult.row("CHN"));
        // 输出Basketball的相关值
        System.out.println(matchResult.column("Basketball"));
        // 输出value
        System.out.println(matchResult.get("CHN", "Football"));
        // 判断是含有行为CHN，列为Basketball的项
        System.out.println(matchResult.contains("CHN", "Basketball"));
        // 判断是否含有列Basketball
        System.out.println(matchResult.containsColumn("Basketball"));
        // 按列输出对应的map
        System.out.println(matchResult.columnMap());
        // 按行输出对应的map
        System.out.println(matchResult.rowMap());
    }
}
