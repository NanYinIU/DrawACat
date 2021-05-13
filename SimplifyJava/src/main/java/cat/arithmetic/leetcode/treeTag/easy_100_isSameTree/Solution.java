package cat.arithmetic.leetcode.treeTag.easy_100_isSameTree;

import cat.arithmetic.leetcode.treeTag.TreeNode;

/**
 * 100. 相同的树
 *
 *
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 *  
 * 示例 1：
 *
 *
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 *
 *
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-15
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 总结边界条件
        if ((p != null && q == null) || (p == null && q != null)
                || (p != null && q != null && p.val != q.val)) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        // 递归调用，但是这样效率很低，TODO:如何提高效率？
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
