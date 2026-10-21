#include <stack>
using namespace std;

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        stack<int> firstDigits;
        stack<int> secondDigits;

        while (l1 != nullptr) {
            firstDigits.push(l1->val);
            l1 = l1->next;
        }

        while (l2 != nullptr) {
            secondDigits.push(l2->val);
            l2 = l2->next;
        }

        int carry = 0;
        ListNode* result = nullptr;

        while (!firstDigits.empty() || !secondDigits.empty() || carry != 0) {
            int sum = carry;

            if (!firstDigits.empty()) {
                sum += firstDigits.top();
                firstDigits.pop();
            }

            if (!secondDigits.empty()) {
                sum += secondDigits.top();
                secondDigits.pop();
            }

            ListNode* node = new ListNode(sum % 10);
            node->next = result;
            result = node;
            carry = sum / 10;
        }

        return result;
    }
};
