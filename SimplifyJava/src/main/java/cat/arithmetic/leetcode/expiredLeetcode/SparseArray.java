package cat.arithmetic.leetcode.expiredLeetcode;

public class SparseArray {
    public static void main(String[] args) {

        int[][] arr = init();
        // 初始化数组
        System.out.println("打印原始数组开始=======");
        outPut(arr);
        System.out.println("打印原始数组结束=======");
        // 新数组的第一行是 num,3,max
        //     第二行是值的 行、列、值
        int num = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
               if(arr[i][j] != 0){
                   num ++;
                   if(arr[i][j]>max){
                       max = arr[i][j];
                   }
               }
            }
        }

        int[][] result = new int[num+1][3];
        // 插入稀疏数组的第一列
        result[0][0] = 10;
        result[0][1] = 10;
        result[0][2] = num;

        int count = 1;
        // 再次遍历
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    result[count][0] = i;
                    result[count][1] = j;
                    result[count][2] = arr[i][j];
                    count ++;
                }
            }
        }

        System.out.println("转化稀疏数组开始=======");
        outPut(result);
        System.out.println("转化稀疏数组结束=======");

        // 开始恢复原始数组

    }

    static int[][] init(){
        int[][] arr = new int[10][10];
        arr[2][3] = 2;
        arr[3][4] = 1;
        arr[4][5] = 2;
        arr[1][6] = 1;
        return arr;
    }

    static void outPut(int[][] result){
        // 打印结果
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.printf("%d\t",result[i][j]);
            }
            System.out.println("\n");
        }
    }
}
