package cat.arithmetic.leetcode.dpTag.easy_338_countBits;

/**
 * 338. 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * [0,1,1,2,1,2,2,3,1,
 *
 * i = i-1 + 1
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-06-02
 */
public class Solution {

    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            /**
             * i & (i - 1)可以去掉i最右边的一个1（如果有），
             * 因此 i & (i - 1）是比 i 小的，而且i & (i - 1)的1的个数已经在前面算过了，
             * 所以i的1的个数就是 i & (i - 1)的1的个数加上1
             *
             * todo:完了。。。只有看答案才有思路。。。完了
             */
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}
