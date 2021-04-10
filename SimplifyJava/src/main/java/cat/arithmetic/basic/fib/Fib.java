package cat.arithmetic.basic.fib;

/**
 * 斐波那契数列
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-10
 */
public class Fib {

    public static void main(String[] args) {

        int target = 10;
        System.out.println(fib(target));


        init(target);
        System.out.println(fib2(mem, target));

        System.out.println(fib3(target));

        System.out.println(fib4(target));
    }

    /**
     * ===============暴力递归=============
     * 时间复杂度 o(2^n)
     *
     * @param value
     * @return
     */
    private static int fib(int value) {
        if (value == 1 || value == 2) {
            return 1;
        }
        return fib(value - 1) + fib(value - 2);
    }

    /**
     * ===============备忘录===============
     * 时间复杂度 o(n)
     */
    private static int[] mem;

    private static void init(int value) {
        mem = new int[value + 1];
    }

    private static int fib2(int[] mem, int value) {
        if (value == 1 || value == 2) {
            return 1;
        }
        if (mem[value] != 0) {
            return mem[value];
        }
        int result = fib2(mem, value - 1) + fib2(mem, value - 2);
        mem[value] = result;
        return result;
    }

    /**
     * 动态规化问题解决方案
     * <p>
     * 1. 明确base case
     * 2. 找出状态转移方程
     * <p>
     * 明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义
     *
     * @param value
     * @return
     */

    private static int fib3(int value) {
        int[] dp = new int[value + 1];

        dp[0] = dp[1] = dp[2] = 1;

        for (int i = 3; i <= value; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[value];
    }

    /**
     * 观察状态之后，发现实际不用dp数组就能够实现，空间复杂度o(1)
     *
     * @param value
     * @return
     */
    private static int fib4(int value) {
        int a = 1;
        int b = 1;
        if (value <= 2) {
            return 1;
        }

        for (int i = 3; i <= value; i++) {
            int temp = a;
            a = a + b;
            b = temp;
        }
        return a;
    }
}
