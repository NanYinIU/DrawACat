package cat.arithmetic.leetcode;

/**
 * @author nanyin
 * @class Trap.java
 * @description 42. 接雨水
 * @create 22:19 2020-04-04
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Trap {
    /**
     * @title: trap
     * @description 标准暴力解法，复杂度O(n)2,仍有其他解法待实现，如双指针，或创建新list缓存left和right的方式 todo
     * @param height
     * @return int
     * @author: nanyin
     * @update: 16:38 2020-04-05
     */
    public int trap(int[] height) {
        int result = 0;
        for (int i = 1; i < height.length; i++) {
            int left = 0;
            int right = 0;
            for (int j = i - 1; j >= 0; j --) {
                if(height[j] > left){
                    left = height[j];
                }
            }
            for (int j = i + 1; j < height.length; j ++) {
                if(height[j] > right){
                    right = height[j];
                }
            }
            //计算值
            int stand = Math.min(left,right);
            if(stand > height[i]){
                // 算出当前位置和最小的高度的差值，记为当前i位置的填装雨水
                result = result + ( stand - height[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        int[] ints = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap.trap(ints));
    }
}
