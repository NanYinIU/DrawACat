package cat.arithmetic.leetcode;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode first = new ListNode(-1);
        first.next = head;
        ListNode temp = first;
        while(temp.next !=null && temp.next.next!=null){
            // 设置交换节点 l1,l2
            ListNode l1 = temp.next;
            ListNode l2 = temp.next.next;

            // 交换节点
            temp.next = l2;
            l1.next = l2.next;
            l2.next = l1;

            // 向后平移
            temp = l1;

        }
        return first.next;
    }
}
// temp l1 l2 next