package cat.arithmetic.leetcode.dpTag.middle_97_isInterleave;

/**
 * 97. 交错字符串
 * <p>
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * <p>
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-31
 */
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if (n1 == 0 && n2 == 0 && n3 == 0) {
            return true;
        }
        if (n3 != n1 + n2) {
            return false;
        }
        boolean[] dp = new boolean[n3];
        for (int i = 0; i < n3; i++) {
            dp[i] = false;
        }
        int index1 = 0, index2 = 0;
        for (int i = 0; i < n3 - 1; i++) {
            boolean b1 = s3.charAt(i) == s1.charAt(index1);
            boolean b2 = s3.charAt(i) == s2.charAt(index2);
            if (b1) {
                index1++;
            } else if (b2) {
                index2++;
            }
            if (i == 0) {
                dp[i] = b1 || b2;
            }
            dp[i + 1] = dp[i] && (b1 || b2);
            if (!dp[i + 1]) {
                System.out.println(i + "&&index1==" + index1 + "&&b1=" + b1 + "&&b2=" + b2 + "&&index2==" + index2 + "&&dp[i-1]" + dp[i]);
                return false;
            }
        }
        return true;
    }
}
