package cat.arithmetic.basic.mergeSort;

import java.util.Arrays;

/**
 * 归并排序算法
 * 归并排序是一种分治算法，将待排序的分而治之
 * 思路就是将两个有序数组合并成一个有序的数组
 *
 * @Author basic
 * @Date 22:08 2019-06-10
 **/
public class merge {
    public static void main(String[] args) {
        int[] sortArray = new int[]{1, 3, 5, 2,10,6,7,4};
        mergeSort(sortArray);
        System.out.println(Arrays.toString(sortArray));
    }

    private static void mergeSort(int[] arr) {
        //取中间值，切分成两块
        int start = 0;
        int end = arr.length;
        int middle = (end - start)/2;
        if (arr.length > 1) {
            // 分别制造左边和右边的数组，用来合并
            int[] leftArr = Arrays.copyOfRange(arr, start, middle);
            int[] rightArr = Arrays.copyOfRange(arr, middle, end);
            // 对左边数组递归排序
            mergeSort(leftArr);
            // 对右边数组递归排序
            mergeSort(rightArr);
            // 对有序数组归并
            mergeTwoArr(arr, start, leftArr, rightArr);
        }
    }

    public static void mergeTwoArr(int[] arr, int start, int[] leftArr, int[] rightArr) {
        //分别设立左右数组的计数器，如果放到大数组里计数器就向前挪动1
        int leftFlag = 0;
        int rightFlag = 0;
        int global = start;

        while (leftFlag < leftArr.length && rightFlag < rightArr.length) {
            if (leftArr[leftFlag] < rightArr[rightFlag]) {
                //如果左边的小于右边的元素，则将左边的元素放到大数组里
                arr[global] = leftArr[leftFlag];
                leftFlag++;
                global++;
            } else {
                arr[global] = rightArr[rightFlag];
                rightFlag++;
                global++;
            }
        }
        //如果左边的没有放完，则将后面的都加到arr中
        while (leftFlag < leftArr.length) {
            arr[global] = leftArr[leftFlag];
            global++;
            leftFlag++;
        }
        //同理右边
        while (rightFlag < rightArr.length) {
            arr[global] = rightArr[rightFlag];
            global++;
            rightFlag++;
        }

    }

}
