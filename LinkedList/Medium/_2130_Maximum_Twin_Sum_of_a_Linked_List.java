/*
LeetCode Problem: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/

Question: 2130. Maximum Twin Sum of a Linked List

Problem Statement: In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

    For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.

The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

Example 1:
Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6. 

Example 2:
Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7. 

Example 3:
Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.

Constraints:
    The number of nodes in the list is an even integer in the range [2, 10^5].
    1 <= Node.val <= 10^5
*/

/*
Approach: Fast-Slow Pointer + Reverse Second Half

Goal:
- Find the maximum twin sum in an even-length
  linked list.

Twin Nodes:
-----------
For a list of length n:

    node[i]
    node[n - 1 - i]

form a twin pair.

Twin Sum:

    node[i].val + node[n - 1 - i].val

Return the maximum twin sum among all pairs.

Core Idea:
- Accessing nodes from both ends is difficult in a
  singly linked list.
- Split the list into two halves.
- Reverse the second half.
- Now corresponding twin nodes become aligned and
  can be traversed simultaneously.

Algorithm Steps:
1. Find the middle of the list using:
      slow pointer
      fast pointer

   When the loop ends:
      slow points to the beginning of the
      second half.

2. Reverse the second half of the list.

3. Initialize:
      head    -> first half
      newHead -> reversed second half

4. Traverse both lists together:
      twinSum = head.val + newHead.val

   Update:
      maximumTwinSum

5. Return the maximum twin sum.

Example:
---------

Input:

    5 -> 4 -> 2 -> 1

Middle:

    2 -> 1

Reverse second half:

    1 -> 2

Compare pairs:

    5 + 1 = 6
    4 + 2 = 6

Answer:

    6

Another Example:

    4 -> 2 -> 2 -> 3

Reverse second half:

    3 -> 2

Twin sums:

    4 + 3 = 7
    2 + 2 = 4

Answer:

    7

Why It Works:
- Reversing the second half transforms:

      node[n-1]
      node[n-2]
      ...

  into the traversal order needed to pair with:

      node[0]
      node[1]
      ...

- Each iteration directly evaluates one twin pair.

Time Complexity:
- O(n)

  Find middle:      O(n)
  Reverse half:     O(n)
  Compare pairs:    O(n)

Space Complexity:
- O(1)

Result:
- Returns the maximum twin sum among all twin
  node pairs in the linked list.
*/

package LinkedList.Medium;

// Solution Class
class Solution {
  // Method to find the maximum twin sum of the linked list
  public int pairSum(ListNode head) {
    // Initialize the fast and slow pointer
    ListNode fast = head;
    ListNode slow = head;

    // Find the middle of the linked list
    while (fast != null && fast.next != null) {
      // Update the pointers
      slow = slow.next;
      fast = fast.next.next;
    }

    // Initialize three pointer
    ListNode previous = null;
    ListNode current = slow;
    ListNode next;

    // Iterate over the current untill it become null
    while (current != null) {
      // Update the pointers
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }

    // Get the new head of the second list
    ListNode newHead = previous;

    // Initialize the maximum twin sum variable
    int maximumTwinSum = Integer.MIN_VALUE;

    while (head != null && newHead != null) {
      // Update the maximum twin sum
      maximumTwinSum = Math.max(maximumTwinSum, head.val + newHead.val);

      // Update the pointers
      head = head.next;
      newHead = newHead.next;
    }

    // Return the maximum twin sum
    return maximumTwinSum;
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
public class _2130_Maximum_Twin_Sum_of_a_Linked_List {
  // Main method to test pairSum
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { 5, 4, 2, 1 });

    int result = new Solution().pairSum(head);

    System.out.println("The maximum twin sum of the linked list is : " + result);
  }
}
