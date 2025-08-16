/*
LeetCode Problem: https://leetcode.com/problems/intersection-of-two-linked-lists/

Question: 160. Intersection of Two Linked Lists

Problem Statement: Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:

The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.

Example 1:
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.

Example 2:
Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

Example 3:
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.

Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
1 <= m, n <= 3 * 10^4
1 <= Node.val <= 10^5
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.

Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */

/*
Approach:
1. Check for edge cases -> If either headA or headB is null then return null.
2. Initialize two pointers currentA and currentB at headA and headB.
3. Traverse both lists together until one reaches the end.
   - At this point the other pointer indicates which list is longer.
4. Create skipA and skipB to align both lists by skipping extra nodes in the longer list.
5. Move skipA and skipB one step at a time until they meet.
6. If they meet, return the intersection node else return null.

Time Complexity -> O(m + n), where m and n are lengths of the two linked lists.
Space Complexity -> O(1), because only constant extra pointers are used.
*/

package LinkedList.Easy;

public class _160_Intersection_of_Two_Linked_Lists {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */

    // Method to find the intersection of the linked list
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Edge Case Check
        if (headA == null || headB == null) {
            return null;
        }

        // Initialize two variable for traversing the linked list
        ListNode currentA = headA, currentB = headB;

        // Traverse one list to the end
        while (currentA.next != null && currentB.next != null) {
            currentA = currentA.next;
            currentB = currentB.next;
        }

        // Make a skipA variabel for alinging the headA linked list
        ListNode skipA = headA;

        // Extinguish the headA linked list
        while (currentA.next != null) {
            skipA = skipA.next;
            currentA = currentA.next;
        }

        // Make a skipB variabel for alinging the headB linked list
        ListNode skipB = headB;

        // Extinguish the headB linked list
        while (currentB.next != null) {
            skipB = skipB.next;
            currentB = currentB.next;
        }

        // Check for the same node in the list
        while (skipA != skipB && skipA != null && skipB != null) {
            skipA = skipA.next;
            skipB = skipB.next;
        }

        // Return the skipA
        return skipA;
    }

    // Function to convert the lists into intersected ListNodes
    public static ListNode[] makelist(int[] listA, int[] listB, int intersection) {
        ListNode[] lists = { makelist(listA), makelist(listB) };
        ListNode headA = lists[0], headB = lists[1];

        if (headA == null || headB == null || intersection == -1) {
            return lists; // no intersection
        }

        // Find the intersection node in List A
        ListNode intersectNode = headA;
        while (intersectNode != null && intersectNode.val != intersection) {
            intersectNode = intersectNode.next;
        }

        if (intersectNode == null) {
            return lists; // intersection value not found in listA
        }

        // Find the last node in List B (or the node before intersection)
        ListNode currB = headB;
        while (currB.next != null) {
            if (currB.next.val == intersection) {
                break; // stop before intersection node
            }
            currB = currB.next;
        }

        // Connect List B to intersection node in List A
        currB.next = intersectNode;

        return lists;
    }

    // Function to convert an array into a ListNode
    public static ListNode makelist(int[] list) {
        if (list.length == 0) {
            return null;
        }
        ListNode head = new ListNode(list[0]);
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

        public ListNode(int val) {
            this.val = val;
        }
    }

    // Main method to test getIntersectionNode
    public static void main(String[] args) {
        int[] listA = { 4, 1, 8, 4, 5 };
        int[] listB = { 5, 6, 1, 8, 4, 5 };

        ListNode[] lists = makelist(listA, listB, 8);

        ListNode result = getIntersectionNode(lists[0], lists[1]);

        System.out.print("The intersection part of the linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
