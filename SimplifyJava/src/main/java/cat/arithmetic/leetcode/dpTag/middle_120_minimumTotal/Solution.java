package cat.arithmetic.leetcode.dpTag.middle_120_minimumTotal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *  
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *  
 *
 * 进阶：
 *
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-06-01
 */
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int level = triangle.size();
        if (level == 0) return 0;
        int[][] dp = new int[level][level];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < level; i++) {
            List<Integer> current = triangle.get(i);
            for (int j = 0; j < current.size(); j++) {
                if (j == 0) {
                    // 最左边，找上一层的正上方的元素
                    dp[i][j] = dp[i - 1][j] + current.get(j);
                } else if (j == current.size() - 1) {
                    // 最右边，找上一层的正上方-1的元素
                    dp[i][j] = dp[i - 1][j - 1] + current.get(j);
                } else {
                    // 找到上方两个元素其中最小的
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + current.get(j);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < level; i++) {
            if (dp[level - 1][i] < result) {
                result = dp[level - 1][i];
            }
        }
        return result;
    }
}
