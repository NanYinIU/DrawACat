package cat.arithmetic.leetcode.easy_226_invertTree;

import cat.arithmetic.leetcode.tree.TreeNode;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;

/**
 * 226. 翻转二叉树
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-13
 */
public class InvertTree {


    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    /**
     * 递归实现，实现节点的左右两个子节点互相交换，从而实现翻转
     *
     * 使用前序遍历，在递归遍历根节点的左节点、右节点
     *
     * @param root
     */
    public void invert(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;


        TreeNode temp = right;
        root.right = left;
        root.left = temp;

        invertTree(left);
        invertTree(right);
    }


}
