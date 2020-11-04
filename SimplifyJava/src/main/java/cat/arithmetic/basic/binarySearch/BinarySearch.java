package cat.arithmetic.basic.binarySearch;

/**
 * 二分查找算法
 *
 *
 */
public class BinarySearch {

    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 确定边界 [low,high] 需要左右都是闭区间
        while (right >= left) {
            int middle = (right + left) / 2;
            if (nums[middle] == target) {
                // 搜索 mid
                return middle;
            } else if (nums[middle] < target) {
                //搜索 [middle+1,right]
                left = middle + 1;
            } else if (nums[middle] > target) {
                // 搜索 [left,middle-1]
                right = middle - 1;
            }
        }
        return -1;
    }
}
