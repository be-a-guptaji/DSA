/*
LeetCode Problem: https://leetcode.com/problems/add-two-numbers-ii/

Question: 445. Add Two Numbers II

Problem Statement: You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]

Example 2:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]

Example 3:
Input: l1 = [0], l2 = [0]
Output: [0]

Constraints:
    The number of nodes in each linked list is in the range [1, 100].
    0 <= Node.val <= 9
    It is guaranteed that the list represents a number that does not have leading zeros.

Follow up: Could you solve it without reversing the input lists?
*/

/*
Approach: Reverse Both Lists + Elementary Addition

Goal:
- Add two non-negative integers represented by
  linked lists.
- Digits are stored in forward order.

Example:

    7 -> 2 -> 4 -> 3
  + 5 -> 6 -> 4
  ----------------
    7 -> 8 -> 0 -> 7

Core Idea:
- Addition is naturally performed from the least
  significant digit.
- Since the digits are stored in forward order,
  reverse both lists first.
- Perform standard digit-by-digit addition with
  carry.
- Reverse the resulting list to restore forward
  order.

Algorithm Steps:
----------------

1. Reverse l1.

      7 -> 2 -> 4 -> 3
            becomes
      3 -> 4 -> 2 -> 7

2. Reverse l2.

      5 -> 6 -> 4
          becomes
      4 -> 6 -> 5

3. Add digits while:
      l1 exists
      OR l2 exists
      OR carry exists

      sum = val1 + val2 + carry

      digit = sum % 10
      carry = sum / 10

4. Append digit to the result list.

5. Reverse the result list.

6. Return the new head.

Example:
---------

Reversed Lists:

      3 -> 4 -> 2 -> 7
      4 -> 6 -> 5

Addition:

      3 + 4 = 7
      4 + 6 = 10
      2 + 5 + 1 = 8
      7 + 0 = 7

Result (reversed):

      7 -> 0 -> 8 -> 7

Reverse again:

      7 -> 8 -> 0 -> 7

Why It Works:
-------------
After reversing:

      index 0

represents the least significant digit of both
numbers.

This allows the same addition process used for
arrays or strings:

      digit = sum % 10
      carry = sum / 10

The final reversal restores the required forward
digit order.

Time Complexity:
----------------
Let:

      n = length of l1
      m = length of l2

Reverse l1:
      O(n)

Reverse l2:
      O(m)

Addition:
      O(max(n,m))

Reverse result:
      O(max(n,m))

Total:

      O(n + m)

Space Complexity:
-----------------
Ignoring the output list:

      O(1)

Result:
-------
Returns a linked list representing the sum of the
two numbers in forward order.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to find the sum as a linked list
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    // Initialize the result node
    ListNode result = new ListNode(0);

    // Initialize the current node
    ListNode current = result;

    // Reverse the linked lists
    l1 = this.reverse(l1);
    l2 = this.reverse(l2);

    // Initialize the carry variable
    int carry = 0;

    // Iterate over the l1 and l2 list
    while (carry != 0 || l1 != null || l2 != null) {
      // Extract values if available, otherwise use 0
      int val1 = (l1 != null) ? l1.val : 0;
      int val2 = (l2 != null) ? l2.val : 0;

      // Initialize the sum variable
      int sum = val1 + val2 + carry;

      // Update the carry variable
      carry = sum / 10;

      // Update the current list
      current.next = new ListNode(sum % 10);

      // Move the current pointer forward
      current = current.next;

      // Move to next nodes if available
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }

    // Return the result
    return this.reverse(result.next);
  }

  // Helper method to reverse the string
  private ListNode reverse(ListNode head) {
    // Initialize the three pointer
    ListNode previous = null;
    ListNode current = head;
    ListNode next;

    // Iterate over the current untill it become null
    while (current != null) {
      // Update the pointers
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }

    // Return the previous pointer
    return previous;
  }
}

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

// Mock class for makeing the ListNode Class
class ListNode {
  public int val;
  public ListNode next;

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  // Helper method to make the linked list from the array
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
}

// Main Class
public class _445_Add_Two_Numbers_II {
  // Main method to test addTwoNumbers
  public static void main(String[] args) {
    ListNode l1 = ListNode.makelist(new int[] { 7, 2, 4, 3 });
    ListNode l2 = ListNode.makelist(new int[] { 5, 6, 4 });

    ListNode result = new Solution().addTwoNumbers(l1, l2);

    System.out.println("The sum as a linked list is : " + result);
  }
}
