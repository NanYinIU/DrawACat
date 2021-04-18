package cat.arithmetic.leetcode.middle_322_coinChange;

import java.util.Arrays;

/**
 *
 * 322. 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-12
 */
public class Solution2 {


    int[] mem = null;

    /****************************************
     * 带备忘录的递归，类似斐波那契数列的解决方式，自
     * 顶向下。
     ****************************************
     * @param coins 选择
     * @param amount 变化的状态
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        mem = new int[amount + 1];
        Arrays.fill(mem, amount + 1);
        // 判断边界条件
        return doCoinChange(coins, amount);
    }

    public int doCoinChange(int[] coins, int amount) {
        // 判断边界条件

        return -1;
    }

}
