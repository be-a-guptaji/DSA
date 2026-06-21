/*
LeetCode Problem: https://leetcode.com/problems/swapping-nodes-in-a-linked-list/

Question: 1721. Swapping Nodes in a Linked List

Problem Statement: You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]

Example 2:
Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]

Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 10^5
    0 <= Node.val <= 100
*/

/*
Approach: Two Pointers + Kth Node from End

Goal:
- Swap the values of:
    1. The kth node from the beginning.
    2. The kth node from the end.

- The linked list structure remains unchanged.
- Only node values are swapped.

Core Idea:
- First locate the kth node from the start.
- Then use a two-pointer technique to locate
  the kth node from the end.

Key Observation:
----------------
If a pointer starts at the kth node from the
beginning and moves to the end, another pointer
starting at the head will finish at the kth node
from the end.

Algorithm Steps:
1. Move first pointer k steps from the dummy node.

      first = kth node from start

2. Store:

      temp = first

3. Move:
      temp
      last

   together until temp reaches null.

4. At that point:

      last = kth node from end

5. Swap:

      first.val
      last.val

6. Return the original head.

Example:
---------

List:
    1 -> 2 -> 3 -> 4 -> 5

k = 2

kth from start:
    2

kth from end:
    4

After swap:
    1 -> 4 -> 3 -> 2 -> 5

Why It Works:
- Let n be the list length.
- The kth node from the beginning is at position:

      k

- The kth node from the end is at position:

      n - k + 1

- When temp starts at the kth node and moves to
  the end, last advances exactly:

      n - k + 1

  positions from the dummy node, landing on the
  kth node from the end.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the linked list after swapping the
  values of the kth node from the beginning and
  the kth node from the end.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to find the head of the linked list after swapping the values of the
  // kth node from the beginning and the kth node from the end (the list is
  // 1-indexed)
  public ListNode swapNodes(ListNode head, int k) {
    // Initialize the dummy node
    ListNode dummy = new ListNode(0, head);

    // Initialize the first and last node
    ListNode first = dummy;
    ListNode last = dummy;
    ListNode temp;

    // Iterate untill k become zero
    for (int i = 0; i < k; i++) {
      first = first.next;
    }

    // Set the temp equal to first
    temp = first;

    // Iterate over temp untill it become zero
    while (temp != null) {
      // Update the pointers
      temp = temp.next;
      last = last.next;
    }

    // Swap the nodes
    int val = first.val;
    first.val = last.val;
    last.val = val;

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
public class _1721_Swapping_Nodes_in_a_Linked_List {
  // Main method to test swapNodes
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { 1, 2, 3, 4, 5 });
    int k = 2;

    ListNode result = new Solution().swapNodes(head, k);

    System.out.println(
        "The head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed) is : "
            + result);
  }
}
