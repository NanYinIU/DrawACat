package cat.arithmetic.leetcode;

/**
 * 1->2->4, 1->3->4
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode pre = new ListNode(-1);
//        ListNode result = pre;
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                result.next = l1;
//                l1 = l1.next;
//            } else {
//                result.next = l2;
//                l2 = l2.next;
//            }
//            result = result.next;
//        }
//        result.next = l1 == null ? l2 : l1;
//        return pre.next;
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }

    public static void main(String[] args) {
        MergeTwoLists m = new MergeTwoLists();
        System.out.println(m.mergeTwoLists(m.initFirst(),m.initSecond()));;
    }

    public ListNode initFirst(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(4);
        a.next = b;
        b.next = c;
        return a;
    }

    public ListNode initSecond(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(4);
        a.next = b;
        b.next = c;
        return a;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
