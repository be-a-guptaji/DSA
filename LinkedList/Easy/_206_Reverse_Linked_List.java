/*
LeetCode Problem: https://leetcode.com/problems/reverse-linked-list/

Question: 206. Reverse Linked List

Problem Statement: Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:
Input: head = [1,2]
Output: [2,1]

Example 3:
Input: head = []
Output: []

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

/*
Approach: We solve this problem by iterating through the linked list and reversing the pointers step by step.
We keep track of three pointers: 
1. previousPointer – stores the previous node (initially null)
2. currentPointer – stores the current node (initially head)
3. nextPointer – stores the next node (used to avoid losing the rest of the list while reversing)

At every step:
- Save the next node in nextPointer.
- Reverse the current node’s link to point to the previous node.
- Move previousPointer and currentPointer one step forward.

In the end, previousPointer will point to the new head of the reversed linked list.

Time Complexity: O(n), where n is the number of nodes in the linked list.
Space Complexity: O(1), since we only use a constant amount of extra space.
*/

package LinkedList.Easy;

public class _206_Reverse_Linked_List {
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

    // Method to reverse the linked list
    public static ListNode reverseList(ListNode head) {
        // Check all edge cases
        if (head == null) {
            return null;
        }

        // Initialize pointers for reversing the list
        ListNode previousPointer = null, currentPointer = head, nextPointer;

        // Reverse first half while finding the middle
        while (currentPointer != null) {
            // Save next node before reversing
            nextPointer = currentPointer.next;
            // Reverse currentPointer node's link
            currentPointer.next = previousPointer;
            // Move previous and slow forward
            previousPointer = currentPointer;
            currentPointer = nextPointer;
        }

        // Return the previous pointer as the head of the reversed linked list
        return previousPointer;
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

    // Main method to test reverseList
    public static void main(String[] args) {
        int[] head = { 1, 2, 3, 4, 5};

        ListNode result = reverseList(makelist(head));

        System.out.print("The reverse linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
