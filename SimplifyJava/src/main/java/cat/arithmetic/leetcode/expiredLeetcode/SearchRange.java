package cat.arithmetic.leetcode.expiredLeetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SearchRange {
    /**
     *
     * 最简单直接方法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target){
                result[0] = i;
                break;
            }
        }
        for (int i = nums.length-1; i >= 0 ; i--) {
            if(nums[i] == target){
                result[1] = i;
                break;
            }
        }
        return result;
    }

    /**
     * 尝试使用二分的思想解决
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        int left = extremeIndex(nums, target, true);


        if (left == nums.length || nums[left] != target) {
            return result;
        }

        result[0] = left;
        result[1] = extremeIndex(nums, target, false)-1;

        return result;
    }

    private int extremeIndex(int[] nums, int target, boolean left) {
        int low = 0;
        int high = nums.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                high = mid;
            }
            else {
                low = mid+1;
            }
        }
        return low;
    }


    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        int[] ints = new int[]{2,5};
        int[] ints1 = searchRange.searchRange(ints, 5);
        for (int i : ints1) {
            System.out.println(i);
        }
    }
}
