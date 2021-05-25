package cat.arithmetic.leetcode.dpTag.middle_4_longestPalindrome;

/**
 * 5. 最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-24
 */
public class Solution {
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int n = s.length();
        int start = 0;
        int maxLength = 0;
        // 定义dp数组
        boolean[][] dp = new boolean[n][n];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        char[] arrays = s.toCharArray();
        for (int left = 2; left < n; left++) {
            for (int j = 0; j < n; j++) {
                int right = left + j - 1;
                if (right > n) {
                    break;
                }
                if (arrays[left] != arrays[right]) {
                    dp[left][right] = false;
                } else {
                    if (right - left <= 2) {
                        dp[left][right] = true;
                    } else {
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                }
                if (dp[left][right] && right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    start = left;
                }
            }
        }
        maxLength = Math.max(s.length(), maxLength);
        return s.substring(start, start + maxLength);
    }
}
