/*
LeetCode Problem: https://leetcode.com/problems/split-linked-list-in-parts/

Question: 725. Split Linked List in Parts

Problem Statement: Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.

The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.

The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.

Return an array of the k parts.

Example 1:
Input: head = [1,2,3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but its string representation as a ListNode is [].

Example 2:
Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
Output: [[1,2,3,4],[5,6,7],[8,9,10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.

Constraints:
    The number of nodes in the list is in the range [0, 1000].
    0 <= Node.val <= 1000
    1 <= k <= 50
*/

/*
Approach: Compute Part Sizes + Split the Linked List

Goal:
- Split the linked list into k consecutive parts.
- Each part should differ in size by at most one.
- If the nodes cannot be divided equally, the
  earlier parts receive one extra node.

Core Idea:
- Traverse the linked list once to determine its
  total length.
- Compute:
      baseSize = length / k
      extraNodes = length % k
- Every part contains baseSize nodes.
- The first extraNodes parts contain one
  additional node.
- Traverse the list again, detach each part,
  and store its head in the result array.

Algorithm Steps:
1. Traverse the linked list to calculate its
   total length.
2. Compute:
      baseSize = length / k
      extraNodes = length % k
3. Initialize the result array of size k.
4. Traverse the linked list.
5. For each part:
   - Store the current head.
   - Traverse baseSize nodes.
   - If extraNodes > 0, include one additional
     node and decrement extraNodes.
   - Disconnect the current part by setting the
     last node's next pointer to null.
6. Continue until all nodes are assigned.
7. Return the result array.

Why It Works:
- The quotient determines the minimum number of
  nodes every part must contain.
- The remainder represents the number of extra
  nodes that are distributed one by one to the
  earliest parts.
- Each node is visited exactly once during the
  splitting phase and belongs to exactly one part.

Time Complexity:
- O(n)

Space Complexity:
- O(k)

Result:
- Returns an array containing k consecutive
  linked list parts satisfying the required
  size constraints.
*/

package LinkedList.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find an array of the k parts
  public ListNode[] splitListToParts(ListNode head, int k) {
    // Initialize the dummy node
    ListNode dummy = head;

    // Initialize the array of ListNode
    ListNode[] result = new ListNode[k];

    // Initialize the index variable
    int index = 0;

    // Initialize the linkedListLength of the linked list
    int linkedListLength = 0;

    // Get the linkedListLength of the linked list
    while (dummy != null) {
      // Increment the linkedListLength variable
      linkedListLength++;

      // Update the pointer
      dummy = dummy.next;
    }

    // Initialize the splitLength variable
    int splitLength = linkedListLength / k;

    // Initialize the length variable
    int length = 0;

    // Initialize the reminder variable
    int reminder = linkedListLength % k;

    // Iterate over the linked list untill head is not null
    while (head != null) {
      // Update the result if index in bound and the position is not null
      if (index < result.length && result[index] == null) {
        // Update the result list
        result[index] = head;
      }

      // Increment the length variable
      length++;

      // Update the nodes
      dummy = head;
      head = head.next;

      // If reminder is not zero add an extra node to the list
      if (reminder > 0 && length == splitLength) {
        // Update the nodes
        dummy = head;
        head = head.next;

        // Decrement the reminder variable
        reminder--;
      }

      // Increment the index variable if length is correct
      if (length >= splitLength) {
        // If dummy nodes is not null update it
        if (dummy != null) {
          // Update the pointers
          dummy.next = null;
        }

        // Increment the index
        index++;

        // Reset the length
        length = 0;
      }
    }

    // Return the result
    return result;
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
public class _725_Split_Linked_List_in_Parts {
  // Main method to test splitListToParts
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { 1, 2, 3 });
    int k = 5;

    ListNode[] result = new Solution().splitListToParts(head, k);

    System.out.println("An array of the k parts is : " + Arrays.toString(result));
  }
}
