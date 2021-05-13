package cat.arithmetic.leetcode.expiredLeetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author nanyin
 * @class Merge.java
 * @description 56. 合并区间
 * @create 20:49 2020-04-16
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length < 2)   {
            return intervals;
        }
        Arrays.sort(intervals,(a,b) -> a[0] - b[0] );

        List<int[]> ans = new LinkedList<>();
        int[] pre = intervals[0];
        for (int i = 0; i < intervals.length; i++) {
            if(intervals[i][0] <= pre[1]) {
                pre[1] = Math.max(pre[1], intervals[i][1]);
            } else {
                ans.add(pre);
                pre = intervals[i];
            }
        }
        ans.add(pre);
        return ans.toArray(new int[ans.size()][2]);
    }
}
