/*
LeetCode Problem: https://leetcode.com/problems/minimum-cost-to-merge-stones/

Question: 1000. Minimum Cost to Merge Stones

Problem Statement: There are n piles of stones arranged in a row. The ith pile has stones[i] stones.

A move consists of merging exactly k consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these k piles.

Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

Example 1:
Input: stones = [3,2,4,1], k = 2
Output: 20
Explanation: We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.

Example 2:
Input: stones = [3,2,4,1], k = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.

Example 3:
Input: stones = [3,5,1,2,6], k = 3
Output: 25
Explanation: We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.

Constraints:

n == stones.length
1 <= n <= 30
1 <= stones[i] <= 100
2 <= k <= 30
 */

/*
Approach: Dynamic Programming (Bottom-Up)

Key Idea:
- We are given an array of stone piles and can merge exactly k consecutive piles at a time.
- When k piles are merged, the cost is equal to the total number of stones in those k piles,
  and they become a single pile. The goal is to find the minimum total cost to merge all piles into one.

Main Observations:
1. It’s impossible to end up with one pile unless (n - 1) is divisible by (k - 1),
   since each merge operation reduces the number of piles by (k - 1).
   → Hence, if ((n - 1) % (k - 1)) != 0, return -1.

2. Define dp[i][j] = the minimum cost to merge piles from index i to j into as few piles as possible.
   - If we can merge the entire range [i, j] into one pile, we’ll add the total sum of stones in that range.

3. Transition:
   - To compute dp[i][j], we try all valid partitions of [i, j] into two subranges [i, mid] and [mid + 1, j],
     but we only split at intervals of (k - 1) because merging is only possible in those chunk sizes.
   - Recurrence:
        dp[i][j] = min(dp[i][mid] + dp[mid + 1][j]) for all mid in [i, j)
     After merging into one pile:
        if ((length of range - 1) % (k - 1) == 0)
            dp[i][j] += total sum of stones in range [i, j].

4. Use prefix sums to efficiently compute the total stones in a range.

Steps:
1. Compute prefix sums to quickly get the total stones in any subarray.
2. Initialize dp[i][i] = 0 (merging one pile costs nothing).
3. For increasing lengths of subarrays:
     - Compute dp[i][j] using all valid splits.
     - Add total cost if the current subarray can be merged into one pile.
4. The final answer is dp[0][n - 1].

Time Complexity: O(n^3 / k)
Space Complexity: O(n^2)
*/

package DynamicProgramming.Hard;

import java.util.Arrays;

public class _1000_Minimum_Cost_to_Merge_Stones {
    // Method to find the minimum cost to merge all piles of stones into one pile
    public static int mergeStones(int[] stones, int k) {
        // Get the size of the stones
        int size = stones.length;

        // If we can not merge k stones at a time then return -1
        if (((size - 1) % (k - 1)) != 0) {
            return -1;
        }

        // Initialize the prefix sum
        int[] sums = new int[size + 1];

        // Fill the prefix sum
        // sums[i + 1] stores the total number of stones from 0 to i
        for (int i = 0; i < size; i++) {
            sums[i + 1] = sums[i] + stones[i];
        }

        // Initialize the dp matrix
        int[][] dp = new int[size][size];

        // Fill the array with the Integer.MAX_VALUE and make the diagonal to 0
        // dp[i][j] represents the minimum cost to merge stones from i to j
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0; // Cost to merge one pile is 0
        }

        // Iterate over the length of the current subarray (l = length of the range)
        for (int l = 2; l <= size; l++) {
            // Iterate over all starting points j
            for (int j = 0; j + l <= size; j++) {
                int end = j + l - 1; // Ending index of the subarray

                // Try to divide the current range into two parts
                // We only split at steps of (k - 1) since only such divisions make sense
                for (int mid = j; mid < end; mid += k - 1) {
                    if (dp[j][mid] == Integer.MAX_VALUE || dp[mid + 1][end] == Integer.MAX_VALUE)
                        continue;
                    dp[j][end] = Math.min(dp[j][end], dp[j][mid] + dp[mid + 1][end]);
                }

                // If the current range can be fully merged into one pile,
                // then add the sum of stones in this range
                if ((l - 1) % (k - 1) == 0) {
                    dp[j][end] += sums[end + 1] - sums[j];
                }
            }
        }

        // Return the minimum cost to merge all stones into one pile
        return dp[0][size - 1];
    }

    // Main method to test mergeStones
    public static void main(String[] args) {
        int[] stones = { 3, 2, 4, 1 };
        int k = 2;

        int result = mergeStones(stones, k);

        System.out.println("The minimum cost to merge all piles of stones into one pile is : " + result);
    }
}
