/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/

Question: 1553. Minimum Number of Days to Eat N Oranges

Problem Statement: There are n oranges in the kitchen and you decided to eat some of these oranges every day as follows:

Eat one orange.
If the number of remaining oranges n is divisible by 2 then you can eat n / 2 oranges.
If the number of remaining oranges n is divisible by 3 then you can eat 2 * (n / 3) oranges.
You can only choose one of the actions per day.

Given the integer n, return the minimum number of days to eat n oranges.

Example 1:
Input: n = 10
Output: 4
Explanation: You have 10 oranges.
Day 1: Eat 1 orange,  10 - 1 = 9.  
Day 2: Eat 6 oranges, 9 - 2*(9/3) = 9 - 6 = 3. (Since 9 is divisible by 3)
Day 3: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. 
Day 4: Eat the last orange  1 - 1  = 0.
You need at least 4 days to eat the 10 oranges.

Example 2:
Input: n = 6
Output: 3
Explanation: You have 6 oranges.
Day 1: Eat 3 oranges, 6 - 6/2 = 6 - 3 = 3. (Since 6 is divisible by 2).
Day 2: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. (Since 3 is divisible by 3)
Day 3: Eat the last orange  1 - 1  = 0.
You need at least 3 days to eat the 6 oranges.

Constraints:
1 <= n <= 2 * 10^9
*/

/*
Approach: Top-down Memoized Recursion with Optimal Step Selection
Goal:
- Find the minimum days to eat all n oranges,
  where each day you can eat 1 orange, half the
  remaining (if divisible by 2), or a third of the
  remaining (if divisible by 3).
Core Idea:
- To reach a state where n is divisible by 2 or 3,
  eat the remainder oranges one by one first (n%2
  or n%3 steps), then apply the halving or
  thirding operation.
- The recurrence is:
  dp[n] = 1 + min(n%2 + dp[n/2], n%3 + dp[n/3])
  where n%2 and n%3 are the 1-by-1 steps needed
  before each bulk operation.
- Memoize results in a static HashMap to avoid
  recomputing overlapping subproblems across calls.
Algorithm Steps:
1. Base case: n <= 1 returns n (0 days for 0, 1
   day for 1).
2. Return cached result if dp contains n.
3. Compute result = 1 + min(n%2 + dfs(n/2),
   n%3 + dfs(n/3)).
4. Cache and return result.
Why It Works:
- The bulk operations (n/2, n/3) reduce n
  dramatically, so the recursion tree is O(log^2 n)
  deep rather than O(n).
- Adding n%2 or n%3 steps before each division
  correctly accounts for the prerequisite 1-by-1
  eating to reach divisibility.
- The static HashMap persists across test cases,
  amortizing cost over repeated calls.
Time Complexity:
- O(log^2 n) unique states due to the rapid
  reduction via integer division.
Space Complexity:
- O(log^2 n) for the memoization map and call
  stack.
Result:
- Returns the minimum number of days to eat all
  n oranges.
*/

package Graphs.Hard;

import java.util.HashMap;

// Solution Class
class Solution {
  // Initialize the hashmap for the dp
  private static final HashMap<Integer, Integer> dp = new HashMap<>();

  // Helper method for the dfs
  private int dfs(int n) {
    // If n is less then 2 then return n
    if (n <= 1) {
      return n;
    }

    // If dp has value n then return that value
    if (dp.containsKey(n)) {
      return dp.get(n);
    }

    // Initialize the result variable and call recursive dfs call
    int result = 1 + Math.min(n % 2 + this.dfs(n / 2), n % 3 + this.dfs(n / 3));

    // Update the dp map
    dp.put(n, result);

    // Return the result variable
    return result;
  }

  // Method to find the minimum number of days to eat n oranges
  public int minDays(int n) {
    // Call the recursive dfs method
    return this.dfs(n);
  }
}

public class _1553_Minimum_Number_of_Days_to_Eat_N_Oranges {
  // Main method to test minDays
  public static void main(String[] args) {
    int n = 10;

    int result = new Solution().minDays(n);

    System.out.println("The minimum number of days to eat " + n + " oranges is : " + result);
  }
}
