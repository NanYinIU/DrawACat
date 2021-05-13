package cat.arithmetic.leetcode.expiredLeetcode;

/**
 *
 * 33. 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Search {

    /**
     * [4,5,6,7,   0,1,2]
     * @param nums
     * @param target
     * @return
     *
     * low middle high
     * 4 6 2
     * 6 2
     * 0
     */
    public int search(int[] nums, int target) {
        return search(nums,target,0,nums.length-1);
    }

    private int search(int[] num,int target,int low,int high){
        if(high == low){
            if(num[high] == target){
                return high;
            }else{
                return -1;
            }
        }
        if(high - low > 0){
            int middle = (high + low)/2;
            if(num[middle] == target){
                return middle;
                // 如果 middle < high
            }else if(num[middle] < num[high]){
                // 如果 middle < target <= high
                if(num[middle] < target && num[high] >= target){
                    return search(num,target,middle+1,high);
                }else{
                    // 否则 target 在 low 和 middle 之间
                    return search(num,target,low,middle-1);
                }
            }else{
                // 如果 middle >= high
                // 如果 low <= target < middle
                if (num[low] <= target && target < num[middle]) {
                    return search(num, target,low , middle-1 );
                } else{
                    // 否则  target 在 middle  和 high 之间
                    return search(num,target ,middle+1 , high);
                }
            }
        }
        return -1;
    }


    /**
     * [4,5,6,7,   0,1,2]
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int find = -1;
        for (int num : nums) {
            find++;
            if(num == target){
                return find;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{3,5,1};
        Search search = new Search();
        System.out.println(search.search(ints,3));
    }
}
