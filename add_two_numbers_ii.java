import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> firstDigits = new ArrayDeque<>();
        Deque<Integer> secondDigits = new ArrayDeque<>();

        while (l1 != null) {
            firstDigits.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            secondDigits.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode result = null;

        while (!firstDigits.isEmpty() || !secondDigits.isEmpty() || carry != 0) {
            int sum = carry;

            if (!firstDigits.isEmpty()) {
                sum += firstDigits.pop();
            }

            if (!secondDigits.isEmpty()) {
                sum += secondDigits.pop();
            }

            ListNode node = new ListNode(sum % 10);
            node.next = result;
            result = node;
            carry = sum / 10;
        }

        return result;
    }
}
