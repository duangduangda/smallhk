package org.dean.duck.core.ds.sort;

public class DataSorter implements Sorter {

    public static void main(String[] args) {
        int[]data = {1,232,454,632,435,21,454};
        new DataSorter().radixSort(data);
    }

    /**
     * 输出排序结果
     *
     * @param a
     */
    private void outputArrSortResult(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

    }

    /**
     * 交换
     *
     * @param a
     * @param x
     * @param y
     */
    private void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    /**
     * 冒泡排序：每趟排序结束后，总能找出当前的最大值
     *
     * @param a
     */
    @Override
    public void bubbleSort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j + 1] < a[j]) {
                    swap(a, j, j + 1);
                }
            }
            outputArrSortResult(a);
        }
    }

    /**
     * 选择排序：设置临时的最值位置，默认为第一个，每次都从带排序的序列中找出最值
     * 如果当前找到的最小值的位置与排序前设定的默认本轮排序的最值位置不一致，进行交换 否则当前记为本次排序最值
     *
     * @param a
     */
    @Override
    public void selectionSort(int[] a) {
        int j, k;
        for (int i = 0; i < a.length; i++) {
            k = i;
            for (j = i + 1; j < a.length; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            if (i != k) {
                swap(a, i, k);
            }
            outputArrSortResult(a);
        }
    }

    /**
     * 直接插入排序：从待排序序列中取值后与已排序的序列进行逐一比较（自后向前），
     * 如果出现比当前要插入的值小，则移动位置，最后将要插入的值放入空出的位置上
     *
     * @param a
     */
    @Override
    public void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && key < a[j]) {
                a[j + 1] = a[j]; // 移动记录
                j--;
            }
            a[j + 1] = key;
            outputArrSortResult(a);
        }
    }

    public void shellSort1(int[] a) {
        for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < a.length; j = j + gap) {
                    if (a[j] < a[j - gap]) {
                        int key = a[j];
                        int k = j - gap;
                        while (k >= 0 && key < a[k]) {
                            a[k + gap] = a[k];
                            k = k - gap;
                        }
                        a[k + gap] = key;
                    }
                    outputArrSortResult(a);
                }
            }
        }
    }

    public void shellSort2(int[] a) {
        for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
            for (int j = gap; j < a.length; j++) {
                if (a[j] < a[j - gap]) {
                    int key = a[j];
                    int k = j - gap;
                    while (k >= 0 && key < a[k]) {
                        a[k + gap] = a[k];
                        k = k - gap;
                    }
                    a[k + gap] = key;
                }
                outputArrSortResult(a);
            }

        }
    }

    /**
     * 希尔排序：设置不同步幅的直接插入排序
     *
     * @param a
     */
    @Override
    public void shellSort(int[] a) {
        for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
            for (int j = gap; j < a.length; j++) {
                for (int k = j - gap; k >= 0 && a[k + gap] < a[k]; k = k - gap) {
                    swap(a, k, k + gap);
                }
                outputArrSortResult(a);
            }

        }
    }

    /**
     * 快速排序： 1.冒泡排序的改进版，使用了算法设计的分治思想 2.每趟排序返回最后放置关键字的位置
     * 3.每趟排序结果：关键字左边的数值比关键之小，关键字右边的数值比关键字大
     *
     * @param a
     */
    @Override
    public void quickSort(int[] a) {
        doQuicksort(a, 0, a.length - 1);
    }

    private void doQuicksort(int[] a, int left, int right) {
        if (left < right) {// 必须符合left<right条件才继续进行递归，否则数组越界
            int i = left, j = right;
            int key = a[left];
            while (i < j) {
                while (i < j && a[j] > key) {
                    j--;
                }
                if (i < j) {
                    a[i++] = a[j];
                }
                while (i < j && a[i] < key) {
                    i++;
                }
                if (i < j) {
                    a[j--] = a[i];
                }
            }
            a[i] = key;
            outputArrSortResult(a);
            doQuicksort(a, left, i - 1);
            doQuicksort(a, i + 1, right);
        }
    }

    /**
     * 归并排序：采用了分治思想，将已排序的序列进行合并，得到完整的排序序列 合并时，需要建立和待排序序列一样长度的临时序列，同时使用中点进行二分分治
     * 合并的末尾，将保存在临时序列中的已排序序列拷贝到原序列中
     *
     * @param a
     */
    @Override
    public void mergeSort(int[] a) {
        doMergeSort(a, 0, a.length - 1);
    }

    private void doMergeSort(int[] a, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            doMergeSort(a, left, center);
            doMergeSort(a, center + 1, right);
            merge(a, left, center, right);
        }
    }

    private void merge(int[] a, int left, int center, int right) {
        int[] tempArr = new int[a.length];
        int mid = center + 1;
        int tmpIndex = left;
        int start = left;
        while (left <= center && mid <= right) {
            if (a[left] <= a[mid]) {
                tempArr[tmpIndex++] = a[left++];
            } else {
                tempArr[tmpIndex++] = a[mid++];
            }
        }

        while (left <= center) {
            tempArr[tmpIndex++] = a[left++];
        }

        while (mid <= right) {
            tempArr[tmpIndex++] = a[mid++];
        }

        // TODO复制数组
        // while(start <= right){
        // a[start] = tempArr[start++];
        // }
        System.arraycopy(tempArr, start, a, start, right - start + 1);
        outputArrSortResult(a);
    }

    /**
     * 堆排序： 1.最重要的两个操作就是构建初始堆和调整堆，而且构建初始堆的过程事实上就是调整堆的过程 2.分治思想的应用
     *
     * @param a
     */
    @Override
    public void heapSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        buildMaxHeap(a);
        for (int i = a.length - 1; i >= 1; i--) {
            swap(a, 0, i);
            heapAjust(a, i, 0);
        }
    }

    /**
     * 求取一个有序数组差值最大的情况可以采用桶排序，通过比较后一个桶的最小值和前一个桶的最大值来获取最大差值
     * @param data
     */
    @Override
    public void bucketSort(int[] data) {
        int max = Integer.MIN_VALUE;
        int length = data.length;
        // 求取最大值
        for (int i = 0;i <length;i++){
            max = Math.max(max,data[i]);
        }
        // 以最大值作为桶的数目(因为数组是从0开始，为了保证所有数据入桶，桶数组的总数为max+1)
        int[] bucket = new int[max + 1];
        for (int j = 0;j < length;j++){
            // 将data数组的元素作为桶数组的索引下标
            bucket[data[j]]++;
        }

        // 重置data数组的值
        int ii = 0;
        for (int x = 0;x < max + 1;x++){
            while (bucket[x]-- > 0){
                data[ii++] = x;
            }
        }

        // 输出排序后的data数组
        outputArrSortResult(data);
    }

    /**
     * (1)遍历序列找出最大的数(为的是确定最大的数是几位数)；

     (2)开辟一个与数组大小相同的临时数组tmp；

     (3)用一个count数组统计原数组中某一位(从低位向高位统计)相同的数据出现的次数；

     (4)用一个start数组计算原数组中某一位(从最低位向最高位计算)相同数据出现的位置；

     (5)将桶中数据从小到大用tmp数组收集起来；

     (6)重复(3)(4)(5)直到所有位都被统计并计算过，用tmp收集起来；

     (7)将tmp数组拷回到原数组中；
     * @param data
     */
    @Override
    public void radixSort(int[] data) {
        // 获取data数组中最大位数值
        int digit = getMaxDigit(data);
        //用在桶每次倒出来的循环
        int k =0;
        // tmp数组的第一维度表示0-9的余数,第二维代表每个余数最多有多少个数
        int[][] tmp = new int[10][data.length];
        //数组order用来表示该位是i的数的个数
        int []count = new int[10];
        // 取余数
        int base = 1;
        while (digit-- > 0){
            // 用于统计某一位相同数字出现的次数
            for (int i = 0;i < data.length;i++){
                int rem = (data[i] / base) % 10;
                tmp[rem][count[rem]++] = data[i];
            }

            for (int i = 0;i < 10;i++){
                // 当前取余后为i的数值的次数大于0
                if (count[i] != 0){
                    for (int j = 0;j < count[i];j++){
                        data[k++] = tmp[i][j];
                    }
                }
                count[i] = 0;
            }

            base *= 10;
            k = 0;
        }

        outputArrSortResult(data);
    }

    /**
     * 获取最大位数
     * @param data
     */
    private int getMaxDigit(int[] data) {
        // 默认为个位数
        int digit = 1;
        // 初始递增基数值
        int base = 10;
        for (int i = 0;i < data.length;i++){
            while (data[i] >= base){
                ++digit;
                base *= 10;
            }
        }
        return digit;
    }


    /**
     * 调整堆
     *
     * @param a
     * @param heapSize
     * @param index
     */
    private void heapAjust(int[] a, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;
        //进入左子树
        if (left < heapSize && a[left] > a[index]) {
            largest = left;
        }

        //进入右子树
        if (right < heapSize && a[right] > a[largest]) {
            largest = right;
        }


        if (index != largest) {
            swap(a, index, largest);
            heapAjust(a, heapSize, largest);
        }

    }

    /**
     * 构建初始大顶堆
     *
     * @param a
     */
    public void buildMaxHeap(int[] a) {
        for (int i = a.length / 2; i >= 0; i--) {
            heapAjust(a, a.length, i);
        }
        outputArrSortResult(a);
    }
}