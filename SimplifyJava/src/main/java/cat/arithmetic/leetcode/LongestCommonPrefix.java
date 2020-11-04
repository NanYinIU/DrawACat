package cat.arithmetic.leetcode;

/**
 * 寻找最长的公共前缀：
 * 最长的公共前缀的基本条件是小于所有字符串的最小值！
 * 对第一个字符串进行循环
 * 1. 对每个单词循环，依次比较单词的对应位置上的字母是否相同，如果比相同，直接return前面的字符
 * 2. 如果循环时发现有个字符串的长度小于i时，则直接返回这个小的字符串，因为它是最小的了，比如循环到 index 为 4，发现 有个字符的长度正好为4
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length > 0){
            // 获取第一个单词的状态
            String first = strs[0];
            char[] firstArr = first.toCharArray();
            int min = first.length();
            for (int i = 0; i < min; i++) {
                // 对所有单词循环
                for (String str : strs) {
                    if(str.length() <= i){
                        return str;
                    }
                   if(str.charAt(i)!=firstArr[i]){
                      return first.substring(0,i);
                   }
                }
            }
            return first;
        }
        return "";
    }

    public static void main(String[] args) {
//        String[] arr = new String[]{"flower","flow","flight"};
        String[] arr = new String[]{"aaa","aa","a"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(arr));
    }
}
