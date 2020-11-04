package cat.arithmetic.basic.selectionSort;

import java.util.Arrays;

/**
 * 今天就来看看选择排序是如何实现的
 * 选择排序原理就是从第一个位置上选择一个最小的数字，第二个位置上选择剩下的数中最小的数字，以此类推排序就完成了
 */
public class Selection {
    public static void main(String[] args) {
        Integer[] sortArray = new Integer[]{1,3,2,5,4,9,7,6,8};
        for (int i = 0 ; i < sortArray.length ; i++){
            // 记录最小的位置
            int min = i;
            // 因为要替换最小元素的位置，所以每次循环都需要从已经替换的位置开始 也就是i+1
            for(int j = i+1 ; j < sortArray.length ;j++){
                if(sortArray[j] < sortArray[min]){
                    //如果当前元素小于最小的元素，则替换两个元素
                   min = j ;
                }
                // 如果不相等，则替换两个位置
                if(min != i){
                    int x =  sortArray[min] ;
                    sortArray[min] = sortArray[i];
                    sortArray[i] = x;
                }
            }
        }
        Arrays.toString(sortArray);
    }
}

