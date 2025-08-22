/*
LeetCode Problem: https://leetcode.com/problems/sort-list/

Question: 148. Sort List

Problem Statement: Given the head of a linked list, return the list after sorting it in ascending order.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Example 3:
Input: head = []
Output: []

Constraints:

The number of nodes in the list is in the range [0, 5 * 10^4].
-10^5 <= Node.val <= 10^5

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
*/

/*
Approach:
1. Check edge case
   If head is null or head.next is null then the list is already sorted.
2. Find the length of the linked list
   Traverse the list once and count the number of nodes.
3. Use a dummy node
   Create a dummy node pointing to head for easier merging.
4. Perform bottom-up merge sort
   Start with sublists of size 1, then 2, then 4, and so on until size < length.
   For each iteration:
      - Split the list into left and right sublists of given size using split().
      - Merge the two sublists using merge().
      - Attach the merged list to the previous node.
5. Continue until the whole list is merged
   The list becomes fully sorted after log n passes.
6. Return the sorted list
   Return dummy.next as the head of the sorted list.

Time Complexity: O(n log n)
Space Complexity: O(1) because sorting is done iteratively in place
*/

package LinkedList.Medium;

public class _148_Sort_List {
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

    // Method to sort the linked list
    public static ListNode sortList(ListNode head) {
        // Edge case check when list is empty or has only one node
        if (head == null || head.next == null) {
            return head;
        }

        // Find the length of the linked list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Dummy node for easier merging
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Logic for bottom up merge sort
        for (int step = 1; step < length; step *= 2) {
            ListNode prev = dummy;
            current = dummy.next;

            while (current != null) {
                // Left sublist head
                ListNode left = current;

                // Right sublist head obtained by splitting
                ListNode right = split(left, step);

                // Next sublist start obtained by splitting again
                current = split(right, step);

                // Merge left and right sublists, connect to prev
                prev = merge(left, right, prev);
            }
        }

        // Return the head of the fully sorted linked list
        return dummy.next;
    }

    // Helper function to split the node
    private static ListNode split(ListNode head, int size) {
        // Return if head is empty
        if (head == null)
            return null;

        // Get all the requied nodes for the sublist
        for (int i = 1; head.next != null && i < size; i++) {
            head = head.next;
        }

        // Get the next node and terminate the current linked list
        ListNode next = head.next;
        head.next = null;

        // Retrun the head of the next sublist
        return next;
    }

    // Helper function to merge the left and right list to the head
    private static ListNode merge(ListNode left, ListNode right, ListNode head) {
        ListNode current = head;

        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        // Attach remaining nodes from either list
        current.next = (left != null) ? left : right;

        // Move current pointer to the end of merged list
        while (current.next != null) {
            current = current.next;
        }

        // Return the head of the linked list
        return current;
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

    // Main method to test sortList
    public static void main(String[] args) {
        int[] head = { -1, 5, 3, 4, 0 };

        ListNode result = sortList(makelist(head));

        System.out.print("The sorted linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
