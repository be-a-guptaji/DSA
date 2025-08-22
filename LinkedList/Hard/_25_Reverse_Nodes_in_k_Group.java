/*
LeetCode Problem: https://leetcode.com/problems/reverse-nodes-in-k-group/

Question: 25. Reverse Nodes in k-Group

Problem Statement: Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]

Constraints:

The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000

Follow-up: Can you solve the problem in O(1) extra memory space?
*/

/*
Approach:
1. Handle edge cases
   If the head is null or k == 1 then no reversal is required and we simply return head.
2. Use a dummy node
   Create a dummy node pointing to head to simplify connections when reversing the first group.
3. Initialize pointers
   prevGroupEnd → points to the end of the previously reversed group (starts from dummy).
   current → points to the first node of the current group.
4. Process the list in groups of k
   a) Check if there are at least k nodes remaining.
      Traverse k nodes ahead using a temporary pointer (check).
      If fewer than k nodes remain, stop processing.
   b) Reverse exactly k nodes.
      Use standard linked list reversal with pointers:
      prev, current, next.
      Keep track of the tail (the original start of the group).
   c) Reconnect the reversed group with the rest of the list.
      prevGroupEnd.next = new head of the reversed group.
      tail.next = next group’s head.
   d) Move prevGroupEnd to tail for the next group.
5. Return the new head
   Finally return dummy.next which points to the new head after all reversals.

Time Complexity: O(n)   (every node is visited once)
Space Complexity: O(1)  (reversal is done in-place)
*/

package LinkedList.Hard;

public class _25_Reverse_Nodes_in_k_Group {
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

    // Method to reverse the linked list in the group of k
    public static ListNode reverseKGroup(ListNode head, int k) {
        // Check the edge case
        if (head == null || k == 1) {
            return head;
        }

        // Initialize the new node for as the head of the linked list
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        // Pointers for traversal
        ListNode prevGroupEnd = dummy;
        ListNode current = head;

        // Traverse the linked list to the end
        while (true) {
            // Initialize the variable for tracking the group
            ListNode check = current;
            int count = 0;

            // Get the group of the k node in a linked list
            while (count < k && check != null) {
                check = check.next;
                count++;
            }

            // Break out of the loop if the count is less than the k
            if (count < k) {
                break;
            }

            // Initialize variable for reversing the linked list
            ListNode prev = null;
            ListNode tail = current; // This is the tail of the reversed linked list

            // Reverse the linked list
            for (int i = 0; i < k; i++) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            // Join the linked list
            prevGroupEnd.next = prev;
            tail.next = current;

            // Move the previous group end to the next group
            prevGroupEnd = tail;
        }

        // Return the head of the modified linked list
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

    // Main method to test reverseKGroup
    public static void main(String[] args) {
        int[] head = { 1, 2, 3, 4, 5 };
        int k = 2;

        ListNode result = reverseKGroup(makelist(head), k);

        System.out.print("The linked list after reversing the node in " + k + " groups is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
