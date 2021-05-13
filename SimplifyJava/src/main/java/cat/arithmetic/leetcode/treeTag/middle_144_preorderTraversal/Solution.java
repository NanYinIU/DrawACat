package cat.arithmetic.leetcode.treeTag.middle_144_preorderTraversal;

import cat.arithmetic.leetcode.treeTag.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
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
 * 输出：[1,2]
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
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-24
 */
public class Solution {

    // 1. 使用递归的方式
    // 2. 使用迭代的方式
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        searchTreeHelper2(list,root);
        return list;
    }

    /**
     * 使用递归方式进行前序遍历
     * @param list
     * @param root
     */
    public void searchTreeHelper(List<Integer> list ,TreeNode root){
        if(root == null){
            return;
        }
        list.add(root.val);
        searchTreeHelper(list,root.left);
        searchTreeHelper(list,root.right);
    }

    /**
     * 使用迭代的方式进行前序遍历
     * @param list
     * @param root
     */
    public void searchTreeHelper2(List<Integer> list,TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        while(root !=null || !stack.isEmpty()){
            if(root!=null){
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }else if(!stack.isEmpty()){
                TreeNode node = stack.pop();
                root = node.right;
            }
        }
    }



}
