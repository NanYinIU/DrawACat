package cat.arithmetic.basic.quickSort;

import java.util.Arrays;

/**
 * 快速排序，从名字上来看就非常高端
 * 其实原理很多好理解，先选出一个基准数，根据这个基准数，分为两部分，左边小于这个基准数，右边大于基准数。
 * 然后递归的对左右两部分再次进行这样的排序。
 * 先从右向左 替换低位纪录并加一，再从左向右替换高位记录并减一，从而实现替换的目的
 * 迷糊的时候debug一边就ok了
 */
public class quickSort {

    private static void quick_sort(int array[],int l,int r){
        //i在左边j在右边
        //每次都取第一个数作为基准数
        int i = l;
        int j = r;
        int standNumber = array[l];
        if(i>j){
            return;
        }
        while(i<j){
//            时刻判断i是否小于j 大于j就不对了
            while(i<j && array[j]>standNumber){
//                右边如果大于基准值则j-- 一直走，知道他发现它小于standNumber的时候停止
                j--;
            }
            if(i<j){
//                当停止的时候比基准小的替换低位记录,并且低位向前加一
                array[i++] = array[j];
            }
            while(i<j && array[i]<standNumber){
//                同理 左边i++ 一直走，当他发现他大 于standNumber的时候停止
                i++;
            }
            if(i<j){
//                当停止的时候比基准大的替换高位记录，并向后减一
                array[j--] = array[i];
            }
        }

        array[i] = standNumber;
        quick_sort(array,l,i-1);
        if(i!=array.length-1){
            quick_sort(array,i+1,r);
        }

    }

    private static void  sort(int arr[]){
        quick_sort(arr,0,arr.length-1);
    }
    public static void main(String[] args) {
        int[] sortArray = new int[]{4,5,1,6,3};
        sort(sortArray);
        System.out.println(Arrays.toString(sortArray));
    }
}
