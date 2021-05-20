package cat.arithmetic.leetcode.treeTag.middle_199_rightSideView;

import cat.arithmetic.leetcode.treeTag.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-20
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return Collections.EMPTY_LIST;
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0){
            int size = queue.size();
            boolean isAdd = false;
            while(size > 0){
                TreeNode node = queue.poll();
                if(node == null){
                    break;
                }
                // 从右向左，只输出第一个弹出的节点就行了
                if(!isAdd){
                    result.add(node.val);
                    isAdd = true;
                }
                if(node.right !=null){
                    queue.add(node.right);
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                size --;
            }
        }
        return result;
    }

}
