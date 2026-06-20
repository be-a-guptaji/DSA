/*
LeetCode Problem: https://leetcode.com/problems/remove-nodes-from-linked-list/

Question: 2487. Remove Nodes From Linked List

Problem Statement: You are given the head of a linked list.

Remove every node which has a node with a greater value anywhere to the right side of it.

Return the head of the modified linked list.

Example 1:
Input: head = [5,2,13,3,8]
Output: [13,8]
Explanation: The nodes that should be removed are 5, 2 and 3.
- Node 13 is to the right of node 5.
- Node 13 is to the right of node 2.
- Node 8 is to the right of node 3.

Example 2:
Input: head = [1,1,1,1]
Output: [1,1,1,1]
Explanation: Every node has value 1, so no nodes are removed.

Constraints:
    The number of the nodes in the given list is in the range [1, 10^5].
    1 <= Node.val <= 10^5
*/

/*
Approach: Reverse Linked List + Running Maximum

Goal:
- Remove every node that has a node with a
  greater value somewhere to its right.

Example:

    5 -> 2 -> 13 -> 3 -> 8

Since:
    5 has 13 on its right
    2 has 13 on its right
    3 has 8 on its right

Result:

    13 -> 8

Core Idea:
- The condition depends on nodes to the right.
- Singly linked lists are easy to traverse from
  left to right, but not from right to left.

- Reverse the list so that:
      right-side nodes become left-side nodes.

- Then maintain the maximum value seen so far.
- Any node smaller than this maximum must be
  removed.

Algorithm Steps:
1. Reverse the linked list.

2. Initialize:
      max = head.val

3. Traverse the reversed list:
   - If:

         current.next.val < max

     remove current.next.

   - Otherwise:
       - update max
       - move forward

4. Reverse the list again.

5. Return the new head.

Example:
---------

Original:

    5 -> 2 -> 13 -> 3 -> 8

Reverse:

    8 -> 3 -> 13 -> 2 -> 5

Traversal:

    max = 8

    3 < 8
    remove 3

    13 >= 8
    max = 13

    2 < 13
    remove 2

    5 < 13
    remove 5

Remaining:

    8 -> 13

Reverse Again:

    13 -> 8

Why It Works:
- After reversing, every node only needs to be
  compared against the maximum value encountered
  so far.
- A node survives iff it is greater than or equal
  to every original node on its right.

Time Complexity:
- O(n)
  - First reverse: O(n)
  - Traversal: O(n)
  - Second reverse: O(n)

Space Complexity:
- O(1)

Result:
- Returns the linked list after removing every
  node that has a strictly greater value somewhere
  to its right.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to find the modified linked list
  public ListNode removeNodes(ListNode head) {
    // Reverse the linked list
    head = this.reverse(head);

    // Initialize the current node
    ListNode current = head;

    // Initialize the max variable
    int max = current.val;

    // Iterate over the temp untill it become null
    while (current != null && current.next != null) {
      if (current.next.val < max) {
        current.next = current.next.next;
      } else {
        current = current.next;
        max = current.val;
      }
    }

    // Return the head of the modified linked list
    return this.reverse(head);
  }

  // Helper method to reverse a linked list
  private ListNode reverse(ListNode head) {
    // Initialize three pointer
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
  public ListNode makelist(int[] list) {
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
public class _2487_Remove_Nodes_From_Linked_List {
  // Main method to test removeNodes
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { 5, 2, 13, 3, 8 });

    ListNode result = new Solution().removeNodes(head);

    System.out.println("The modified linked list is : " + result);
  }
}
