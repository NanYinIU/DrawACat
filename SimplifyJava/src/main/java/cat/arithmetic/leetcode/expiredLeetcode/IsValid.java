package cat.arithmetic.leetcode.expiredLeetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsValid {

    /**
     * 使用类似进栈出栈的做法，如果最后栈内为空，则说明对应
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        int length = s.length();
        if (length <= 0) {
            return true;
        } else if (length == 1) {
            return false;
        } else {
            // 大于等于2
            if (length % 2 == 1) {
                return false;
            } else {
                char[] arr = s.toCharArray();
                char[] result = new char[arr.length];
                int j = 0;
                for (int i = 0; i < arr.length; i++) {
                    // 先放一个元素进去，第二个元素开始比较前一个元素，如果对应，则j--，否则j++
                    // 如果最后结果j>0 说明元素没有完全对应上，则返回false
                    if (j == 0) {
                        result[j] = arr[i];
                        j++;
                    } else {
                        if (map.get(result[j - 1]) != null && map.get(result[j - 1]) == arr[i]) {
                            j = j - 1;
                        } else {
                            result[j] = arr[i];
                            j++;
                        }
                    }
                }
                if (j > 0) {
                    return false;
                }
                return true;
            }
        }
    }

    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        System.out.println(isValid.isValid("()[]"));
    }
}
