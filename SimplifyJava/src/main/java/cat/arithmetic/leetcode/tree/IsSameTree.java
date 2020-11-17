package cat.arithmetic.leetcode.tree;

/**
 * 100. 相同的树
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
 *
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-17
 */
public class IsSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }else if( (p == null && q != null) || (p !=null && q == null) ){
            return false;
        }
        return p.val==q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

}
