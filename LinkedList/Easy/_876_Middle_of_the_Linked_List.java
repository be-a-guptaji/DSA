/*
LeetCode Problem: https://leetcode.com/problems/middle-of-the-linked-list/

Question: 876. Middle of the Linked List

Problem Statement: Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

Example 1:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Example 2:
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.

Constraints:

The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100
 */

/*
Approach: We can solve this problem using the two-pointer technique combined with in-place reversal of the first half of the linked list.

1. Use two pointers — `fastPointer` (moves two steps) and `slowPointer` (moves one step) — to find the middle of the list.
2. While finding the middle, reverse the first half of the linked list in place.
3. If the list has an odd length, skip the middle element by moving `slowPointer` one step ahead.
4. Compare the reversed first half (`previousPointer`) with the second half (`slowPointer`) node by node.
5. If any mismatch is found, return `false`.
6. If all values match, return `true`.

Time Complexity: O(n), where n is the number of nodes in the linked list.
Space Complexity: O(1), since we reverse the list in place without extra storage.
*/

package LinkedList.Easy;

public class _876_Middle_of_the_Linked_List {
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

    // Method to find if the linked list is palindromic or not
    public static ListNode middleNode(ListNode head) {
        // Check all edge cases
        if (head == null) {
            return null;
        }

        // Initialize two pointers for finding the middle
        ListNode fastPointer = head, slowPointer = head;

        // Reverse first half while finding the middle
        while (fastPointer != null && fastPointer.next != null) {
            // Move fast pointer twice and slow pointer once
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

       //
        return true;
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

    // Main method to test middleNode
    public static void main(String[] args) {
        int[] head = { 1, 2, 3, 4, 5 };

        ListNode result = middleNode(makelist(head));

        System.out.print("The merged list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
