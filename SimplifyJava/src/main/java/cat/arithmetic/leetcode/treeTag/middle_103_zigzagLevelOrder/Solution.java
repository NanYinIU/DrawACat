package cat.arithmetic.leetcode.treeTag.middle_103_zigzagLevelOrder;

import cat.arithmetic.leetcode.treeTag.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. 二叉树的锯齿形层序遍历
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-14
 * @see cat.arithmetic.leetcode.treeTag.middle_102_levelOrder.Solution 二叉树的层序遍历
 * @see cat.arithmetic.leetcode.treeTag.middle_107_levelOrderBottom.Solution 二叉树的层序遍历，由底向上
 */
class Solution {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        //用linklist，整个作为队列遍历树，前半部分看成栈1，后半部分看成栈2.做到每一层的节点依次放到不同栈中。
        Deque<TreeNode> dequeue = new LinkedList<>();
        dequeue.addFirst(root); //开始从前面进前面出
        int count = 0;  //计数。从偶数开始
        while (!dequeue.isEmpty()) {    //队列用来遍历树
            int curSize = dequeue.size();   //获取当前层的元素个数
            List<Integer> list = new ArrayList<>();
            while (curSize > 0) { //遍历当前层所有节点
                if (count % 2 == 0) { //偶数，从前面栈中取元素。并分别将左子节点和右子节点放到后面栈中
                    TreeNode first = dequeue.pollFirst();
                    list.add(first.val);
                    if (first.left != null) dequeue.addLast(first.left);
                    if (first.right != null) dequeue.addLast(first.right);
                } else {    //奇数，从后面栈中取元素。并分别将右子节点和左子节点放到前面栈中
                    TreeNode right = dequeue.pollLast();
                    list.add(right.val);
                    if (right.right != null) dequeue.addFirst(right.right);
                    if (right.left != null) dequeue.addFirst(right.left);
                }
                curSize--;
            }
            count++;    //计数+1
            lists.add(list);    //list添加到总list中
        }
        return lists;
    }
}
