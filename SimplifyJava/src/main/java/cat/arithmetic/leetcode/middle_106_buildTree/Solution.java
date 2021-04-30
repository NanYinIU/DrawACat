package cat.arithmetic.leetcode.middle_106_buildTree;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-30
 */
public class Solution {

    Map<Integer,Integer> memo = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length < 1) {
            return null;
        }
        // 找到下一次递归的左树的范围和右树的范围
        for (int i = 0; i < inorder.length; i++) {
            memo.put(inorder[i],i);
        }

        return buildTreeHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] inorder, int i_start, int i_end,
                                    int[] postorder, int p_start, int p_end) {
        if(i_start > i_end || p_start > p_end){
            return null;
        }
        // 后序的最后的一个节点是根节点
        int rootVal = postorder[p_end - 1];
        TreeNode root = new TreeNode(rootVal);
        int root_index = memo.get(rootVal);
        System.out.println(root_index);
        int le = root_index - i_start;
        System.out.println(le);
        root.left = buildTreeHelper(inorder, i_start, root_index - 1, postorder, p_start, p_start + le - 1);
        root.right = buildTreeHelper(inorder, root_index + 1, root_index + le , postorder, p_start + le + 1, p_end - 1);
        return root;
    }


}
