package cat.arithmetic.leetcode.treeTag.middle_103_zigzagLevelOrder;

import cat.arithmetic.leetcode.treeTag.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. 二叉树的锯齿形层序遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @see cat.arithmetic.leetcode.treeTag.middle_102_levelOrder.Solution 二叉树的层序遍历
 * @see cat.arithmetic.leetcode.treeTag.middle_107_levelOrderBottom.Solution 二叉树的层序遍历，由底向上
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-14
 */
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean order = true;
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> temp = new LinkedList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                // 锯齿就是放入顺序一直在变而已
                if (node == null) {
                    continue;
                }
                temp.add(node.val);
                if (order) {
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                } else {
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                size--;
            }
            order = !order;
            result.add(temp);
        }
        return result;
    }
}
