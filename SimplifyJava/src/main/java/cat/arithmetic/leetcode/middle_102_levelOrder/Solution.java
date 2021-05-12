package cat.arithmetic.leetcode.middle_102_levelOrder;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 102. 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-28
 */
public class Solution {
    /**
     * 层序遍历的思路是使用队列存储元素，当一层结束的时候将首节点弹出，将子节点放入队列中。
     * 难点在于如何判断一层结束？思路是做标记，标记一层的结束位置；找到弹出节点时，标记下一层结束的队列的长度，
     * 这个长度就是下一层开始的位置。接下来就是遍历这一层，将这一层的子节点放到队列中。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // basecase
        if(root == null){
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while(size > 0){
                TreeNode node = queue.poll();
                if(node == null){
                    continue;
                }
                temp.add(node.val);
                if(node.left !=null){
                    queue.add(node.left);
                }
                if(node.right !=null){
                    queue.add(node.right);
                }
                size --;
            }
            result.add(temp);
        }
        return result;
    }

}
