package org.dean.duck.core.ds.sort;

public class QuickSort {

    public int[] sort(int[] nums) {
        execute(nums, 0, nums.length - 1);
        return nums;
    }

    private void execute(int[] nums, int start, int end) {
        if (start < end) {
            int privokey = partition(nums, start, end);
            execute(nums, start, privokey - 1);
            execute(nums, privokey + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int p = start;
        int i = p - 1;
        int key = nums[end];
        for (int j = p; j < end; j++) {
            if (nums[j] <= key) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, end);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int partition1(int[]nums, int start,int end){
        int i = start;
        int j = end;
        int key = nums[end];
        while(i < j){
            while(i < j && nums[i] < key ){
                i++;
            }
            swap(nums, i, j);
            while(i < j && nums[j] > key){
                j--;
            }
            swap(nums, i, j);
        }
        nums[i+1] = key;
        return i + 1;
    }

}