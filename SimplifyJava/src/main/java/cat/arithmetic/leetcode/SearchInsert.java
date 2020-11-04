package cat.arithmetic.leetcode;

import java.util.Arrays;

/**
 * 35. 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInsert {

    /**
     *
     * 第一种做法，添加，排序，获取
     *
     * 先添加，再排序，找到对应位置，效率很低
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert2(int[] nums, int target) {
        int size = nums.length;
        int[] newInts = Arrays.copyOf(nums,size+1);
        newInts[size] = target;
        Arrays.sort(newInts);
        for (int i = 0; i < newInts.length; i++) {
            if(newInts[i] == target){
                return i;
            }
        }
        return 0;
    }

    /**
     * 第二种做法：直接遍历，利用本来就有序的特性
     *
     * 因为本身就是从大到小的顺序排列，
     * 所以直接找到大于等于target的index，则是确定的index。
     *
     * 1. 第一部分，只要判断到了大于等于target的地方，则直接返回index
     * 2. 第二部分，如果全部都小于target，则返回 nums.length
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 第三种做法：使用二分的方式
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert3(int[] nums, int target) {
        return searchInsert3(nums, target,0,nums.length-1);
    }

    public int searchInsert3(int[] nums, int target,int left,int right){
        if(right >= left){
            int middle = (left + right)/2;
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle] > target){
                // 单独判断middle到达左边界
                if(middle == 0){
                    return 0;
                }
                if(nums[middle - 1] < target){
                    return middle;
                }
                return searchInsert3(nums, target,left,middle-1);
            }else if (nums[middle] < target){
                // 单独判断middle到达右边界
                if(middle == nums.length -1){
                    return nums.length;
                }
                if(nums[middle + 1] > target){
                    return middle + 1;
                }
                return searchInsert3(nums,target,middle + 1,right);
            }
        }
        return nums.length;
    }



    public static void main(String[] args) {
        int[] ints = new int[]{1,2,5,8};
        SearchInsert searchInsert = new SearchInsert();
        System.out.println(searchInsert.searchInsert3(ints,0));
    }
}
