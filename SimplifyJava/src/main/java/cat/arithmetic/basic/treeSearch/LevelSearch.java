package cat.arithmetic.basic.treeSearch;

import cat.arithmetic.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 树的层序遍历
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-04-25
 */
public class LevelSearch {
    public void levelSearch(TreeNode root) {
        // 声明队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode element = queue.poll();
            System.out.println(element);
            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }
        }
    }



    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 怎么知道这一层开始了，或者结束了？
        Map<TreeNode,Integer> map = new HashMap<>();
        List<Integer> nodeList = new ArrayList<>();
        map.put(root,1);
        queue.add(root);
        Integer currentLevel = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val);
            Integer level = map.get(node);
            if(node.left !=null){
                queue.add(node.left);
                map.put(node.left, currentLevel + 1);
            }
            if(node.right !=null){
                queue.add(node.right);
                map.put(node.right, currentLevel + 1);
            }
            if(currentLevel.equals(level)){
                System.out.println(level + " and " + root.val);
                nodeList.add(root.val);
            }else{
                result.add(nodeList);
                nodeList.clear();
                currentLevel = currentLevel + 1;
            }
        }
        result.add(nodeList);
        return result;
    }

}
