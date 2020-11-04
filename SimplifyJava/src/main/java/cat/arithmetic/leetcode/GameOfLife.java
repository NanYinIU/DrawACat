package cat.arithmetic.leetcode;

/**
 * @author nanyin
 * @class GameOfLife.java
 * @description 289. 生命游戏
 * @create 14:30 2020-04-02
 * <p>
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； <2 = 0
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；=2||=3 =1
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；>3 = 0
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活； =3 =1
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * 示例：
 * [a-1,b-1], [a-1,b],[a-1,b+1]
 * [a,b]
 * <p>
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GameOfLife {

    public void gameOfLife(int[][] board) {
        int row = board.length;
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int i = 0; i < row; i++) {
            int[] cur_row = board[i];
            int col = cur_row.length;
            for (int j = 0; j < col; j++) {
                // 获得当前细胞状态
                int status = board[i][j];
                // 周围存活数计量
                int count = 0;
                // 获得周围细胞状态
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if ((x >= 0 && x < row) && (y >= 0 && y < col)) {
                        if (board[x][y] == 1 || board[x][y] == -1) {
                            count+=1;
                        }
                    }
                }
                if (status == 1 && (count < 2 || count > 3)) {
                    // 细胞死亡
                    board[i][j] = -1;
                }
                if (status == 0 && count == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
            }
        }


    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.gameOfLife(board);
        int row = board.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }
}
