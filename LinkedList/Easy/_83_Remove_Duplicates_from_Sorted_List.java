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
Approach:  
We can remove duplicates from a sorted linked list by using a single pointer to traverse it.
Starting from the head, we compare the current node’s value with the next node’s value.
If they are the same, we skip the next node by changing the current node’s `next` pointer to `current.next.next`.
If they are different, we simply move the current pointer to the next node.
Since the list is sorted, all duplicates will be adjacent, so one pass is enough.

Time Complexity: O(n), where n is the number of nodes in the linked list.
Space Complexity: O(1), as we only use a constant amount of extra space.
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
        // Check for the edge case
        if (head == null) {
            return null;
        }

        // Initialize pointer for traking variable value
        ListNode current = head;

        // Logic for removing duplicate nodes from the linked list
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next; // Skip the duplicate
            } else {
                current = current.next;
            }
        }

        // Return the modified linked list
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
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
