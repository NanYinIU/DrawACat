package cat.arithmetic.leetcode.dpTag.middle_139_wordBreak;

import java.util.List;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-06-02
 */
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        // 定义dp
        boolean[] dp = new boolean[n + 1];
        // 初始化dp
        dp[0] = true;
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int wn = word.length();
                if (i >= wn) {
                    dp[i] = dp[i - wn] && s.substring(i - wn, i).equals(word);
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }
        return dp[n];
    }

}
