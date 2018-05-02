package com.smallhk.core.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/8
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class ListTest {

    @Test
    public void testArrayList(){
        List<String> stringList = new ArrayList<>(3);
        stringList.add(null);
        stringList.add("123");
        stringList.add(null);
        assertEquals(3, stringList.size());
        assertNull(stringList.get(0));
        assertEquals(stringList.get(0),stringList.get(2));
        assertTrue(stringList.contains(null));

    }
}
