package cat.arithmetic.leetcode.dpTag.easy_10_1_fib;

import java.util.Collections;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。
 *
 * 斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *  
 *
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-24
 */
public class Solution {
    private int num = 1000000007;

    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        // 声明DP数组
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n;i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fib(4));
    }
}
