package cat.arithmetic.leetcode.stringTag.middle_3_lengthOfLongestSubstring;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-21
 */
class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> window = new HashMap<Character, Integer>();
        int left = 0;
        int result = 0;
        for (int index = 0; index < s.length(); index++) {
            if (window.containsKey(s.charAt(index))) {
                left = Math.max(left, window.get(s.charAt(index)) + 1);
            }
            window.put(s.charAt(index), index);
            result = Math.max(result, index - left + 1);
        }
        return result;
    }
}