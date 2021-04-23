package cat.arithmetic.basic.treeSearch;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 迭代的方式遍历树
 *
 * 其实递归是系统给默认生成了一个栈，而自己实现
 * 迭代递归树，无非是多添加栈结构，循环实现
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-23
 */
public class Iterative {


    /**
     * 前序遍历
     *
     * 前序就是针对每一个子树，都先输出根节点、左节点、右节点
     *
     * 1 2 3
     * 栈内：2进栈 1进栈 1出栈 2 出栈 3进栈 3出栈
     *
     */

    public List<Integer> preorderTraversal(TreeNode root) {
        return null;
    }

    /**
     * 中序遍历
     * 中序就是针对每一个子树，都先输出左节点、根节点、右节点
     * 使用迭代的方式就是，遇到root不为null压栈，否则判断栈是否为空，输出元素，
     * 设为弹出节点的右节点，直至遍历结束
     *
     */
    private List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else if (!stack.empty()) {
                TreeNode node = stack.pop();
                result.add(node.val);
                root = node.right;
            }
        }
        return result;
    }

    /**
     * 后序遍历
     * 后序遍历就是针对每一个子树，都先输出左节点、右节点、根节点
     */
    private void postOrder(TreeNode root) {

    }


}
