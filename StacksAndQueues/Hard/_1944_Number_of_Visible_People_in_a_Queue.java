/*
LeetCode Problem: https://leetcode.com/problems/number-of-visible-people-in-a-queue/

Question: 1944. Number of Visible People in a Queue

Problem Statement: There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. You are given an array heights of distinct integers where heights[i] represents the height of the ith person.

A person can see another person to their right in the queue if everybody in between is shorter than both of them. More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).

Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.

Example 1:
Input: heights = [10,6,8,5,11,9]
Output: [3,1,2,1,1,0]
Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.

Example 2:
Input: heights = [5,1,2,3,10]
Output: [4,1,1,1,0]

Constraints:

n == heights.length
1 <= n <= 10^5
1 <= heights[i] <= 10^5
All the values of heights are unique.
 */

/*
Approach: Monotonic Decreasing Stack

Goal:
- For each person, determine how many people to their right
  they can see in the queue.

Core Idea:
- Traverse from right to left.
- Maintain a monotonic decreasing stack of heights.
- A person can see:
    1. All shorter people popped from the stack.
    2. The first taller person that blocks the view.

Algorithm Steps:
1. Traverse heights from right to left.
2. While stack is not empty and top < current height:
   - Pop stack and increment visible count.
3. If stack still has elements:
   - Current person can see the first taller person.
4. Push current height into stack.
5. Store counts in result array.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns an array where result[i] represents
  the number of people person i can see to their right.
*/

package StacksAndQueues.Hard;

import java.util.Arrays;

public class _1944_Number_of_Visible_People_in_a_Queue {
    // Method to find the number of people the ith person can see to their right in
    // the queue
    public static int[] canSeePersonsCount(int[] heights) {
        // Get the length of the heights array
        int length = heights.length;

        // Initialize the stack for the heights
        int[] stack = new int[length];

        // Initialize the stack pointer
        int stackPointer = 0;

        // Initialize the result array for returning
        int[] result = new int[length];

        // Iterate over the heights right to left
        for (int i = length - 1; i > -1; i--) {
            // Pop from the stack if top element is less then the current height
            while (stackPointer > 0 && stack[stackPointer - 1] < heights[i]) {
                stackPointer--;
                result[i]++;
            }

            if (stackPointer > 0) {
                result[i]++;
            }

            stack[stackPointer++] = heights[i];
        }

        // Return result array
        return result;
    }

    // Main method to test canSeePersonsCount
    public static void main(String[] args) {
        int[] heights = new int[] { 10, 6, 8, 5, 11, 9 };

        int[] result = canSeePersonsCount(heights);

        System.out.println("The number of people the ith person can see to their right in the queue is : "
                + Arrays.toString(result));
    }
}
