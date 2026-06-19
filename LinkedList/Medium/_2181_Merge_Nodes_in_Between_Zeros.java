/*
LeetCode Problem: https://leetcode.com/problems/merge-nodes-in-between-zeros/

Question: 2181. Merge Nodes in Between Zeros

Problem Statement: You are given the head of a linked list, which contains a series of integers separated by 0's. The beginning and end of the linked list will have Node.val == 0.

For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes. The modified list should not contain any 0's.

Return the head of the modified linked list.

Example 1:
Input: head = [0,3,1,0,4,5,2,0]
Output: [4,11]
Explanation: 
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 3 + 1 = 4.
- The sum of the nodes marked in red: 4 + 5 + 2 = 11.

Example 2:
Input: head = [0,1,0,3,0,2,2,0]
Output: [1,3,4]
Explanation: 
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 1 = 1.
- The sum of the nodes marked in red: 3 = 3.
- The sum of the nodes marked in yellow: 2 + 2 = 4.

Constraints:
    The number of nodes in the list is in the range [3, 2 * 10^5].
    0 <= Node.val <= 1000
    There are no two consecutive nodes with Node.val == 0.
    The beginning and end of the linked list have Node.val == 0.
*/

/*
Approach: Running Segment Sum Between Zero Nodes

Goal:
- Given a linked list of the form:

      0 -> x -> x -> ... -> 0 -> y -> ... -> 0

  replace each segment between two consecutive
  zeroes with a single node containing the sum
  of that segment.

Example:

      0 -> 3 -> 1 -> 0 -> 4 -> 5 -> 2 -> 0

becomes:

      4 -> 11

Core Idea:
- Traverse the list once.
- Maintain a pointer in the output list.
- For every non-zero node:
      add its value to the current segment sum.
- When a zero is encountered:
      start a new output node for the next segment.

How This Code Works:
--------------------
1. Create a dummy node.
2. Traverse the original list.
3. When:
      head.val == 0
   create a new node in the result list.
4. When:
      head.val != 0
   add its value to the current result node.
5. Stop before the final zero
   using:

      while (head.next != null)

6. Return:

      dummy.next

Why It Works:
- Every segment between two zeroes contributes
  its total sum to exactly one node.
- The trailing zero is never processed because
  the loop stops when:

      head.next == null

- Therefore no extra node is produced for the
  final delimiter zero.

Example:
---------

Input:
    0 -> 3 -> 1 -> 0 -> 4 -> 5 -> 2 -> 0

Processing:
    Segment 1 sum = 4
    Segment 2 sum = 11

Output:
    4 -> 11

Time Complexity:
- O(n)

Space Complexity:
- O(m)

where:
- n = length of input list
- m = number of zero-delimited segments

Result:
- Returns a new linked list where each node
  contains the sum of values between consecutive
  zero nodes.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to find the head of the modified linked list
  public ListNode mergeNodes(ListNode head) {
    // Initialize the dummy variable
    ListNode dummy = new ListNode(0, head);

    // Initialize the current variable
    ListNode current = dummy;

    // Iterate over the head linked list untill it become null
    while (head.next != null) {
      // If head val is zero then get add new node to the current node
      if (head.val == 0) {
        current.next = new ListNode(0, null);
        current = current.next;
      } else {
        current.val += head.val;
      }

      // Update the head node
      head = head.next;
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
public class _2181_Merge_Nodes_in_Between_Zeros {
  // Main method to test mergeNodes
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { 0, 1, 0, 3, 0, 2, 2, 0 });

    ListNode result = new Solution().mergeNodes(head);

    System.out.println("The head of the modified linked list is : " + result);
  }
}
