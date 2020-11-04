package cat.arithmetic.leetcode;

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 *
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        // 记录慢指针
        int start = 0;
        for (int num : nums) {
            if(num != val){
               nums[start] = num;
                start++;
            }
        }
//        nums = Arrays.copyOf(nums,start);
        for (int num : nums) {
            System.out.println(num);
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,1,1,1,2,2,3,3,4};
        RemoveElement removeElement = new RemoveElement();
        removeElement.removeElement(arr,2);
    }
}
