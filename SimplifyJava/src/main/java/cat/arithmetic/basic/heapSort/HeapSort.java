package cat.arithmetic.basic.heapSort;

import java.util.Arrays;

public class HeapSort {

    public void sort(int[] arr) {
        // 1. 先构建堆
        buildMaxHeap(arr);
        // 2. 将堆顶元素弹出，重新整理堆
        // 弹出栈顶元素，需要把当前栈顶元素和尾部元素替换，并且重新heapify
        int n = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            // 交换堆顶元素为堆尾元素
            swap(arr, i, n);
            // 重新堆化后面的元素，这步操作包括将堆顶元素排除掉
            heapify(arr, 0, n - i);
        }
    }

    public void buildMaxHeap(int[] arr) {
        // 形成堆后，数组位置i的元素的左子节点位置为 2i+1 右子节点位置为2i+2 父节点位置为 floor(i/2)
        // 如果想建立堆，需要的是对每一个父节点进行堆化，怎么找堆的最后的一个父节点：最后一个节点（i-1）/2 就是当前的父节点，
        int lastNode = arr.length - 1;
        int lastNodeParent = lastNode / 2;
        for (int j = lastNodeParent; j >= 0; j--) {
            //对所有有子节点的父节点进行堆化操作，从而达到建立大顶堆的目的。
            heapify(arr, j, arr.length);
        }
    }

    public void heapify(int[] arr, int i, int n) {
        //堆化 就是比较 这三个位置的的大小 i为节点位置，n为需要排序的节点数量，根据这三个参数能够获得子节点，父节点
        if (i >= n) {
            // 设置出口
            return;
        }
        // 左子节点
        int left = 2 * i + 1;
        // 右子节点
        int right = 2 * i + 2;
        //设置最大值
        int max = i;
        //找出这三个节点中的最大值
        if (left < n && arr[left] > arr[max]) {
            max = left;
        }
        if (right < n && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            //如果父节点的值发生变化，需要交换父节点和即将交换的节点的值
            swap(arr, i, max);
            //递归向下对子节点排序
            heapify(arr, max, n);
        }
    }

    public void swap(int[] arr, int i, int max) {
        int temp = arr[i];
        arr[i] = arr[max];
        arr[max] = temp;
    }

    public static void main(String[] args) {
        int[] sortArr = new int[]{4, 8, 6, 5};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(sortArr);
        System.out.println(Arrays.toString(sortArr));
    }
}
