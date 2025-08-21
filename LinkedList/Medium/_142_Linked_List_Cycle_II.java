/*
LeetCode Problem: https://leetcode.com/problems/linked-list-cycle-ii/

Question: 142. Linked List Cycle II

Problem Statement: Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Constraints:

The number of the nodes in the list is in the range [0, 10^4].
-10^5 <= Node.val <= 10^5
pos is -1 or a valid index in the linked-list.

Follow up: Can you solve it using O(1) (i.e. constant) memory?
*/

/*
Approach:
1. Use two pointers (slow and fast) starting from the head.
   - slow → moves one step at a time.
   - fast → moves two steps at a time.

2. Traverse the list until:
   - fast reaches the end → no cycle exists → return null.
   - slow and fast meet → cycle is detected.

3. Once cycle is detected:
   - Keep one pointer at the meeting point.
   - Place another pointer at the head of the list.
   - Move both pointers one step at a time.
   - The node where they meet again is the start of the cycle.

Why this works:
- At the meeting point: distance traveled by slow = L1 + L2,
  distance traveled by fast = L1 + L2 + n*C (C = cycle length).
- Since fast travels twice as fast:
    2 * (L1 + L2) = L1 + L2 + n*C  →  L1 = n*C - L2
- Which means if we move one pointer from head and the other
  from meeting point, both will meet at the cycle start.

Time Complexity: O(n) → each pointer traverses at most 2n steps.
Space Complexity: O(1) → only constant extra pointers used.
*/

package LinkedList.Medium;

public class _142_Linked_List_Cycle_II {
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

    // Method to detect cycle on the node
    public static ListNode detectCycle(ListNode head) {
        // Handle edge cases
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize fast and slow pointers
        ListNode slowPointer = head, fastPointer = head;

        // Logic to detect cycle
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next; // Move slow by 1
            fastPointer = fastPointer.next.next; // Move fast by 2

            // If both pointers meet, cycle exists
            if (slowPointer == fastPointer) {
                // Reset one pointer to head
                ListNode entry = head;

                // Move both one step at a time until they meet
                while (entry != slowPointer) {
                    entry = entry.next;
                    slowPointer = slowPointer.next;
                }

                // The meeting point is the start of the cycle
                return entry;
            }
        }

        // No cycle found
        return null;
    }

    // Function to convert the list into the ListNode with cycle
    public static ListNode makelist(int[] list, int pos) {
        // Handle empty array
        if (list.length == 0) {
            return null;
        }

        // First element as head
        ListNode head = new ListNode(list[0]);
        ListNode current = head;

        // Pointer to store the cycle connection node
        ListNode cycleNode = null;

        // If the pos is 0
        if (0 == pos) {
            cycleNode = head;
        }

        // Traverse the array and create linked list
        for (int i = 1; i < list.length; i++) {
            current.next = new ListNode(list[i]);
            current = current.next;

            // Store the node at position 'pos' for cycle connection
            if (i == pos) {
                cycleNode = current;
            }
        }

        // If pos is valid, connect the last node to cycleNode
        if (pos != -1) {
            current.next = cycleNode;
        }

        // Return the head of the list
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

    // Main method to test detectCycle
    public static void main(String[] args) {
        int[] head = { 1, 2 };
        int pos = 0;

        ListNode result = detectCycle(makelist(head, pos));

        if (result != null) {
            System.out.println("The linked list node which have the cycle is : " + result.val);
        } else {
            System.out.println("The linked list dose not contain any cycle.");
        }
    }
}
