/*
LeetCode Problem: https://leetcode.com/problems/reverse-linked-list-ii/

Question: 92. Reverse Linked List II

Problem Statement: Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Example 2:
Input: head = [5], left = 1, right = 1
Output: [5]

Constraints:
    The number of nodes in the list is n.
    1 <= n <= 500
    -500 <= Node.val <= 500
    1 <= left <= right <= n

Follow up: Could you do it in one pass?
*/

/*
Approach: In-Place Reversal of a Linked List Segment

Goal:
- Reverse the nodes from position left to right
  (1-indexed) while keeping the rest of the list
  unchanged.

Example:

    1 -> 2 -> 3 -> 4 -> 5
    left = 2, right = 4

Result:

    1 -> 4 -> 3 -> 2 -> 5

Core Idea:
- Locate the node immediately before the
  sublist to reverse.
- Reverse exactly (right - left + 1) nodes.
- Reconnect the reversed portion back to the
  unreversed parts.

Important Pointers:
-------------------

targetLeft
    Node before position left.

current
    First node of the segment being reversed.

previous
    Head of the reversed segment being built.

After reversal:

targetLeft
      |
      v
 ... -> previous -> ... -> tail

The original left node becomes the tail of the
reversed segment.

Algorithm Steps:
----------------

1. Create a dummy node.

       dummy -> head

   This handles cases where left = 1.

2. Move to position left:

       targetLeft = node before left
       current    = node at left

3. Reverse exactly:

       right - left + 1

   nodes using the standard iterative reversal.

4. Reconnect:

   Original left node:

       targetLeft.next

   becomes the tail of the reversed segment.

   Connect:

       targetLeft.next.next = current

   where current is the first node after the
   reversed segment.

5. Connect:

       targetLeft.next = previous

   where previous is the new head of the
   reversed segment.

6. Return:

       dummy.next

Example:
---------

Input:

    1 -> 2 -> 3 -> 4 -> 5
         ^           ^
       left=2     right=4

Reverse:

    2 -> 3 -> 4

Becomes:

    4 -> 3 -> 2

Reconnect:

    1 -> 4 -> 3 -> 2 -> 5

Why It Works:
-------------
Before reversal:

    targetLeft -> leftNode -> ...

After reversal:

    previous -> ... -> leftNode

where:

    previous = new head
    leftNode = new tail

The two reconnection statements restore the list:

    targetLeft.next.next = current
    targetLeft.next = previous

Time Complexity:
----------------

Traversal to left:
    O(left)

Reversal:
    O(right - left + 1)

Total:
    O(n)

Space Complexity:
----------------

    O(1)

Result:
-------
Returns the linked list after reversing the nodes
between positions left and right inclusive.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to find the reversed list
  public ListNode reverseBetween(ListNode head, int left, int right) {
    // Initialize the dummy node
    ListNode dummy = new ListNode(0, head);

    // Initialize the target nodes
    ListNode targetLeft = dummy;

    // Initialize the three node for reversing the linked list
    ListNode previous = null;
    ListNode current = dummy.next;
    ListNode next;

    // Iterate over the left
    for (int i = 1; i < left; i++) {
      // Update the pointers
      targetLeft = current;
      current = current.next;
    }

    // Reverse the linked list
    for (int i = 0; i <= right - left; i++) {
      // Update the pointes
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }

    // Update the pointers
    targetLeft.next.next = current;
    targetLeft.next = previous;

    // Return the next of the dummy node
    return dummy.next;
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
public class _92_Reverse_Linked_List_II {
  // Main method to test reverseBetween
  public static void main(String[] args) {
    ListNode head = ListNode.makelist(new int[] { 3, 5 });
    int left = 1;
    int right = 2;

    ListNode result = new Solution().reverseBetween(head, left, right);

    System.out.println("The reversed list is : " + result);
  }
}
