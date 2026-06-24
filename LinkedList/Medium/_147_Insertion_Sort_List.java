/*
LeetCode Problem: https://leetcode.com/problems/insertion-sort-list/

Question: 147. Insertion Sort List

Problem Statement: Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.

The steps of the insertion sort algorithm:

    Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
    At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
    It repeats until no input elements remain.

The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Constraints:
    The number of nodes in the list is in the range [1, 5000].
    -5000 <= Node.val <= 5000
*/

/*
Approach: Insertion Sort on Linked List

Goal:
- Sort a singly linked list using the
  Insertion Sort algorithm.

Core Idea:
- Maintain a sorted portion of the list.
- Take one node at a time from the unsorted
  portion and insert it into its correct
  position within the sorted portion.

Why Linked Lists Suit Insertion Sort:
-------------------------------------
- Inserting a node into a linked list is O(1)
  once the insertion position is found.
- No shifting of elements is required as in
  arrays.

Algorithm Steps:
----------------

1. Create a dummy node:

       dummy -> head

   The dummy node simplifies insertion at the
   beginning of the sorted list.

2. Maintain:

       previous

   as the last node of the currently sorted
   portion.

3. Let:

       current = previous.next

   be the node to insert.

4. If:

       current.val >= previous.val

   then the node is already in the correct
   position.

   Move both pointers forward.

5. Otherwise:
   - Start from dummy.
   - Find the first node whose value is
     greater than or equal to current.val.
   - Insert current before that node.

6. Continue until all nodes are processed.

Example:
---------

Input:

    4 -> 2 -> 1 -> 3

Step 1:
    Sorted:   4
    Current:  2

Insert 2:

    2 -> 4 -> 1 -> 3

Step 2:
    Sorted:   2 -> 4
    Current:  1

Insert 1:

    1 -> 2 -> 4 -> 3

Step 3:
    Sorted:   1 -> 2 -> 4
    Current:  3

Insert 3:

    1 -> 2 -> 3 -> 4

Output:

    1 -> 2 -> 3 -> 4

Pointer Operations:
-------------------

Removing current:

    previous.next = current.next

Insertion:

    current.next = temp.next
    temp.next = current

where temp is the node immediately before the
insertion position.

Why It Works:
-------------
- At every iteration, the portion before
  current remains sorted.
- current is inserted into its correct
  position within that sorted portion.
- Repeating this process eventually produces a
  fully sorted list.

Time Complexity:
----------------

Best Case (already sorted):
    O(n)

Worst Case (reverse sorted):
    O(n²)

Average Case:
    O(n²)

Space Complexity:
----------------

    O(1)

Only a few pointer variables are used.

Result:
-------
Returns the head of the linked list sorted in
non-decreasing order using insertion sort.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to sort a linked list using Insertion Sort
  public ListNode insertionSortList(ListNode head) {
    // If the list is empty or contains only one node, it is already sorted
    if (head == null || head.next == null) {
      return head;
    }

    // Dummy node helps simplify insertion at the beginning
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    // 'previous' points to the last node of the sorted part
    ListNode previous = head;

    // 'current' is the node to be inserted into the sorted part
    ListNode current = head.next;

    // Traverse the list
    while (current != null) {
      // If current node is already in correct position, simply move both pointers
      // forward
      if (current.val >= previous.val) {
        previous = current;
        current = current.next;
        continue;
      }

      // Start searching from the beginning of the sorted part
      ListNode temp = dummy;

      // Find the insertion position move until we find a node whose value is greater
      // than or equal to current.val
      while (temp.next != null && temp.next.val < current.val) {
        temp = temp.next;
      }

      // Remove current node from its current position
      previous.next = current.next;

      // Insert current node after temp
      current.next = temp.next;
      temp.next = current;

      // Move current to the next node to process
      current = previous.next;
    }

    // Return the head of the sorted list
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
public class _147_Insertion_Sort_List {
  // Main method to test insertionSortList
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { -1, 5, 3, 4, 0 });

    ListNode result = new Solution().insertionSortList(head);

    System.out.println("The sorted list's head is : " + result);
  }
}
