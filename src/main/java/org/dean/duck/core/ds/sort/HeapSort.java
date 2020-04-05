package org.dean.duck.core.ds.sort;
/**
 * 建立最大堆
 */
public class HeapSort {

    public int[] sort(final int[] nums) {
        if(null == nums || nums.length <= 1){
            return nums;
        }
        // 创建堆
        create(nums);
        // 堆排序
        heap_sort(nums);
        // 输出
        return nums;
    }

    private void create(int[]nums){
        int n = nums.length - 1;
        for(int i = n / 2;i >= 0;i--){
            shifdown(nums,i,n);
        }
    }

    private void heap_sort(int[]nums){
        int n = nums.length - 1;
        while(n >= 1){
            swap(nums,0,n);
            n--;
            shifdown(nums,0,n);
        }
    }

    private void swap(int[]nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void shifdown(int[]nums,int i ,int n){
        int t;
        // 表示子树是否满足最大堆的特性
        boolean flag = false;
        // 至少存在左子树以及未
        while(2 * i <= n && !flag){
            if(nums[2 * i] > nums[i]){
                t = 2 * i;
            }else{
                t = i;
            }
            // 判断是否存在右子树
            if(2 * i + 1 <= n){
                if(nums[2 * i + 1] > nums[t]){
                    t = 2 * i + 1;
                }
            }
            if( t != i){
                swap(nums, i, t);
                // 切换i的下标，以准备下一个子树的执行
                i = t;
            }else{
                flag = true;
            }
        }
    }
}