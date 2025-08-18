/*
LeetCode Problem: https://leetcode.com/problems/partition-list/

Question: 86. Partition List

Problem Statement: Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

Example 2:
Input: head = [2,1], x = 2
Output: [1,2]

Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
*/

/*
Approach:
1. Check the edge case:
   - If head is null, return null immediately.
2. Create two dummy nodes:
   - beforeDummy → holds nodes with values less than x.
   - afterDummy → holds nodes with values greater than or equal to x.
3. Use two pointers:
   - before → builds the "before" list (values < x).
   - after → builds the "after" list (values >= x).
4. Traverse the linked list:
   - If current.val < x → attach current node to before list and move before.
   - Else → attach current node to after list and move after.
   - Move current to the next node each step.
5. Terminate the after list:
   - Set after.next = null to prevent cycle.
6. Join the two lists:
   - Connect before.next = afterDummy.next.
7. Return the new head:
   - Which is beforeDummy.next (skip the dummy node).

Time Complexity: O(n), where n is the number of nodes in the linked list (each node is processed once).
Space Complexity: O(1), as only constant extra space is used.
*/

package LinkedList.Medium;

public class _86_Partition_List {
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

    // Method to partition a list
    public static ListNode partition(ListNode head, int x) {
        // Check the edge case
        if (head == null) {
            return null;
        }

        // Dummy heads (constant extra space)
        ListNode beforeDummy = new ListNode(0);
        ListNode afterDummy = new ListNode(0);

        // Pointers for building the two partitions
        ListNode before = beforeDummy;
        ListNode after = afterDummy;

        // Traverse and rearrange nodes
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        // Cut off cycle
        after.next = null;

        // Merge lists
        before.next = afterDummy.next;

        // Retrun the head of the before list
        return beforeDummy.next; // Skip the dummy node
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
        ListNode next = null;

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

    // Main method to test partition
    public static void main(String[] args) {
        int[] head = { 2, 1 };
        int x = 2;

        ListNode result = partition(makelist(head), x);

        System.out.print("The partion linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
