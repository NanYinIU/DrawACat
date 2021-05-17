package cat.arithmetic.leetcode.treeTag.middle_113_pathSum;

import cat.arithmetic.leetcode.treeTag.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 113. 路径总和 II
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * TODO: 很差的算法，效率比较低，需要优化
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-17
 */
public class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        pathFinder(root, targetSum, new ArrayList<>());
        return result;
    }

    public void pathFinder(TreeNode root, int targetSum, List<Integer> path) {
        if (root == null) {
            return;
        }
        int newTarget = targetSum - root.val;
        path.add(root.val);
        if (root.left == null && root.right == null && newTarget == 0) {
            result.add(path);
        }
        List<Integer> leftPath = clonePath(path);
        List<Integer> rightPath =  clonePath(path);
        pathFinder(root.left, newTarget, leftPath);
        pathFinder(root.right, newTarget, rightPath);
    }

    public List<Integer> clonePath(List<Integer> path){
        List<Integer> result = new ArrayList<>();
        for (Integer i : path) {
            result.add(i);
        }
        return result;
    }
}
