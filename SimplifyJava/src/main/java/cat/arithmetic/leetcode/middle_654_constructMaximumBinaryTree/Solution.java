package cat.arithmetic.leetcode.middle_654_constructMaximumBinaryTree;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 654. 最大二叉树
 *
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 *
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 *         - 空数组，无子节点。
 *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 *             - 空数组，无子节点。
 *             - 只有一个元素，所以子节点是一个值为 1 的节点。
 *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 *         - 只有一个元素，所以子节点是一个值为 0 的节点。
 *         - 空数组，无子节点。
 * 示例 2：
 *
 *
 * 输入：nums = [3,2,1]
 * 输出：[3,null,2,null,1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * nums 中的所有整数 互不相同
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-20
 */
public class Solution {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return buildTree(0, nums.length, nums);
    }

    /**
     **************************
     * 暴力求解，找出每次找出最大值后
     * 递归左子树、右子树
     **************************
     * @param low
     * @param high
     * @param nums
     * @return
     */
    private TreeNode buildTree(int low, int high, int[] nums) {
        int max = 0;
        int max_num = 0;
        if (low == high) {
            return null;
        }
        for (int i = low; i < high; i++) {
            if (max_num <= nums[i]) {
                max_num = nums[i];
                max = i;
            }
        }
        TreeNode root = new TreeNode(max_num);
        TreeNode left = buildTree(low, max, nums);
        TreeNode right = buildTree(max + 1, high, nums);
        root.left = left;
        root.right = right;
        return root;
    }


}
