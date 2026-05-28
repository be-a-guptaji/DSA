/*
LeetCode Problem: https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/

Question: 2379. Minimum Recolors to Get K Consecutive Black Blocks

Problem Statement: You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', representing the color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.

You are also given an integer k, which is the desired number of consecutive black blocks.

In one operation, you can recolor a white block such that it becomes a black block.

Return the minimum number of operations needed such that there is at least one occurrence of k consecutive black blocks.

Example 1:
Input: blocks = "WBBWWBBWBW", k = 7
Output: 3
Explanation:
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
so that blocks = "BBBBBBBWBW". 
It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
Therefore, we return 3.

Example 2:
Input: blocks = "WBWBBBW", k = 2
Output: 0
Explanation:
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.

Constraints:
n == blocks.length
1 <= n <= 100
blocks[i] is either 'W' or 'B'.
1 <= k <= n
*/

/*
Approach: Fixed-Size Sliding Window

Goal:
- Find the minimum number of recolors needed
  to obtain at least one substring of length k
  consisting entirely of black blocks ('B').

Core Idea:
- In any window of size k:
   - White blocks ('W') must be recolored.
- Therefore:
   - Minimum recolors =
       minimum number of 'W' characters
       across all windows of size k.

Algorithm Steps:
1. Count white blocks in the first window of size k.
2. Initialize result with this count.
3. Slide the window:
   - Add incoming character contribution.
   - Remove outgoing character contribution.
4. Update minimum white count.
5. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns minimum recolors required.
*/

package TwoPointersAndSlidingWindow.Easy;

// Solution Class
class Solution {
    // Method to find the minimum number of operations needed such that there is at
    // least one occurrence of k consecutive black blocks
    public int minimumRecolors(String blocks, int k) {
        // Initialize the numberOfWhiteBlocks
        int numberOfWhiteBlocks = 0;

        // Get the length of the blocks string
        int length = blocks.length();

        // Initialize the result variable
        int result = length;

        // Iterate over the window k
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                numberOfWhiteBlocks++;
            }
        }

        // Update the result variable
        result = Math.min(result, numberOfWhiteBlocks);

        // Iterate over the blocks
        for (int i = k; i < length; i++) {
            if (blocks.charAt(i) == 'W') {
                numberOfWhiteBlocks++;
            }

            if (blocks.charAt(i - k) == 'W') {
                numberOfWhiteBlocks--;
            }

            // Update the result variable
            result = Math.min(result, numberOfWhiteBlocks);
        }

        // Return the result
        return result;
    }
}

public class _2379_Minimum_Recolors_to_Get_K_Consecutive_Black_Blocks {
    // Main method to test the minimumRecolors
    public static void main(String[] args) {
        String blocks = "WBBWWBBWBW";
        int k = 3;

        int result = new Solution().minimumRecolors(blocks, k);

        System.out.println("The minimum number of operations needed such that there is at least one occurrence of " + k
                + " consecutive black blocks is : " + result);
    }
}
