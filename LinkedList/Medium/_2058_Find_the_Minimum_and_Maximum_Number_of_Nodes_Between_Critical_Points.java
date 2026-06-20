/*
LeetCode Problem: https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/

Question: 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points

Problem Statement: A critical point in a linked list is defined as either a local maxima or a local minima.

A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.

A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.

Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.

Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points, return [-1, -1].

Example 1:
Input: head = [3,1]
Output: [-1,-1]
Explanation: There are no critical points in [3,1].

Example 2:
Input: head = [5,3,1,2,5,1,2]
Output: [1,3]
Explanation: There are three critical points:
- [5,3,1,2,5,1,2]: The third node is a local minima because 1 is less than 3 and 2.
- [5,3,1,2,5,1,2]: The fifth node is a local maxima because 5 is greater than 2 and 1.
- [5,3,1,2,5,1,2]: The sixth node is a local minima because 1 is less than 5 and 2.
The minimum distance is between the fifth and the sixth node. minDistance = 6 - 5 = 1.
The maximum distance is between the third and the sixth node. maxDistance = 6 - 3 = 3.

Example 3:
Input: head = [1,3,2,2,3,2,2,2,7]
Output: [3,3]
Explanation: There are two critical points:
- [1,3,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater than 1 and 2.
- [1,3,2,2,3,2,2,2,7]: The fifth node is a local maxima because 3 is greater than 2 and 2.
Both the minimum and maximum distances are between the second and the fifth node.
Thus, minDistance and maxDistance is 5 - 2 = 3.
Note that the last node is not considered a local maxima because it does not have a next node.

Constraints:
    The number of nodes in the list is in the range [2, 10^5].
    1 <= Node.val <= 10^5
*/

/*
Approach: Single Pass Traversal + Critical Point Tracking

Goal:
- Find:
    1. Minimum distance between any two distinct
       critical points.
    2. Maximum distance between any two distinct
       critical points.

- Return:
      [minDistance, maxDistance]

- If fewer than two critical points exist:
      [-1, -1]

Critical Point:
---------------
A node is critical if it is:

1. Local Maximum:
      prev < curr > next

OR

2. Local Minimum:
      prev > curr < next

Core Idea:
- Traverse the linked list using three pointers:
      previous
      current
      next

- Whenever a critical point is found:
   - Record its index.
   - Update the minimum distance using the
     previous critical point.
   - Update the maximum distance using the
     first critical point.

Tracked Variables:
------------------
firstIndex:
    Index of the first critical point.

currentIndex:
    Most recently discovered critical point.

minimumDistance:
    Minimum gap between consecutive critical
    points.

Algorithm Steps:
1. Traverse the list with a sliding window of
   three nodes.
2. Check whether current is:
      local minimum
      or
      local maximum.
3. For every critical point:
   - Store the first critical point index.
   - Compute distance from the previous
     critical point.
   - Update minimum distance.
   - Update latest critical point index.
4. After traversal:
   - If fewer than two critical points exist:
         return [-1, -1]
   - Otherwise:
         minDistance = smallest consecutive gap
         maxDistance = lastCritical - firstCritical

Why It Works:
- The minimum distance must occur between two
  consecutive critical points in sorted order.
- The maximum distance must occur between the
  first and last critical points.
- Therefore only the first and previous critical
  indices need to be tracked.

Example:
---------

List:
    5 -> 3 -> 1 -> 2 -> 5 -> 1 -> 2

Critical Points:
          1      5      1

Indices:
          2      4      5

Minimum Distance:
      min(4-2, 5-4)
    = min(2,1)
    = 1

Maximum Distance:
      5 - 2 = 3

Answer:
      [1,3]

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns:
      [minimum distance between any two critical points,
       maximum distance between first and last critical points]
*/

package LinkedList.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find an array of length 2 containing [minDistance, maxDistance]
  // where minDistance is the minimum distance between any two distinct critical
  // points and maxDistance is the maximum distance between any two distinct
  // critical points. If there are fewer than two critical points
  public int[] nodesBetweenCriticalPoints(ListNode head) {
    // Initialize the result variable
    int[] result = new int[] { -1, -1 };

    // If head.next.next is null then return result
    if (head.next.next == null) {
      return result;
    }

    // Initailize three pointer previous, current and next
    ListNode previous = head;
    ListNode current = head.next;
    ListNode next = head.next.next;

    // Initialize the first and last peak and first and second variable
    int firstIndex = -1;
    int lastIndex = -1;
    int currentIndex = -1;
    int previousIndex = -1;

    // Initialize the index variable
    int index = 0;

    // Initialize the minimum distance variable
    int minimumDistance = Integer.MAX_VALUE;

    // Iterate over the next untill list is extinguished
    while (next != null) {
      // Initialize the val1, val2 and val3
      int val1 = previous.val;
      int val2 = current.val;
      int val3 = next.val;

      // If we found a peak and a valley then update the pointers
      if ((val1 < val2 && val2 > val3) || (val1 > val2 && val2 < val3)) {
        if (firstIndex == -1) {
          firstIndex = index;
        } else {
          lastIndex = index;
        }

        if (currentIndex == -1) {
          currentIndex = index;
        } else {
          previousIndex = currentIndex;
          currentIndex = index;

          // Update the minimum distance variable
          minimumDistance = Math.min(minimumDistance, currentIndex - previousIndex);
        }
      }

      // Update the pointers
      previous = current;
      current = next;
      next = next.next;

      // Increment the index variable
      index++;
    }

    // If minimum distance variable is not equal to Integer.MAX_VALUE then update
    // the result variable
    if (minimumDistance != Integer.MAX_VALUE) {
      result[0] = minimumDistance;
      result[1] = lastIndex - firstIndex;
    }

    // Return the result
    return result;
  }
}

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

// Mock class for makeing the ListNode Class
class ListNode {
  public int val;
  public ListNode next;

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  // Helper method to make the linked list from the array
  public ListNode makelist(int[] list) {
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
}

// Main Class
public class _2058_Find_the_Minimum_and_Maximum_Number_of_Nodes_Between_Critical_Points {
  // Main method to test nodesBetweenCriticalPoints
  public static void main(String[] args) {
    ListNode head = new ListNode().makelist(new int[] { 5, 3, 1, 2, 5, 1, 2 });

    int[] result = new Solution().nodesBetweenCriticalPoints(head);

    System.out.println(
        "An array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points is : "
            + Arrays.toString(result));
  }
}
