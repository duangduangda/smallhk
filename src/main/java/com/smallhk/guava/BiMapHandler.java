package com.smallhk.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @description: guava集合：键值对双向映射
 * 对value的值强制唯一性，如果value值出现重复，则会抛出错误：java.lang.IllegalArgumentException
 * @author: dean
 * @create: 2019/06/04 11:27
 */
public class BiMapHandler {
    public static void main(String[] args) {
        BiMap<String, Integer> kvMap = HashBiMap.create();
        kvMap.put("k1", 1);
        kvMap.put("k2", 2);
        kvMap.put("k3", 3);
        kvMap.put("k4", 4);
        System.out.println(kvMap);
        // 根据value查询对应的Key
        BiMap<Integer, String> vkMap = kvMap.inverse();
        System.out.println(vkMap.get(1));
        // 打印反转之后的map
        System.out.println(vkMap);
        // 添加元素
        kvMap.put("K5", 5);
        System.out.println(kvMap);
        System.out.println(vkMap);
    }
}
