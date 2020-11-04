package cat.arithmetic.leetcode;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 比如：输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 */
public class LongestPalindrome {

    /**
     * 动态规划
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        if(s.length() > 1){
            //构造二维数组，对应a[i][j] 表示最大公共字串的长度
            int length = origin.length();
            int[][] arr = new int[length][length];
            int maxLen = 0;
            int maxEnd = 0;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    // 判断初始状态
                    if(origin.charAt(i) == reverse.charAt(j)){
                        if(i==0 || j==0){
                            // 是否初次匹配
                            arr[i][j] = 1;
                        }else{
                            arr[i][j] = arr[i-1][j-1] + 1;
                        }
                    }
//                    if(arr[i][j] > maxLen){
//                        maxLen = arr[i][j];
//                        maxEnd = i;
//                    }
                    //还有一种特殊情况 获取最大公共字串的时候无法避免 abc12cba,没有最大公共字串
                    // 但如果不判断，最大字串就是abc或者cba，如何避免这种情况？
                    if(arr[i][j] > maxLen){
                        int beforeRev = length - 1 - j; //获得反转前位置
                        //   a b c b
                        // b
                        // c
                        // b
                        // a
                        //判断下标是否对应,如 横向的 b 出现的位置就是 4-1-1，也就是 第二个位置（0开始）
                        // 如果 反转前位置 + 公共字串的位置 - 1 = 当前位置（反转后），那么说明排除上面的情况
                        if (beforeRev + arr[i][j] - 1 == i) {
                            maxLen = arr[i][j];
                            maxEnd = i;
                        }
                    }
                }
            }
            return s.substring(maxEnd - maxLen + 1,maxEnd+1);
        }else{
            return s;
        }
//         作者：windliang
//         链接：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-bao-gu/
//         来源：力扣（LeetCode）
//         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("cbbd"));
//        String s = "tattarrattat";
//        System.out.println(s.length()%2);
    }

    public String solution(String s){
        if (s.length() <= 1) {
            return s;
        } else {
            String result = s.substring(0,1);
            if(s.length()%2==0){
                // 偶数个 1 2 3 4
                for (int i = 1; i <= s.length() / 2; i++) {
                    if(s.charAt(i-1) == s.charAt(s.length() - i)){
                        String res = s.substring(i-1, s.length() - i +1);
                        if (result.length() < res.length()) {
                            result = res;
                        }
                    }
                }
                return result;
            }else{
                //奇数个
                for (int i = 1; i <= s.length() - 1; i++) {
                    // 假设中位数为 1，需要左右必须相等
                    int j = i - 1, k = i + 1;
                    for (; j > 0 && k <= s.length(); j--, k++) {
                        if (s.charAt(j-1) == s.charAt(k-1)) {
                            String res = "";
                            res = s.substring(j-1, k);
                            if (result.length() < res.length()) {
                                result = res;
                            }
                        } else {
                            break;
                        }
                    }
                }
                return result;
            }
        }
    }
}
