package cat.arithmetic.leetcode.tree;

/**
 * 树节点的数据结构
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-17
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
