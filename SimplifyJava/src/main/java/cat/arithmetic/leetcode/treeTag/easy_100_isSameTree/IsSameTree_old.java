package cat.arithmetic.leetcode.treeTag.easy_100_isSameTree;

import cat.arithmetic.leetcode.treeTag.TreeNode;

/**
 * 100. 相同的树
 * <p>
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-17
 */
public class IsSameTree_old {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
