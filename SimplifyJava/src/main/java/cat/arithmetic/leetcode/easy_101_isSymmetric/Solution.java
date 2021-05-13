package cat.arithmetic.leetcode.easy_101_isSymmetric;

import cat.arithmetic.leetcode.tree.TreeNode;

/**
 * 101. 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-13
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSy(root.left,root.right);
    }

    public boolean isSy(TreeNode left,TreeNode right){
        if(left == null && right !=null || left !=null && right == null){
            return false;
        }
        if(left == null && right == null){
            return true;
        }
        return isSy(left.left,right.right) && isSy(left.right,right.left) && left.val == right.val;
    }

}
