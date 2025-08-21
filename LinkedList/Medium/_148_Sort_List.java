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
   -> If the head is null or head.next is null then the list is already sorted.
2. Find the middle node
   -> Use slowPointer and fastPointer.
   -> Move slow by 1 step and fast by 2 steps until fast reaches the end.
   -> Track the node before slow using previousPointer.
   -> Break the list into two halves by setting previousPointer.next = null.
3. Recursively sort both halves
   -> Call sortList on the left half (head).
   -> Call sortList on the right half (slowPointer).
   -> This keeps dividing the list until each part has only one node.
4. Merge the two sorted halves
   -> Use a dummy node to simplify merging.
   -> Compare left and right nodes.
   -> Append the smaller node to the merged list.
   -> Move the pointer forward for the list from which node was taken.
5. Handle remaining nodes
   -> If left still has nodes, attach them.
   -> If right still has nodes, attach them.
6. Return the sorted list
   -> Return head.next (skipping the dummy node).

Time Complexity: O(n log n)
Space Complexity: O(log n) due to recursion stack
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
        // Check the edge case
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize two pointers for the traversing
        ListNode previousPointer = head, slowPointer = head, fastPointer = head;

        // Traverse the linked list for finding the middle
        while (fastPointer != null && fastPointer.next != null) {
            previousPointer = slowPointer;
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // Make the last node of first half point to null
        previousPointer.next = null;

        // Reccusively call the sort until the sorted list is size of one
        ListNode left = sortList(head);
        ListNode right = sortList(slowPointer);

        // Merge both the list and return that
        return merge(left, right);
    }
    
    // Helper function for merging the two list
    public static ListNode merge(ListNode left, ListNode right) {
        // Initialize variable for merging
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode current = head;

        // Merge the list until one extinguish
        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            // Move the pointer
            current = current.next;
        }

        // Extinguish the left
        while (left != null) {
            current.next = left;

            // Move the pointers
            left = left.next;
            current = current.next;
        }

        // Extinguish the right
        while (right != null) {
            current.next = right;

            // Move the pointer
            right = right.next;
            current = current.next;
        }

        // Return the head of the list
        return head.next;
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
