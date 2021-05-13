package cat.arithmetic.leetcode.expiredLeetcode;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        if("".equals(digits)){
            return  new ArrayList<>();
        }
        return combinations(digits.substring(digits.length()-1),digits,1);
    }

    public List<String> combinations(String next,String digits,int i){
        List<String> result = new ArrayList<>();
        Map<String,char[]> map = init();
        char[] chars = map.get(next);
        if(i < digits.length()){
            for (char aChar : chars) {
                List<String> list1 = combinations(digits.substring(digits.length()-i-1,digits.length()-i),digits,i+1);
                for (String s : list1) {
                    s = s + aChar;
                    result.add(s);
                }
            }
        }else{
            for (char aChar : chars) {
                result.add(""+aChar);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> strings = letterCombinations.letterCombinations("234");
        for (String string : strings) {
            System.out.println(string);
        }
        Set<Integer> set = new HashSet<>();
        Iterator<Integer> iterator = set.iterator();
    }

    private Map<String,char[]> init(){
        Map<String,char[]> map = new HashMap<>();
        map.put("2",new char[]{'a','b','c'});
        map.put("3",new char[]{'d','e','f'});
        map.put("4",new char[]{'g','h','i'});
        map.put("5",new char[]{'j','k','l'});
        map.put("6",new char[]{'m','n','o'});
        map.put("7",new char[]{'p','q','r','s'});
        map.put("8",new char[]{'t','u','v'});
        map.put("9",new char[]{'w','x','y','z'});
        return map;
    }
}
