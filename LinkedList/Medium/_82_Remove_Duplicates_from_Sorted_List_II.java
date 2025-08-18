/*
LeetCode Problem: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

Question: 82. Remove Duplicates from Sorted List II

Problem Statement: Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.

Example 1:
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Example 2:
Input: head = [1,1,1,2,3]
Output: [2,3]

Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
*/

/*
Approach:
1. Create a dummy node before the head to simplify edge cases (like when head itself is duplicate).
2. Use two pointers:
   - previous → last confirmed unique node
   - current → pointer to scan through the list
3. Traverse the list:
   - If current node value != next node value → move previous and current forward.
   - If current node value == next node value → store this duplicate value,
     and keep moving current forward until all nodes with this value are skipped.
     Then link previous.next to current (removing duplicates).
4. Continue until reaching the end of the list.
5. Return dummy.next as the new head (skipping dummy).

Time Complexity: O(n), where n is the number of nodes in the linked list (each node visited once).
Space Complexity: O(1), since only constant extra pointers are used.
*/

package LinkedList.Medium;

public class _82_Remove_Duplicates_from_Sorted_List_II {
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

    // Method to deleting all non unique items from the list
    public static ListNode deleteDuplicates(ListNode head) {
        // Check the edge case
        if (head == null) {
            return null;
        }

        // Create a dummy node to simplify head handling
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode previous = dummy, current = head;

        // Logic for removing the non unique nodes in the linked list
        while (current != null && current.next != null) {
            if (current.val != current.next.val) {
                // Move both pointers forward
                previous = current;
                current = current.next;
            } else {
                int nonUniqueValue = current.val;

                // Skip all nodes that have similar value
                while (current != null && current.val == nonUniqueValue) {
                    current = current.next;
                }

                // Update the previous pointer
                previous.next = current;
            }
        }

        // Retrun the head of the modified list
        return dummy.next; // Skip the dummy node
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
        int[] head = { 1, 2, 3, 3, 4, 4, 5 };

        ListNode result = deleteDuplicates(makelist(head));

        System.out.print("The linked list after deleting all non unique items from the list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
