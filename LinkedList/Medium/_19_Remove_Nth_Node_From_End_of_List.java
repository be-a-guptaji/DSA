/*
LeetCode Problem: https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Question: 19. Remove Nth Node From End of List

Problem Statement: Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

Follow up: Could you do this in one pass?
*/

/*
Approach: We solve this problem using the two-pointer technique with a dummy node.

1. Create a dummy node that points to the head of the list.  
   This simplifies deletion, especially when the head itself needs to be removed.  

2. Maintain two pointers:
   - fast pointer – used to create a gap of 'n' nodes.
   - current pointer – used to find the node just before the target node.  

3. Move the fast pointer 'n' steps forward.  
   This ensures that when the fast pointer reaches the end,  
   the current pointer will be just before the node to be deleted.  

4. Move both pointers forward until the fast pointer reaches the last node.  

5. Skip the Nth node from the end by adjusting links:  
   current.next = current.next.next  

6. Return dummy.next as the new head of the modified list.  

Time Complexity: O(n), since we traverse the list once.  
Space Complexity: O(1), as we only use constant extra space.
*/

package LinkedList.Medium;

public class _19_Remove_Nth_Node_From_End_of_List {
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

    // Method to remove Nth node from end of list
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // Check the edge case
        if (head == null) {
            return null;
        }

        // Initialize a dummy node to simplify deletion cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize two pointers
        ListNode current = dummy, fast = dummy;

        // Move the fast pointer N steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the last node
        while (fast.next != null) {
            current = current.next;
            fast = fast.next;
        }

        // Remove the Nth node
        current.next = current.next.next;

        // Return the head of the modified linked list
        return dummy.next;
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

    // Main method to test removeNthFromEnd
    public static void main(String[] args) {
        int[] head = { 1, 2, 3, 4, 5 };
        int n = 5;

        ListNode result = removeNthFromEnd(makelist(head), n);

        System.out.print("The linked list after removing Nth node from the end is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
