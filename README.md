# LeetCode 445: Add Two Numbers II

**Course Code:** CSC301  
**Group:** Group 22  
**Topic:** Linked Lists

## 1. Introduction

A linked list is a data structure made up of nodes, where each node stores a value and a reference to the next node. Unlike arrays, linked-list elements are not necessarily stored next to one another in memory. Linked lists are useful when data is represented as a sequence of connected elements.

LeetCode problem 445, **Add Two Numbers II**, applies this structure to arithmetic. The task is to add two non-negative integers represented by linked lists and return their sum in the same linked-list format. The important detail is that each number is stored in **forward order**: the first node contains the most significant digit, and the last node contains the least significant digit.

## 2. Problem Statement

We are given two non-empty singly linked lists, `l1` and `l2`. Each node contains one digit, and the sequence of nodes represents a non-negative integer. We must add the two represented numbers and return a new linked list containing the result.

For example, the lists `7 → 2 → 4 → 3` and `5 → 6 → 4` represent the numbers **7243** and **564**. Their sum is **7807**, so the returned linked list should contain `7 → 8 → 0 → 7`.

The lists may have different lengths. We can assume that neither number has leading zeroes unless the number itself is zero. The solution should also leave the original input lists unchanged.

## 3. Diagram

The diagram below illustrates the two input lists and the result. The nodes are connected in the order in which the digits are stored.

![Linked-list addition example: 7243 plus 564 gives 7807](/assets/diagram.png)

**Example:**

- **First number:** `7 → 2 → 4 → 3` = 7243
- **Second number:** `5 → 6 → 4` = 564
- **Sum:** 7243 + 564 = 7807
- **Result list:** `7 → 8 → 0 → 7`

The diagram makes the forward-order representation clear: the leftmost node is the first digit of each number, not the last digit.

## 4. Understanding the Challenge

In ordinary addition, we begin with the rightmost digits and move left, carrying any value of ten or more into the next column. A linked list in this problem stores digits in the opposite direction from the order needed for addition: its head points to the most significant digit, while addition begins with the least significant digit.

A singly linked list only allows us to move forward from a node to the next node. Starting at the head therefore does not give direct access to the last digit. Reversing the input lists could make the digits easier to process, but it would modify the original lists. We can avoid that by storing the digits temporarily in stacks.

## 5. Approach: Using Two Stacks

The group solution uses two stacks, one for each input list. A stack follows the **last in, first out (LIFO)** rule. When we traverse a linked list from its head and push each digit onto a stack, the least significant digit ends up at the top. We can then pop digits in the order required for addition without reversing either list.

The algorithm works as follows:

1. Traverse the first linked list and push each digit onto the first stack.
2. Traverse the second linked list and push each digit onto the second stack.
3. Initialise `carry` to zero and create an empty result list.
4. While either stack still contains digits, or a carry remains, start the current sum with the carry.
5. If the first stack is not empty, pop its top digit and add it to the sum. Do the same for the second stack.
6. The current result digit is `sum % 10`, while the next carry is `sum / 10`.
7. Create a node containing the result digit and insert it at the beginning of the result list.
8. Continue until both stacks are empty and no carry remains, then return the head of the result list.

Inserting each new node at the front is important. Since we calculate digits from least significant to most significant, front insertion restores the correct forward order in the final list.

## 6. Worked Example

Consider the addition **7243 + 564**.

The stacks allow us to start with the rightmost digits even though both lists are stored in forward order:

| Step | Digits added | Carry in | Total | Result digit | Carry out |
|---|---:|---:|---:|---:|---:|
| 1 | 3 + 4 | 0 | 7 | 7 | 0 |
| 2 | 4 + 6 | 0 | 10 | 0 | 1 |
| 3 | 2 + 5 | 1 | 8 | 8 | 0 |
| 4 | 7 + 0 | 0 | 7 | 7 | 0 |

At the first step, `3 + 4` gives 7. At the second step, `4 + 6` gives 10, so we store 0 as the current digit and carry 1 to the next step. The next addition is `2 + 5 + 1`, which gives 8. Finally, the remaining digit 7 is added to 0, producing 7.

The digits are processed in the order **7, 0, 8, 7**, from least significant to most significant. Because every new node is inserted at the front, the final linked list is **`7 → 8 → 0 → 7`**, representing **7807**.

## 7. Handling Carry and Different Lengths

The loop must continue while either stack contains digits **or** the carry is non-zero. This ensures that a final carry becomes a new node when necessary. For instance, adding `9 → 9` (99) and `1` gives 100, so the result must be `1 → 0 → 0`.

The stacks can also have different sizes. Once the shorter stack becomes empty, its missing digit is treated as zero, while the remaining digits in the longer stack continue to be processed. The same logic handles the case where both input lists represent zero.

## 8. Correctness

The algorithm processes each number from its least significant digit to its most significant digit because the stacks reverse the access order without changing the linked lists. At each step, it adds the available digits and the carry from the previous step. Taking the remainder after division by ten gives the correct digit for the current place, and integer division by ten gives the carry for the next place.

Every computed digit is inserted at the front of the result list. This reverses the order in which the digits are calculated and restores the required forward format. Since the loop also processes any remaining carry, all digits of the sum are included. Therefore, the algorithm returns the correct sum as a linked list while preserving both inputs.

## 9. Complexity Analysis

Let **m** be the number of nodes in the first linked list and **n** the number of nodes in the second.

- **Time complexity: `O(m + n)`** — each input list is traversed once to fill the stacks, and each digit is popped and processed once.
- **Auxiliary space complexity: `O(m + n)`** — the two stacks may store all input digits. The result list requires up to `O(max(m, n) + 1)` nodes, depending on the carry.

The approach is efficient for the constraints of the problem, where each input list can contain up to 100 nodes.

## 10. Conclusion

Add Two Numbers II demonstrates how linked-list structure affects the way an algorithm accesses data. Because the digits are stored in forward order, direct addition from the least significant digit is inconvenient in a singly linked list. Two stacks solve this access problem, allowing the algorithm to process digits from right to left, manage carry correctly, and construct the answer in forward order.

The method takes linear time, handles unequal list lengths and final carries, and does not reverse or modify either input list.

---

## Group Information

**Course Code:** CSC301  
**Group Number:** Group 22

### Group Members

| No. | Name | Matric Number |
|---:|---|---|
| 1 | Ugwuoke Henry | VUG/SEN/24/11307 |
| 2 | Kamaldeen Mahmud | VUG/SEN/24/11395 |
| 3 | Akong Samuel | VUG/SEN/24/13176 |
| 4 | Agbo Sunny Ameh | VUG/SEN/25/14687 |
| 5 | Okoro Victor Chima | VUG/SEN/24/13066 |
| 6 | Esmond Oise-Amu | VUG/SEN/24/11201 |
| 7 | Samuel Ehizogie | VUG/SEN/24/11832 |
