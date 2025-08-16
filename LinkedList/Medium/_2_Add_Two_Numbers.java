/*
LeetCode Problem: https://leetcode.com/problems/add-two-numbers/

Question: 2. Add Two Numbers

Problem Statement: You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 */

/*
Approach: We solve this problem by simulating digit-by-digit addition of two numbers 
stored in linked lists (in reverse order).

We maintain:
1. resultList – a dummy node to simplify list construction.
2. current – pointer to build the result list step by step.
3. carry – stores the carry-over value during addition.

At every step:
- Extract the current digit from l1 and l2 (use 0 if the list is exhausted).
- Compute sum = val1 + val2 + carry.
- Update carry = sum / 10.
- Create a new node with value = sum % 10 and attach it to the result list.
- Move l1 and l2 forward if nodes exist.

In the end, if carry is non-zero, append it as the last node.
Finally, return resultList.next (skipping the dummy node).

Time Complexity: O(max(m, n)), where m and n are the lengths of the two lists.
Space Complexity: O(1), since we only use constant extra space apart from the output list.
*/

package LinkedList.Medium;

public class _2_Add_Two_Numbers {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    // Method to add two numbers represented as linked lists
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Check the edge case of empty input lists
        if (l1 == null || l2 == null) {
            return null;
        }

        // ResultList node to simplify building result
        ListNode resultList = new ListNode(0);
        ListNode current = resultList;

        // Initialize variable for carry
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            // Extract values if available, otherwise use 0
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            // Compute sum and carry
            int sum = val1 + val2 + carry;
            carry = sum / 10;

            // Add node with digit value
            current.next = new ListNode(sum % 10);
            current = current.next;

            // Move to next nodes if available
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // Return the result list
        return resultList.next;
    }

    // Function to convert the list into the ListNode
    public static ListNode makelist(int[] list) {
        if (list.length == 0) {
            return null; // Handle empty array
        }

        ListNode head = new ListNode(list[0]); // First element as head
        ListNode current = head;

        for (int i = 1; i < list.length; i++) {
            current.next = new ListNode(list[i]);
            current = current.next;
        }

        return head;
    }

    // Mock class for makeing the ListNode Class
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Main method to test addTwoNumbers
    public static void main(String[] args) {
        int[] l1 = { 9, 9, 9, 9, 9, 9, 9 }, l2 = { 9, 9, 9, 9 };

        ListNode result = addTwoNumbers(makelist(l1), makelist(l2));

        System.out.print("The added linked list of two given linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
