package cat.arithmetic.leetcode;

/**
 * 9. 回文数
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class IsPalindrome {

    /**
     * 暴力拼接 比较 原来的拼接后的
     * @param x
     * @return
     */
    public boolean solution(int x){
        if(x < 0){
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String org = Integer.toString(x);
        for (int i = org.length(); i > 0; i--) {
            stringBuilder.append(org.charAt(i-1));
        }
        return stringBuilder.toString().equals(org);
    }

    /**
     * 前后互相对比
     * @param x
     * @return
     */
    public boolean solution2(int x){
        if(x < 0){
            return false;
        }
        String org = Integer.toString(x);
        for (int i = 0; i < org.length()/2 + 1; i++) {
           if(org.charAt(i) != org.charAt(org.length()-i-1)) {
               return false;
           }
        }
        return true;
    }

    public boolean solution3(int x){
        if(x < 0|| (x % 10 == 0 && x != 0)){
            return false;
        }
        //1221  revertedNumber 应为21反转后的12
        int revertedNumber = 0;
        while (x > revertedNumber){
            // 第一次 1 第二次应为 1*10 + 2
            revertedNumber=revertedNumber*10 + x%10;
            // 每次x递减
           x/=10;
        }
        return x==revertedNumber || x==revertedNumber/10;

    }


    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.solution3(1212));
        System.out.println(122%10);
    }
}
