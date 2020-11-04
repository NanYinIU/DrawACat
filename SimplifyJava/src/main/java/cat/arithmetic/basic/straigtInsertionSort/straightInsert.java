package cat.arithmetic.basic.straigtInsertionSort;

import java.util.Arrays;

/**
 * 直接插入排序的算法实现 copy 别人的 其实就是和扑克牌相似的，手里一张一张的拿到牌，每一次拿到牌，都插入到
 * 正确的位置，需要每次拿到之后先由后向前比他大的数，因为前面的数已经排好了，所以想要插入到正确位置就需要找到
 * 这个比这个数大的数，找到了把这个数插到这个位置，后面比他大的数向后排。这就是直接插入排序。
 */
public class straightInsert {
//    public static void main(String[] args) {
//        Integer[] sortArray = new Integer[]{1,3,2,5,4,9,7,6,8};
//        int n = sortArray.length;
//        int i, j, k;
//        for (i = 1; i < n; i++)
//        {
//            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
//            for (j = i - 1; j >= 0; j--)
//            {
//                if (sortArray[j] <= sortArray[i])
//                {
//                    break;
//                }
//            }
//
//            //如找到了一个合适的位置
//            if (j != i - 1)
//            {
//                //将比a[i]大的数据向后移
//                int temp = sortArray[i];
//                for (k = i - 1; k > j; k--)
//                {
//                    sortArray[k + 1] = sortArray[k];
//                }
//                //将a[i]放到正确位置上
//                sortArray[k + 1] = temp;
//            }
//        }
//        for(int i1:sortArray){
//            System.out.println(i1);
//        }
//
//    }
    public static void main(String[] args) {
        Integer[] sortArray = new Integer[]{1, 3, 2, 5, 4, 9, 7, 6, 8};
        // 排序后的数组
        Integer[] after = new Integer[sortArray.length];
        for (int i = 0; i < sortArray.length; i++) {
            after[i] = sortArray[i];
            // 对新数组内的比较大小，如果有比他大的，就直接插入在他前面
            for (int j = i-1; j > 0; j--) {
                if (sortArray[i] < after[j]) {
                    after[j+1] = after[j];
                    after[j] = sortArray[i];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(after));
    }
}
