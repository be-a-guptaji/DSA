/*
LeetCode Problem: https://leetcode.com/problems/reorder-list/

Question: 143. Reorder List

Problem Statement: You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4]
Output: [1,4,2,3]

Example 2:
Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Constraints:

The number of nodes in the list is in the range [1, 5 * 10^4].
1 <= Node.val <= 1000
*/

/*
Approach:
1. Find the middle of the linked list using slow and fast pointers.
   - slowPointer moves one step, fastPointer moves two steps.
   - when fastPointer reaches the end, slowPointer will be at middle.
2. Reverse the second half of the list starting from slowPointer.next.
   - standard linked list reversal using previous, current, next pointers.
3. Merge the two halves:
   - one node from first half, one node from reversed second half alternately.
   - continue until all nodes are merged.
4. Return head as the reordered list.

Time Complexity: O(n), since we traverse the list a constant number of times.
Space Complexity: O(1), as only a few extra pointers are used.
*/

package LinkedList.Medium;

public class _143_Reorder_List {
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

    // Method to reorder the list
    public static ListNode reorderList(ListNode head) {
        // Check the edge case
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize two pointers for the traversing
        ListNode slowPointer = head, fastPointer = head;

        // Traverse the linked list for finding the middle
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // Initialize pointers for reversing the list
        ListNode previousPointer = null, currentPointer = slowPointer.next, nextPointer;

        // Make the last node of first half point to null
        slowPointer.next = null;

        // Reverse the second half of list
        while (currentPointer != null) {
            // Save next node before reversing
            nextPointer = currentPointer.next;
            // Reverse currentPointer node's link
            currentPointer.next = previousPointer;
            // Move previous and slow forward
            previousPointer = currentPointer;
            currentPointer = nextPointer;
        }

        // Initialize two pointer for the head of the both of the linked list
        ListNode list1 = head, list2 = previousPointer;

        // Merge both of the list
        while (list1 != null && list2 != null) {
            // Initialize the two next variable for both of the linked list
            ListNode next1 = list1.next, next2 = list2.next;

            // Merge both the linked list
            list1.next = list2;
            list2.next = next1;
            list1 = next1;
            list2 = next2;
        }

        // Return the head of the modified linked list
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

    // Main method to test reorderList
    public static void main(String[] args) {
        int[] head = { 1, 2, 3, 4 };

        ListNode result = reorderList(makelist(head));

        System.out.print("The reordered linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
