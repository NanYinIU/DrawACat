package cat.arithmetic.leetcode.treeTag.middle_105_buildTree;

import cat.arithmetic.leetcode.treeTag.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 1.根据前序知道3是根节点
 * 2.根据中序遍历知道9是左节点、[15,20,7]是右节点
 *
 * 第一次：root_index=1
 * 前：[3] [9] [20,15,7]
 * 中：[9] [3] [15,20,7]
 *
 * 下一次：
 * 左节点：
 * 前从为p_start+1开始、到p_start + (root_index - i_start)结束
 * 中从为p_start开始，到root_index
 *
 * 右节点：
 * 前从为root_index-i_start+1开始到p_end结束
 * 中从root_index+1开始，到i_end结束
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-21
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    public TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end,
                                    int[] inorder, int i_start, int i_end) {

        if (p_start == p_end) {
            return null;
        }
        // 前序的第一个节点是当前的根
        int rootVal = preorder[p_start];
        int root_index = 0;
        // 遍历中序序列，找到根的值的index
        for (int i = i_start; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                // 根节点左侧是左子树
                root_index = i;
                break;
            }
        }
        int left_num = root_index - i_start;
        TreeNode root = new TreeNode(rootVal);
        // TODO：这里需要研究下
        TreeNode left = buildTreeHelper(preorder, p_start + 1, p_start + left_num + 1, inorder, i_start, root_index);
        TreeNode right = buildTreeHelper(preorder, p_start + left_num + 1, p_end, inorder, root_index + 1, i_end);
        root.right = right;
        root.left = left;
        return root;
    }

}
