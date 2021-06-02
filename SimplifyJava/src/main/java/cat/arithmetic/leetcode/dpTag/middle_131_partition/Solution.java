package cat.arithmetic.leetcode.dpTag.middle_131_partition;

import java.util.Collections;
import java.util.List;

/**
 * 131. 分割回文串
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-06-02
 */
public class Solution {
    public List<List<String>> partition(String s) {
        if(s.length() == 0){
            return Collections.EMPTY_LIST;
        }


        // TODO: 2021/6/2 直接放弃！！！！
        return Collections.EMPTY_LIST;
    }
}
