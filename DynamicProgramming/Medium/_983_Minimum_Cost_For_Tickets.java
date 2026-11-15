/*
LeetCode Problem: https://leetcode.com/problems/minimum-cost-for-tickets/

Question: 983. Minimum Cost For Tickets

Problem Statement: You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.

Example 1:
Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.

Example 2:
Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.

Constraints:
1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000
*/

/*
Approach: Top-down Memoized DP over Travel Day Index
Goal:
- Find the minimum cost to cover every travel day
  using 1-day, 7-day, and 30-day passes.
Core Idea:
- dfs(index) = minimum cost to cover days[index..n-1],
  given days before index are already covered.
- At each index, try each pass type: a pass of
  duration d bought on days[index] covers all days
  < days[index] + d, so the next uncovered day is
  the first j with days[j] >= days[index] + d.
- Pass durations are increasing, so one pointer j
  only moves forward across the three attempts.
- Memoize per index so each suffix is solved once.
Algorithm Steps:
1. If index == n, return 0.
2. Return dp[index] if already computed.
3. For each (duration, cost) in (1, c0), (7, c1),
   (30, c2):
   - Advance j while days[j] < days[index] + duration.
   - best = min(best, cost + dfs(j)).
4. Cache and return best.
Time Complexity:
- O(n) n states, each with 3 transitions and an amortized
O(n) pointer advance across all three passes bounded by O(1) per state on average.
Space Complexity:
- O(n) dp array plus recursion stack.
Result:
- Returns the minimum total cost to travel on all
  given days.
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the minimum number of dollars you need to travel every day in
  // the given list of days
  public int mincostTickets(int[] days, int[] costs) {
    // Initialize the dp array
    int[] dp = new int[days.length];

    // Fill the dp array by -1
    Arrays.fill(dp, -1);

    // Call the recusrive dfs method and return it
    return this.dfs(0, days, costs, dp);
  }

  // Helper method for the dfs
  private int dfs(int index, int[] days, int[] costs, int[] dp) {
    // If index is equal to the days.length then return 0
    if (index == days.length) {
      return 0;
    }

    // If dp[index] is not -1 then return dp[index]
    if (dp[index] != -1) {
      return dp[index];
    }

    // Initialize the dp[index] to Integer.MAX_VALUE
    dp[index] = Integer.MAX_VALUE;

    // Initialize the two variable i and j
    int i = 0, j = index;

    // Iterate over the passes
    for (int day : new int[] { 1, 7, 30 }) {
      // Increment the j
      while (j < days.length && days[j] < days[index] + day) {
        j++;
      }

      // Update the dp[index]
      dp[index] = Math.min(this.dfs(j, days, costs, dp) + costs[i], dp[index]);

      // Increment the i variable
      i++;
    }

    // Return the dp[index]
    return dp[index];
  }
}

public class _983_Minimum_Cost_For_Tickets {
  // Main method to test maxSumMinProduct
  public static void main(String[] args) {
    int[] days = new int[] { 1, 4, 6, 7, 8, 20 };
    int[] costs = new int[] { 2, 7, 15 };

    int result = new Solution().mincostTickets(days, costs);

    System.out
        .println("The minimum number of dollars you need to travel every day in the given list of days is : " + result);
  }
}