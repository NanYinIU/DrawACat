package cat.arithmetic.leetcode.tree;

/**
 * 树节点的数据结构
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-17
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
