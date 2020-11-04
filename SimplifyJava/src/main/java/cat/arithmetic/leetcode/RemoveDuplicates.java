package cat.arithmetic.leetcode;

import java.util.Arrays;

/**
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int start = 0,temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i == 0 || temp != nums[i]){
                // 不想等
                temp = nums[i];
                // 将但前元素放到上一个start的位置上
                nums[start] = nums[i];
                // 记录start的为当前位置
                start ++;
            }
        }
        nums = Arrays.copyOf(nums,start);
        for (int num : nums) {
            System.out.println(num);
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,1,1,1,2,2,3,3,4};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        System.out.println(removeDuplicates.removeDuplicates(arr));
    }
}
