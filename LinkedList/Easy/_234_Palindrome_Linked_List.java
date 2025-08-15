/*
LeetCode Problem: https://leetcode.com/problems/palindrome-linked-list/

Question: 234. Palindrome Linked List

Problem Statement: Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Example 1:
Input: head = [1,2,2,1]
Output: true

Example 2:
Input: head = [1,2]
Output: false

Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?
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

public class _234_Palindrome_Linked_List {
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
    public static boolean isPalindrome(ListNode head) {
        // Check all edge cases
        if (head == null || head.next == null) {
            return true;
        }

        // Initialize two pointers for finding the middle
        ListNode fastPointer = head, slowPointer = head;
        ListNode previousPointer = null, nextPointer;

        // Reverse first half while finding the middle
        while (fastPointer != null && fastPointer.next != null) {
            // Move the fast pointer twice
            fastPointer = fastPointer.next.next;

            // Save next node before reversing
            nextPointer = slowPointer.next;

            // Reverse current node's link
            slowPointer.next = previousPointer;

            // Move previous and slow forward
            previousPointer = slowPointer;
            slowPointer = nextPointer;
        }

        // If the list has odd length, skip the middle element
        if (fastPointer != null) {
            slowPointer = slowPointer.next;
        }

        // Compare reversed first half with second half
        while (slowPointer != null && previousPointer != null) {
            if (slowPointer.val != previousPointer.val) {
                return false;
            }
            slowPointer = slowPointer.next;
            previousPointer = previousPointer.next;
        }

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

    // Main method to test isPalindrome
    public static void main(String[] args) {
        int[] head = { 1, 2, 2, 3, 1, 2, 2, 1 };

        boolean result = isPalindrome(makelist(head));

        if (result) {
            System.out.print("The ListNode is a palindrome.");
        } else {
            System.out.print("The ListNode is not a palindrome.");
        }
    }
}
