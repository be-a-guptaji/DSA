/*
LeetCode Problem: https://leetcode.com/problems/remove-duplicates-from-sorted-list/

Question: 83. Remove Duplicates from Sorted List

Problem Statement: Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Example 1:
Input: head = [1,1,2]
Output: [1,2]

Example 2:
Input: head = [1,1,2,3,3]
Output: [1,2,3]

Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
 */

/*
Approach: This problem can be solved using Floyd’s Cycle Detection Algorithm (Tortoise and Hare approach).
We maintain two pointers: a slow pointer that moves one step at a time, and a fast pointer that moves two steps at a time.
If there is a cycle in the linked list, the fast pointer will eventually meet the slow pointer inside the cycle.
If the fast pointer reaches the end of the list (null), then there is no cycle.

Time Complexity: O(n), where n is the number of nodes in the linked list.
Space Complexity: O(1), as we only use two pointers and no additional data structures.
*/

package LinkedList.Easy;

public class _83_Remove_Duplicates_from_Sorted_List {
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

    // Method to remove duplicate in the linked list
    public static ListNode deleteDuplicates(ListNode head) {
      
        return head;
    }

    // Function to convert the list into the ListNode
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

    // Mock class for makeing the ListNode Class
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Main method to test deleteDuplicates
    public static void main(String[] args) {
        int[] head = { 1, 1, 2 };

        ListNode result = deleteDuplicates(makelist(head));

        System.out.print("The merged list is : ");

        while (result != null) {
            System.out.print(result.val + ", ");
            result = result.next;
        }
    }
}
