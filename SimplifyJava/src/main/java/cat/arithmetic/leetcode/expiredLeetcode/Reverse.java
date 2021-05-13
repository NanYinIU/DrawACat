package cat.arithmetic.leetcode.expiredLeetcode;


public class Reverse {
    public int solution(int x){
        int flag = 1;
        int trueNumber = x;
        if(x < 0){
            flag = -1;
            trueNumber = x * -1;
        }
        String y = Integer.valueOf(trueNumber).toString();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = y.length(); i > 0; i--) {
            stringBuilder.append(y.charAt(i-1)) ;
        }

        return Integer.parseInt(stringBuilder.toString()) * flag;
    }

    public int solution2(int x){
        int flag = 1;
        if(x < 0){
            flag = -1;
            x = x * -1;
        }
        double result = 0;
        while(x > 0){
            int p = x % 10;
            result = result * 10 + p;
            x = x/10;
        }
        result = result * flag;
        if(result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE){
            return 0;
        }
        return (int)result ;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        System.out.println(reverse.solution2(12));
        /**
         * 1244
         * 1+2*10+4+10*10+4*10*10*10
         */
        System.out.println(12%100);
    }
}
