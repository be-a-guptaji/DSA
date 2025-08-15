/*
LeetCode Problem: https://leetcode.com/problems/merge-two-sorted-lists/

Question: 21. Merge Two Sorted Lists

Problem Statement: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 10^4
-10^9 <= nums[i] <= 10^9
-10^9 <= target <= 10^9
Only one valid answer exists.


Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */

/*
Approach: We can solve this problem using a dummy node to simplify list construction.  
We maintain a `currentNode` pointer starting at the dummy node.  
We iterate through both linked lists simultaneously:  
- Compare the current values of `list1` and `list2`.  
- Append the smaller value as a new node to the merged list.  
- Move the pointer forward in the list from which the node was taken.  

Once one list is fully traversed, append all remaining nodes from the other list.  
Finally, return `dummy.next` as the head of the merged linked list.  

Time Complexity: O(m + n), where m and n are the number of nodes in `list1` and `list2`.  
Space Complexity: O(m + n) because a new node is created for each element.  
If we reuse nodes instead of creating new ones, the space complexity becomes O(1).  
*/

package LinkedList.Easy;

public class _21_Merge_Two_Sorted_Lists {
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

    // Method to merge two lists
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Check all edge cases
        if (list1 == null && list2 == null) {
            return null;
        } else if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        // Initialize a new merged node list
        ListNode mergedNodehead = new ListNode(), currentNode = mergedNodehead;

        // Logic to merge two list
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                currentNode.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                currentNode.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            currentNode = currentNode.next;
        }

        // Extinguish the first list
        while (list1 != null) {
            currentNode.next = new ListNode(list1.val);
            currentNode = currentNode.next;
            list1 = list1.next;
        }

        // Extinguish the second list
        while (list2 != null) {
            currentNode.next = new ListNode(list2.val);
            currentNode = currentNode.next;
            list2 = list2.next;
        }

        // Return the head of the merged list node
        return mergedNodehead.next;  // Skip Dummy Node
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

    // Main method to test mergeTwoLists
    public static void main(String[] args) {
        int[] list1 = { 1, 2, 4 }, list2 = { 1, 3, 4 };

        ListNode result = mergeTwoLists(makelist(list1), makelist(list2));

        System.out.print("The merged list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
