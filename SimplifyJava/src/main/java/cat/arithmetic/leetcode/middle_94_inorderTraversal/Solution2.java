package cat.arithmetic.leetcode.middle_94_inorderTraversal;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-17
 */
class Solution2 {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<TreeNode>();

    /********************************
     * 使用迭代的方式
     * 通过循环将元素一直放入栈中，直到没有元素
     * 再一个一个的出栈，输出元素，在递归右侧元素
     ********************************
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {


        while (root !=null || !stack.isEmpty() ){
            while (root !=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }

        return result;
    }
}
