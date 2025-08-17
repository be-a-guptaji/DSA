/*
LeetCode Problem: https://leetcode.com/problems/swap-nodes-in-pairs/

Question: 24. Swap Nodes in Pairs

Problem Statement: Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

Example 1:
Input: head = [1,2,3,4]
Output: [2,1,4,3]

Example 2:
Input: head = []
Output: []

Example 3:
Input: head = [1]
Output: [1]

Example 4:
Input: head = [1,2,3]
Output: [2,1,3]

Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
*/

/*
Approach: We solve this problem by swapping every two adjacent nodes in the linked list.  
To simplify handling the head node, we use a dummy node that points to the head.  
A pointer (current) is used to traverse the list and perform swaps.  

At every step:  
1. Identify two nodes to swap: first and second.  
2. Update pointers so that 'second' comes before 'first'.  
   - first.next = second.next  
   - second.next = first  
   - current.next = second  
3. Move the current pointer two steps ahead (to first) to continue swapping the next pair.  

In the end, dummy.next points to the new head of the list.  

Time Complexity: O(n), where n is the number of nodes in the list (we traverse once).  
Space Complexity: O(1), since we only use constant extra space.  
*/

package LinkedList.Medium;

public class _24_Swap_Nodes_in_Pairs {
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

    // Method to swap pair in the Linked List
    public static ListNode swapPairs(ListNode head) {
        // Check the edge case
        if (head == null) {
            return null;
        }

        // Create a dummy node to simplify head handling
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        // Swap the pointer while traversing the list
        while (current.next!=null&&current.next.next!=null) {
            // Identify the two nodes to swap
            ListNode first = current.next;
            ListNode second = current.next.next;

            // Perform the swap
            first.next = second.next;
            second.next = first;
            current.next = second;

            // Move current pointer two steps ahead
            current = first;
        }

        // Return the new head and skip the dummy node
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

    // Main method to test swapPairs
    public static void main(String[] args) {
        int[] head = { 1, 2, 3, 4 };

        ListNode result = swapPairs(makelist(head));

        System.out.print("The modified linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
