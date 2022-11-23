package cat.arithmetic.basic.bubbleSort;

/**
 * 排序算法之一 冒泡排序 其中原理就是 冒泡排序是稳定的排序算法 它会遍历若干次要排序的数列，逐一比较前后两个值的大小 大的放在后面，小的排在前面，以此类推
 * 这样，一次遍历之后，最大的元素就在数列的末尾！ 采用相同的方法再次遍历时，第二大的元素就被排列在最大元素之前。重复此操作，直到整个数列都有序为止！
 * 下面的实现的是最基础的冒泡排序 因为冒泡排序要走n趟，可能再n趟之前就已将排好序了，其实我们就应该考虑如何再排序完成的时候立即知道，然后退出外层循环。
 * 可以考虑使用flag标志，详细可见
 * <p>
 * for(int i = 0 ; i < sortArray.length ; i++ ){ boolean flag = true; for(int j
 * = 0 ; j < sortArray.length-1; j++){ swap(sortArray,j,j+1); flag = false; }
 * if(flag == true){ break; } } 其实再理解原理之后代码就非常容易实现了。
 */
public class Bubble {
    public static void main(String[] args) {
        Integer[] sortArray = new Integer[] { 1, 3, 2, 5, 4, 9, 7, 6, 8 };
        // 由前向后排
        for (int i = 0; i < sortArray.length; i++) {
            boolean flag = true;
            for (int j = 0; j < sortArray.length - i - 1; j++) {
                // 1,2 位置比较 大的放后面，2，3位置比较，大的放后面，以此类推，一轮下来，最大的放在最后面，
                // 下一轮只用比较 【length - 已确定】次数
                flag = swap(sortArray, j, j + 1);
            }
            if (flag) {
                break;
            }
        }
        // 打印查看是否排好序
        for (int x : sortArray) {
            System.out.println(x);
        }
    }

    /**
     * 这个函数来比较前后两个数组内的数的大小
     *
     * @param array
     * @param i
     * @param j
     */
    public static boolean swap(Integer[] array, int i, int j) {
        if (array[i] > array[j]) {
            // 如果前一个数大于后面的一个数,两个数交换。否则不做任何事情
            int x = array[i];
            array[i] = array[j];
            array[j] = x;
            return true;
        } else
            return false;
    }
}
