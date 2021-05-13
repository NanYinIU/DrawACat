package cat.arithmetic.leetcode.expiredLeetcode;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * <p>
 * prev curr
 * curr.next = prev
 * prev = curr
 */
public class ReverseList {
    /**
     * 使用迭代实现
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            // 重新设置 prev 和 curr 节点
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * 使用递归实现
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递归，由后向前
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
