/*
LeetCode Problem: https://leetcode.com/problems/rotate-list/

Question: 61. Rotate List

Problem Statement: Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 10^9
*/

/*
Approach: We solve the problem of rotating the linked list by first converting the list into a circular linked list,  
and then breaking it at the correct position to achieve the rotation.  

Steps:  
1. Handle the edge case where the list is empty (head == null).  
2. Count the total number of nodes in the list by traversing it once.  
   - Keep a pointer `current` starting from head.  
   - Traverse till the end while incrementing the node counter.  
3. Link the last node back to the head to form a circular linked list.  
4. The effective number of rotations is `(n % numberOfNode)` because rotating by a multiple of the list size  
   results in the same list.  
5. To find the new head, calculate how many steps to move from the start:  
   - `k = numberOfNode - (n % numberOfNode)` gives the position of the new tail.  
6. Move the `current` pointer `k` times to reach the new tail.  
7. The node after `current` becomes the new head.  
8. Break the circular link by setting `current.next = null`.  
9. Return the new head of the rotated list.  

Time Complexity: O(n), because we traverse the list twice (once for counting nodes and once for moving to the new tail).  
Space Complexity: O(1), since no extra data structures are used.  
*/

package LinkedList.Medium;

public class _61_Rotate_List {
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

    // Method to rotate linked list n times
    public static ListNode rotateRight(ListNode head, int n) {
        // Check the edge case
        if (head == null) {
            return null;
        }

        // Initialize pointers for counting the number of node
        ListNode current = head;

        // Initialize a variable for counting nodes
        int numberOfNode = 1;

        // Count the number of node
        while (current.next != null) {
            current = current.next;
            numberOfNode++;
        }

        // Make it cicular linked list
        current.next = head;

        // Initialize variable for where the rotation of list occur
        int k = (numberOfNode - (n % numberOfNode));

        // Move the pointer where rotation is to be preformed
        for (int i = 0; i < k; i++) {
            current = current.next;
        }

        // Change the head of linked list
        head = current.next;

        // Rotate the linked list
        current.next = null;

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

    // Main method to test rotateRight
    public static void main(String[] args) {
        int[] head = { 0, 1, 2 };
        int k = 4;

        ListNode result = rotateRight(makelist(head), k);

        System.out.print("The linked list after rotating list n times is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
