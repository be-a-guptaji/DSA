/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/

Question: 1769. Minimum Number of Operations to Move All Balls to Each Box

Problem Statement: You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.

Example 1:
Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.

Example 2:
Input: boxes = "001011"
Output: [11,8,5,4,3,4]

Constraints:
n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.
 */

/*
Approach: Prefix Sum (Two-Pass Accumulation)

Goal:
- Compute minimum operations required to move all balls
  to each box.

Core Idea:
- Instead of calculating distances for each index independently (O(n^2)),
  accumulate contributions using two passes:
    1. Left → Right
    2. Right → Left
- Track:
    balls → number of balls seen so far
    moves → total moves required to bring those balls to current index

Algorithm Steps:
1. Initialize result array.
2. Left to Right pass:
   - result[i] = moves
   - moves += balls
   - balls += (boxes[i] == '1')
3. Reset balls and moves.
4. Right to Left pass:
   - result[i] += moves
   - moves += balls
   - balls += (boxes[i] == '1')
5. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(1) (excluding output array)

Result:
- Returns array where result[i] is minimum operations
  to move all balls to box i.
*/

package Arrays.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the minimum number of operations needed to move all the balls
    // to the ith box
    public int[] minOperations(String boxes) {
        // Initialize the lenght array
        int length = boxes.length();

        // Initialize the result array
        int[] result = new int[length];

        // Initialzie the balls and moves variable
        int balls = 0, moves = 0;

        // Update the result array
        for (int i = 0; i < length; i++) {
            // Update the result variable
            result[i] = balls + moves;

            // Update the moves
            moves += balls;
            balls += boxes.charAt(i) - '0';
        }

        // Reset the balls and moves
        balls = moves = 0;

        // Update the result array
        for (int i = length - 1; i >= 0; i--) {
            // Update the result variable
            result[i] += balls + moves;

            // Update the moves
            moves += balls;
            balls += boxes.charAt(i) - '0';
        }

        // Return the result array
        return result;
    }
}

public class _1769_Minimum_Number_of_Operations_to_Move_All_Balls_to_Each_Box {
    // Main method to test minOperations
    public static void main(String[] args) {
        String boxes = "110";

        int[] result = new Solution().minOperations(boxes);

        System.out.println("The minimum number of operations needed to move all the balls to the ith box is : "
                + Arrays.toString(result));
    }
}
