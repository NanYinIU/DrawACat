package cat.arithmetic.leetcode;

/**
 * @author nanyin
 * @class Rotate.java
 * @description 01.07. 旋转矩阵
 * @create 20:30 2020-04-07
 * <p>
 * <p>
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 00,02,22,20
 *
 * 01,12,21,10
 *
 * ij, j(l-i),(l-i)(l-j), (l-j)i
 *
 *
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        // 可以分为四个角,每个角的数递归替换
        int column_lenth = matrix.length - 1;
        for (int i = 0; i < matrix.length / 2 + 1; i++) {
            int row_length = matrix[i].length - 1;
            for (int j = i; j < matrix[i].length - i - i; j++) {
                System.out.println(i + ":" + j);
                int curr = matrix[i][j];
                int right_top = matrix[j][column_lenth - i];
                int right_bottom = matrix[row_length - i][column_lenth - j];
                int left_bottom = matrix[row_length - j][i];
                // 各自进行交换
                matrix[j][column_lenth - i] = curr;
                matrix[row_length - i][column_lenth - j] = right_top;
                matrix[row_length - j][i] = right_bottom;
                matrix[i][j] = left_bottom;
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        Rotate rotate = new Rotate();
        rotate.rotate(board);


    }
}
