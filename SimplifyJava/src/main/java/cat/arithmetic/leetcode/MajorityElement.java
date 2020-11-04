package cat.arithmetic.leetcode;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityElement {
    // 排序后取中值
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    /**
     * 候选数法
     * 遇到相同🉐的数+1，遇到不同的数-1，如果count==0，则更换候选数，
     * 最后剩下的一定是多出来的数
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int num = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                num = nums[i];
            }
            if (num == nums[i]) {
                count++;
            } else {
                count--;
            }

        }
        return num;
    }

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        int[] ints = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement.majorityElement(ints));
        System.out.println(majorityElement.majorityElement2(ints));
    }
}
