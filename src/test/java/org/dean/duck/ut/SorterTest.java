package org.dean.duck.ut;

import static org.junit.Assert.assertEquals;

import org.dean.duck.core.ds.sort.HeapSort;
import org.dean.duck.core.ds.sort.QuickSort;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class SorterTest {

    @Test
    public void testQuickSort() {
        QuickSort qSort = new QuickSort();
        int[] nums = { 1, 6, 2, 3, 9 };
        int first = qSort.sort(nums)[0];
        int second = qSort.sort(nums)[1];
        int third = qSort.sort(nums)[2];
        int forth = qSort.sort(nums)[3];
        int fifth = qSort.sort(nums)[4];
        assertEquals(1, first);
        assertEquals(2, second);
        assertEquals(3, third);
        assertEquals(6, forth);
        assertEquals(9, fifth);

    }

    @Test
    public void testHeapSort() {
        HeapSort hSort = new HeapSort();
        int[] nums = { 1, 6, 2, 3, 9 };
        int first = hSort.sort(nums)[0];
        int second = hSort.sort(nums)[1];
        int third = hSort.sort(nums)[2];
        int forth = hSort.sort(nums)[3];
        int fifth = hSort.sort(nums)[4];
        assertEquals(1, first);
        assertEquals(2, second);
        assertEquals(3, third);
        assertEquals(6, forth);
        assertEquals(9, fifth);

    }
}