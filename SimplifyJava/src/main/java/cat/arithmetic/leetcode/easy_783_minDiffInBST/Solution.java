package cat.arithmetic.leetcode.easy_783_minDiffInBST;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 105
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-13
 */
public class Solution {

    private int minVal = Integer.MAX_VALUE;
    private List<Integer> dp = new ArrayList<>();

    /**
     * 错误理解，理解为相连两节点差最小
    public int minDiffInBST(TreeNode root) {
        if (root.left != null) {
            int leftVal = Math.min(Math.abs(root.val- root.left.val), minDiffInBST(root.left));
            if (minVal >= leftVal) {
                minVal = leftVal;
            }
        }
        if (root.right != null) {
            int rightVal = Math.min(Math.abs(root.val- root.right.val), minDiffInBST(root.right));
            if (minVal >= rightVal) {
                minVal = rightVal;
            }
        }
        return minVal;
    }
    */

    /**
     * 前序遍历
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return minVal;
        }
        int val = root.val;
        for (Integer d : dp) {
            minVal = Math.min(Math.abs(val - d), minVal);
        }
        dp.add(val);

        minDiffInBST(root.left);
        minDiffInBST(root.right);

        return minVal;
    }

}
