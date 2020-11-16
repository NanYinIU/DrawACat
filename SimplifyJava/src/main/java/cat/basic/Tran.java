package cat.basic;

/**
 * fengtao.yft@alibaba-inc.com
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-02
 */
public class Tran {

    /**
     * 规范到9
     */
    static String CHA[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    /**
     * 规范到亿，后面使用百亿、千亿
     */
    static String UN[] = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿"};

    static final String MINUS = "负";

    /**
     * @param tr int 最大值是到百亿
     * @return 中文数字
     */
    public static String tran(int tr) {
        // 标记是否为负数，如果是负数，使用负号添加在最前面
        boolean mark = true;
        if (tr < 0) {
            tr = -tr;
            mark = false;
        }
        char[] trArr = String.valueOf(tr).toCharArray();

        StringBuilder sb = new StringBuilder();

        // 单独处理负数情况
        if (!mark) {
            sb.append(MINUS);
        }

        int length = trArr.length;
        int unLength = UN.length;

        for (int j = mark ? 0 : 1; j < length; j++) {
            String m = trArr[j] + "";
            int n = Integer.parseInt(m);
            boolean isZero = n == 0;
            String unHead = "";
            String un = "";
            // 考虑超出亿的情况
            if (length - 1 - j >= unLength) {
                int head = length - 1 - j - unLength;
                unHead = UN[head + 1];
                un = UN[unLength - 1];
            } else {
                un = UN[(length - 1 - j)];
            }

            if (isZero) {
                // 单独写，做判断条件，处理末尾为0的情况
                if (trArr[length - 1] == '0') {
                    continue;
                    // 处理中间多个零的情况
                } else if (j > 1 && (trArr[j - 1] == '0')) {
                    continue;
                } else {
                    sb.append(CHA[n]);
                }
            } else {
                // 如果单位没有超出，需要数字，否则为单位+单位组合 如 百亿、万亿
                if ("".equals(unHead)) {
                    sb.append(CHA[n]);
                }
                sb.append(unHead);
                sb.append(un);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // 测试用例
        System.out.println(Tran.tran(0));

        System.out.println(Tran.tran(Integer.MAX_VALUE));
        System.out.println(Tran.tran(Integer.MIN_VALUE));

        System.out.println(Tran.tran(10));
        System.out.println(Tran.tran(100));
        System.out.println(Tran.tran(1000));
        System.out.println(Tran.tran(10000));
        System.out.println(Tran.tran(100000));
        System.out.println(Tran.tran(1000000));
        System.out.println(Tran.tran(10000000));
        System.out.println(Tran.tran(100000000));
        System.out.println(Tran.tran(1000000000));

        System.out.println(Tran.tran(1000010001));
        System.out.println(Tran.tran(1000100010));
        System.out.println(Tran.tran(1001000100));
        System.out.println(Tran.tran(1010001000));
        System.out.println(Tran.tran(1100010000));


    }
}
