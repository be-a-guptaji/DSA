/*
LeetCode Problem: https://leetcode.com/problems/ipo/

Question: 502. IPO

Problem Statement: Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.

Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.

The answer is guaranteed to fit in a 32-bit signed integer.

Example 1:
Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.

Example 2:
Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6

Constraints:
    1 <= k <= 10^5
    0 <= w <= 10^9
    n == profits.length
    n == capital.length
    1 <= n <= 10^5
    0 <= profits[i] <= 10^4
    0 <= capital[i] <= 10^9
*/

/*
Approach: Dual Heap Greedy Capital Accumulation
Goal:
- Select at most k projects to maximize final
  capital w, where each project can only be started
  if current capital >= its required capital, and
  completing it adds its profit to current capital.
Core Idea:
- At each step, the optimal greedy choice is to
  pick the highest-profit project among all
  currently affordable ones (capital[i] <= w).
- Use a min-heap ordered by capital to efficiently
  find all newly affordable projects as w grows,
  and a max-heap ordered by profit to always
  select the best available project.
Algorithm Steps:
1. Push all project indices into minCapital,
   ordered by capital[i] ascending.
2. Repeat up to k times:
   a. Move all projects with capital[i] <= w from
      minCapital into maxProfit (they are now
      affordable).
   b. If maxProfit is empty (no affordable project
      exists), stop early.
   c. Poll the highest-profit project from
      maxProfit and add its profit to w.
3. Return w.
Why It Works:
- Greedy selection of the maximum available profit
  at each step is optimal: since profits are
  additive and capital only increases, choosing the
  highest profit now maximizes the capital available
  to unlock future projects.
- The min-heap on capital ensures each project is
  considered for unlocking exactly once (when w
  first reaches its required capital), avoiding
  repeated full scans.
- Projects transferred to maxProfit remain there
  across iterations since w is non-decreasing and
  they stay affordable.
Time Complexity:
- O((n + k) log n)
where n is the number of projects. Each project is
pushed and popped from both heaps at most once,
costing O(log n) each, and k heap polls from
maxProfit cost O(log n) each.
Space Complexity:
- O(n)
for both heaps storing all project indices.
Result:
- Returns the maximum capital achievable after
  completing at most k projects.
*/

package StacksQueuesAndHeaps.Hard;

import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the final maximized capital
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    // Initialize the min ahd max heap
    PriorityQueue<Integer> minCapital = new PriorityQueue<>((x, y) -> Integer.compare(capital[x], capital[y]));
    PriorityQueue<Integer> maxProfit = new PriorityQueue<>((x, y) -> Integer.compare(profits[y], profits[x]));

    // Fill the minCapital
    for (int i = 0; i < capital.length; i++) {
      minCapital.offer(i);
    }

    // Iterate over the k
    for (int i = 0; i < k; i++) {
      // Iterete over the minCapital to get the maxProfit
      while (!minCapital.isEmpty() && capital[minCapital.peek()] <= w) {
        maxProfit.offer(minCapital.poll());
      }

      // If maxProfit is empty then break out of the loop
      if (maxProfit.isEmpty()) {
        break;
      }

      // Update the profit
      w += profits[maxProfit.poll()];
    }

    // Return the profit
    return w;
  }
}

public class _502_IPO {
  // Main method to test findMaximizedCapital
  public static void main(String[] args) {
    int k = 2;
    int w = 0;
    int[] profits = new int[] { 1, 2, 3 };
    int[] capital = new int[] { 0, 1, 1 };

    int result = new Solution().findMaximizedCapital(k, w, profits, capital);

    System.out.println("The final maximized capital is : " + result);
  }
}
