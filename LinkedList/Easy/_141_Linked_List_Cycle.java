/*
LeetCode Problem: https://leetcode.com/problems/linked-list-cycle/

Question: 141. Linked List Cycle

Problem Statement: Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Constraints:

The number of the nodes in the list is in the range [0, 10^4].
-10^5 <= Node.val <= 10^5
pos is -1 or a valid index in the linked-list.

Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */

/*
Approach: This problem can be solved using Floyd’s Cycle Detection Algorithm (Tortoise and Hare approach).
We maintain two pointers: a slow pointer that moves one step at a time, and a fast pointer that moves two steps at a time.
If there is a cycle in the linked list, the fast pointer will eventually meet the slow pointer inside the cycle.
If the fast pointer reaches the end of the list (null), then there is no cycle.

Time Complexity: O(n), where n is the number of nodes in the linked list.
Space Complexity: O(1), as we only use two pointers and no additional data structures.
*/

package LinkedList.Easy;

public class _141_Linked_List_Cycle {
    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */

    // Method to find if the linked list has cycle or not
    public static boolean hasCycle(ListNode head) {
        // Check all edge cases
        if (head == null || head.next == null) {
            return false;
        }

        // Initialize two pointer fast and slow pointer
        ListNode fastPointer = head, slowPointer = head;

        // Logic to find the cycle
        while (fastPointer != null && fastPointer.next != null) {
            // Move fast pointer twice and slow pointer once
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            // Retrun true if fast and slow pointer meet
            if (fastPointer == slowPointer) {
                return true;
            }
        }

        // Retrun flase if fastPointer reaches the end of the linked list
        return false;
    }

    // Function to convert the list into the ListNode which may have cycle
    public static ListNode makelist(int[] list, int pos) {
        if (list.length == 0) {
            return null; // Handle empty array
        }

        ListNode head = new ListNode(list[0]); // First element as head
        ListNode current = head;
        ListNode cycleNode = null; // Will store reference to cycle start node

        for (int i = 1; i < list.length; i++) {
            current.next = new ListNode(list[i]);
            current = current.next;

            // If current index matches `pos`, store the node reference
            if (i == pos) {
                cycleNode = current;
            }
        }

        // If pos is 0, cycle starts from head
        if (pos == 0) {
            cycleNode = head;
        }

        // Create the cycle if pos is valid
        if (pos != -1 && cycleNode != null) {
            current.next = cycleNode; // Link last node to the cycle start
        }

        return head;
    }

    // Mock class for makeing the ListNode Class
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // Main method to test mergeTwoLists
    public static void main(String[] args) {
        int[] head = { 3, 2, 0, -4 };
        int pos = 1;

        boolean result = hasCycle(makelist(head, pos));

        if (result) {
            System.out.print("The ListNode contain cycle.");
        } else {
            System.out.print("The ListNode does not contain cycle.");
        }
    }
}
