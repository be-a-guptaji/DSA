/*
LeetCode Problem: https://leetcode.com/problems/min-cost-climbing-stairs/

Question: 746. Min Cost Climbing Stairs

Problem Statement: You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

Example 1:
Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.

Example 2:
Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.

Constraints:

2 <= cost.length <= 1000
0 <= cost[i] <= 999
*/

/*
Approach: Dynamic Programming (Bottom-Up)

To reach the top of the floor with minimum cost, we compute the minimum cost required
to reach the top starting from each step.

Observation:
- From step i, you can move to step i+1 or i+2.
- You can start from step 0 or step 1.

DP Definition:
- dp[i] = minimum cost to reach the top starting from step i.

Algorithm:
- Create a dp array of size (n + 2) to avoid bounds checking.
- Traverse the cost array from the last index down to 0.
- For each step i:
  dp[i] = cost[i] + min(dp[i+1], dp[i+2])
- The answer is min(dp[0], dp[1]) since you can start at either step.

Why It Works:
- Each subproblem is solved once.
- The optimal solution builds from smaller subproblems.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package DynamicProgramming.Easy;

public class _746_Min_Cost_Climbing_Stairs {
    // Method to find the minimum cost to reach the top of the floor
    public static int minCostClimbingStairs(int[] cost) {
        // Get the length of the cost array
        int length = cost.length;

        // Initialize the dp array which has one more then the lenght
        int[] dp = new int[length + 2];

        // Iterate over the cost array to get the minimum cost
        for (int i = length - 1; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
        }

        // Return the minimum of dp[0] or dp[1]
        return Math.min(dp[0], dp[1]);
    }

    // Main method to test minCostClimbingStairs
    public static void main(String[] args) {
        int[] cost = new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };

        int result = minCostClimbingStairs(cost);

        System.out.println("The minimum cost to reach the top of the floor is : " + result);
    }
}
