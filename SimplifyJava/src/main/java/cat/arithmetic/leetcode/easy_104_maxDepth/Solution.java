package cat.arithmetic.leetcode.easy_104_maxDepth;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-29
 */
public class Solution {

    /**
     * 方法1 宽度有限遍历树
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return 0;
        }
        queue.add(root);
        int deep = 0;
        while(!queue.isEmpty()){
            int currentSize = queue.size();
            while (currentSize > 0){
                TreeNode node = queue.poll();
                if(node.left !=null){
                    queue.add(node.left);
                }
                if(node.right !=null){
                    queue.add(node.right);
                }
                currentSize --;
            }
            deep ++;
        }
        return deep + 1;
    }

    /**
     * 方法2：直接递归解决问题，递归一次+1，找到最大的递归次数
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }


}
