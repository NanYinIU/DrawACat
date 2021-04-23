package cat.arithmetic.basic.treeSearch;

import cat.arithmetic.leetcode.tree.TreeNode;

/**
 *
 * 递归的方式遍历树
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-23
 */
public class Recursive {


    /**
     * 前序遍历
     *
     * 前序就是针对每一个子树，都先输出根节点、左节点、右节点
     *
     */
    private void preOrder(TreeNode root) {
        if(root == null){
            return ;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * 中序就是针对每一个子树，都先输出左节点、根节点、右节点
     */
    private void inOrder(TreeNode root) {
        if(root == null){
            return ;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     * 后序遍历就是针对每一个子树，都先输出左节点、右节点、根节点
     */
    private void postOrder(TreeNode root) {
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }


}
