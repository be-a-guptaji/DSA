/*
LeetCode Problem: https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/

Question: 3217. Delete Nodes From Linked List Present in Array

Problem Statement: You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.

Example 1:
Input: nums = [1,2,3], head = [1,2,3,4,5]
Output: [4,5]
Explanation:
Remove the nodes with values 1, 2, and 3.

Example 2:
Input: nums = [1], head = [1,2,1,2,1,2]
Output: [2,2,2]
Explanation:
Remove the nodes with value 1.

Example 3:
Input: nums = [5], head = [1,2,3,4]
Output: [1,2,3,4]
Explanation:
No node has value 5.

Constraints:
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^5
    All elements in nums are unique.
    The number of nodes in the given list is in the range [1, 10^5].
    1 <= Node.val <= 10^5
    The input is generated such that there is at least one node in the linked list that has a value not present in nums.
*/

/*
Approach: HashSet + Dummy Node Traversal

Goal:
- Remove every node from the linked list whose
  value appears in nums.

Core Idea:
- Checking whether a node value should be removed
  must be efficient.
- Store all values from nums in a HashSet.
- Traverse the linked list and remove nodes whose
  values exist in the set.

Data Structures:
----------------
HashSet<Integer> set

Purpose:
- O(1) average lookup for determining whether
  a node should be deleted.

Dummy Node:
-----------
A dummy node is placed before the head:

      dummy -> head

This simplifies deletion when the original head
itself must be removed.

Algorithm Steps:
1. Insert all values from nums into a HashSet.
2. Create:
      dummy -> head
3. Initialize:
      current = dummy
4. Traverse the list:
   - If:

         current.next.val exists in set

     remove the node:

         current.next = current.next.next

   - Otherwise move forward:

         current = current.next

5. Return:

      dummy.next

Example:
---------

nums:
    [1, 2, 3]

List:
    1 -> 2 -> 4 -> 5 -> 2

Traversal:
    remove 1
    remove 2
    keep 4
    keep 5
    remove 2

Result:
    4 -> 5

Why It Works:
- Every node is visited exactly once.
- The HashSet provides constant-time membership
  testing.
- The dummy node correctly handles deletions at
  the beginning of the list.

Time Complexity:
- O(n + m)

where:
- n = length of nums
- m = length of linked list

Space Complexity:
- O(n)

for the HashSet.

Result:
- Returns the linked list after removing all
  nodes whose values appear in nums.
*/

package LinkedList.Medium;

import java.util.HashSet;

// Solution Class
class Solution {
  // Method to find the head of the modified linked list after removing all nodes
  // from the linked list that have a value that exists in nums
  public ListNode modifiedList(int[] nums, ListNode head) {
    // Add the values in the hashset
    HashSet<Integer> set = new HashSet<>();

    // Initialize the dummy node
    ListNode dummy = new ListNode(0, head);

    // Initialize the current node
    ListNode current = dummy;

    // Add the nums values to the hashset
    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }

    // Iterate over the current.next untill it become null
    while (current.next != null) {
      // Update the pointer accordingly
      if (set.contains(current.next.val)) {
        current.next = current.next.next;
      } else {
        current = current.next;
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
public class _3217_Delete_Nodes_From_Linked_List_Present_in_Array {
  // Main method to test modifiedList
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3 };
    ListNode head = new ListNode().makelist(new int[] { 1, 2, 3, 4, 5 });

    ListNode result = new Solution().modifiedList(nums, head);

    System.out.println(
        "The head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums is : "
            + result);
  }
}
