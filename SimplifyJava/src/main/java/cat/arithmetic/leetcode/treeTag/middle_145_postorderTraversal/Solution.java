package cat.arithmetic.leetcode.treeTag.middle_145_postorderTraversal;

import cat.arithmetic.leetcode.treeTag.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 145. 二叉树的后序遍历
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-25
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversalHelper1(list, root);
        return list;
    }

    public void postorderTraversalHelper1(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        postorderTraversalHelper1(list,root.left);
        postorderTraversalHelper1(list,root.right);
        list.add(root.val);
    }


    /**
     * 第二种解决方式，使用迭代的方式，使用两个践实现
     *
     *
     * @param list
     * @param root
     */
    public void postorderTraversalHelper2(List<Integer> list, TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack1 = new Stack<>();
        while(root !=null || !stack.isEmpty()){
            if(root !=null){
                stack1.push(root);
                stack.push(root);
                root = root.right;
            }
            else if(!stack.isEmpty()){
                TreeNode node = stack.pop();
                root = node.left;
            }
        }

        while(!stack1.isEmpty()){
            list.add(stack1.pop().val);
        }
    }
}
