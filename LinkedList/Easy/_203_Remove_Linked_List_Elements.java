/*
LeetCode Problem: https://leetcode.com/problems/remove-linked-list-elements/

Question: 203. Remove Linked List Elements

Problem Statement: Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

Example 1:
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]

Example 2:
Input: head = [], val = 1
Output: []

Example 3:
Input: head = [7,7,7,7], val = 7
Output: []

Constraints:
    The number of nodes in the list is in the range [0, 10^4].
    1 <= Node.val <= 50
    0 <= val <= 50
*/

/*
Approach: Dummy Node + Iterative Traversal

Goal:
- Remove all nodes from the linked list whose
  value equals val.

Core Idea:
- Removing nodes becomes tricky when the node to
  be removed is the head.
- Introduce a dummy node before the head:
  
      dummy -> head

- This ensures every node, including the original
  head, has a previous node.
- Traverse the list using a pointer that always
  stands at the node before the candidate node.

Algorithm Steps:
1. Create:

      dummy -> head

2. Initialize:

      current = dummy

3. While current.next exists:
   - If:

         current.next.val == val

     remove the node:

         current.next = current.next.next

   - Otherwise move forward:

         current = current.next

4. Return:

      dummy.next

Why It Works:
- current always points to the node immediately
  before the node being examined.
- When a node must be removed, the link is simply
  redirected to skip that node.
- The dummy node handles cases where the head
  itself must be removed.

Example:
---------

Input:
    1 -> 2 -> 6 -> 3 -> 6
    val = 6

Process:
    1 -> 2 -> 3

Output:
    1 -> 2 -> 3

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the linked list after removing all
  nodes whose value equals val.
*/

package LinkedList.Easy;

// Solution Class
class Solution {
  // Method to remove all the nodes of the linked list that has Node.val == val
  public ListNode removeElements(ListNode head, int val) {
    // Initialize the dummy node
    ListNode dummy = new ListNode(0, head);

    // Initialize the current variable
    ListNode current = dummy;

    // Iterate over the linked list
    while (current.next != null) {
      if (current.next.val != val) {
        current = current.next;
      } else {
        current.next = current.next.next;
      }
    }

    // Return the dummy.next
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
public class _203_Remove_Linked_List_Elements {
  // Main method to test removeElements
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { 1, 2, 6, 3, 4, 5, 6 });
    int val = 6;

    ListNode result = new Solution().removeElements(head, val);

    System.out.println(
        "The new linked list after removing all the nodes of the linked list that has Node.val == val is : " + result);
  }
}
