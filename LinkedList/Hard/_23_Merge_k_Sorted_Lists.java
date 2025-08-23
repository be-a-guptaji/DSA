/*
LeetCode Problem: https://leetcode.com/problems/merge-k-sorted-lists/

Question: 23. Merge k Sorted Lists

Problem Statement: You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6

Example 2:
Input: lists = []
Output: []

Example 3:
Input: lists = [[]]
Output: []

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 10^4.
*/

/*
Approach:
1. Handle edge cases
   If the array of lists is null or empty, simply return null.
2. Use a Min-Heap
   Create a priority queue (min-heap) where the comparator is based on node values.
   This ensures we always extract the smallest node among the available list heads.
3. Push all initial nodes
   Iterate through the input array and add all non-null heads into the heap.
4. Build the merged list
   a) Initialize a dummy node to simplify result list construction.
   b) While the heap is not empty:
        - Extract the minimum node from the heap.
        - Attach it to the current end of the merged list.
        - If the extracted node has a next pointer, insert that next node into the heap.
5. Return the merged list
   Finally return dummy.next which represents the head of the merged sorted list.

Time Complexity: O(n log k)  
   n = total number of nodes across all lists  
   k = number of linked lists  
   Each insertion and extraction from heap costs O(log k).
Space Complexity: O(k)  
   Heap stores at most one node from each list at a time.
*/

package LinkedList.Hard;

import java.util.PriorityQueue;

public class _23_Merge_k_Sorted_Lists {
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

    // Method to merge k sorted lists using a Min-Heap
    public static ListNode mergeKLists(ListNode[] lists) {
        // Edge case check
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Initialize the min-heap based on node values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of all non-null lists into the heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // Initialize the dummy node for result list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Process until the heap becomes empty
        while (!minHeap.isEmpty()) {
            // Extract the minimum node from heap
            ListNode minNode = minHeap.poll();

            // Attach it to the result list
            current.next = minNode;
            current = current.next;

            // If the extracted node has a next, add it to the heap
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        // Return the merged sorted list
        return dummy.next; // Skip the dummy node
    }

    // Function to convert int[][] into an array of ListNode
    public static ListNode[] makelist(int[][] lists) {
        if (lists.length == 0) {
            return new ListNode[0]; // Handle empty input
        }

        ListNode[] result = new ListNode[lists.length];

        // Convert each array into a linked list
        for (int i = 0; i < lists.length; i++) {
            if (lists[i].length == 0) {
                result[i] = null; // Empty sublist
                continue;
            }

            ListNode head = new ListNode(lists[i][0]); // First node
            ListNode current = head;

            for (int j = 1; j < lists[i].length; j++) {
                current.next = new ListNode(lists[i][j]);
                current = current.next;
            }

            result[i] = head; // Store head of this sublist
        }

        return result; // Array of linked list heads
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

    // Main method to test mergeKLists
    public static void main(String[] args) {
        int[][] lists = { { 1, 4, 5 }, { 1, 3, 4 }, { 2, 6 } };

        ListNode result = mergeKLists(makelist(lists));

        System.out.print("The linked list after mergeing is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
