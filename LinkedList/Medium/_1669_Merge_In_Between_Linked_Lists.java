/*
LeetCode Problem: https://leetcode.com/problems/merge-in-between-linked-lists/

Question: 1669. Merge In Between Linked Lists

Problem Statement: You are given two linked lists: list1 and list2 of sizes n and m respectively.

Remove list1's nodes from the ath node to the bth node, and put list2 in their place.

The blue edges and nodes in the following figure indicate the result:

Build the result list and return its head.

Example 1:
Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
Output: [10,1,13,1000000,1000001,1000002,5]
Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.

Example 2:
Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
Explanation: The blue edges and nodes in the above figure indicate the result.

Constraints:
    3 <= list1.length <= 10^4
    1 <= a <= b < list1.length - 1
    1 <= list2.length <= 10^4

*/

/*
Approach: Locate Boundaries + Splice Linked Lists

Goal:
- Remove nodes from index a to index b (inclusive)
  in list1 and insert list2 in their place.

Core Idea:
- Find:
    1. The node immediately before index a.
    2. The node immediately after index b.
- Connect:
      beforeA -> list2
- Traverse to the tail of list2.
- Connect:
      tail(list2) -> afterB

This effectively replaces the segment
[a ... b] with list2.

What This Implementation Does:
------------------------------
- Traverses list1 while decrementing a and b.
- When a becomes 1:
    - Connects list1.next to list2.
    - Stores the next node for continued traversal.
    - Moves to the tail of list2.
- When b becomes 1:
    - Connects the tail of list2 to the node
      after index b.
    - Terminates.

Time Complexity:
- O(n + m)

where:
- n = length of list1
- m = length of list2

Space Complexity:
- O(1)

Result:
- Returns list1 after replacing the nodes from
  index a through index b with list2.

Alternative Cleaner Approach:
-----------------------------

1. Find:
      prevA = node at index a - 1
      afterB = node at index b + 1

2. Connect:
      prevA.next = list2

3. Find the tail of list2.

4. Connect:
      tail.next = afterB

This makes the splice operation easier to reason
about and is the standard solution for this problem.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to add new list in the existing range of list
  public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    // Initialize the dummy variable
    ListNode dummy = new ListNode(0, list1);

    // Initialize the node variable
    ListNode node;

    // Iterate over the list1 untill its not null
    while (list1 != null) {
      // If a is one then update the list1
      if (a == 1) {
        // Swap the pointers
        node = list1.next;
        list1.next = list2;
        list1 = node;

        // Get the end of the list2
        while (list2.next != null) {
          list2 = list2.next;
        }
      }

      // If b is one then update the list2 and break out of the loop
      if (b == 1) {
        list2.next = list1.next;
        break;
      }

      // Update the next pointer
      list1 = list1.next;

      // Decrement a and b
      a--;
      b--;
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
public class _1669_Merge_In_Between_Linked_Lists {
  // Main method to test mergeInBetween
  public static void main(String[] args) {
    ListNode list1 = new ListNode().makelist(new int[] { 0, 1, 2, 3, 4, 5, 6 });
    int a = 2;
    int b = 5;
    ListNode list2 = new ListNode().makelist(new int[] { 1000000, 1000001, 1000002, 1000003, 1000004 });

    ListNode result = new Solution().mergeInBetween(list1, a, b, list2);

    System.out.println("The new linked list is : " + result);
  }
}
