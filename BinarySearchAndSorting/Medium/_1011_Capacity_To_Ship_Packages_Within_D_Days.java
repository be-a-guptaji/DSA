/*
LeetCode Problem: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

Question: 1011. Capacity To Ship Packages Within D Days

Problem Statement: A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

Example 1:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10
Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:
Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:
Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1

Constraints:

1 <= days <= weights.length <= 5 * 10^4
1 <= weights[i] <= 500
 */

/*
Approach: Binary Search on Answer (Capacity Feasibility Check)

Goal:
- Determine the minimum ship capacity required to ship all packages
  within the given number of days while maintaining order.

Core Idea:
- The capacity must lie between:
  - max(weights)  → minimum feasible capacity.
  - sum(weights)  → capacity if shipped in one day.
- Use binary search to find the smallest capacity that satisfies
  the shipping constraint.
- A helper function checks whether a given capacity can ship all
  packages within the allowed number of days.

Algorithm Steps:
1. Compute bounds:
   - left  = maximum weight in the array.
   - right = sum of all weights.
2. Perform binary search on capacity:
   - mid represents a candidate ship capacity.
3. Use helper function:
   - Simulate loading packages sequentially.
   - If capacity exceeded, increment ship count (new day).
4. If packages can be shipped within days:
   - Try smaller capacity (move right).
5. Otherwise:
   - Increase capacity (move left).
6. Track the minimum valid capacity.

Time Complexity:
- O(n log S)
  n = number of packages
  S = sum of weights (search range)

Space Complexity:
- O(1)

Result:
- Returns the minimum ship capacity required to ship all packages
  within the specified number of days.
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
  // Method to find the least weight capacity of the ship that will result in all
  // the packages on the conveyor belt being shipped within days days
  public int shipWithinDays(int[] weights, int days) {
    // Initialize the left and the right bound
    int left = 0, right = 0;

    // Iterate over the weights to find the left and right bound
    for (int weight : weights) {
      // Update the left value to the maximum weight of the weights array
      left = Math.max(left, weight);

      // Update the right value to the sum of weight in the array
      right += weight;
    }

    // Initialize the minimum weigth variable to the Integer.MAX_VALUE
    int minimumWeigth = Integer.MAX_VALUE;

    // Iterate over the bound to find the minimum weight to carry
    while (left <= right) {
      // Intialize the mid value
      int mid = (left + right) >> 1;

      // If we can fit all the weights in the days then update the result value
      if (this.canFitAllWeights(weights, days, mid)) {
        // Update the right variable
        right = mid - 1;

        // Update the minimum weigth variable
        minimumWeigth = Math.min(minimumWeigth, mid);
      } else {
        // Update the left variable
        left = mid + 1;
      }
    }

    // Return the minimum weigth
    return minimumWeigth;
  }

  // Helper method to determine if we can fit all the weights in days
  private boolean canFitAllWeights(int[] weights, int days, int cap) {
    // Initialize the ships and current cap
    int ships = 0, currentCap = cap;

    // Iterate over the weights to find out if we can fit all weights in the given
    // days
    for (int w : weights) {
      // Update the intermediate weights and days
      if (currentCap - w < 0) {
        ships++;

        if (ships == days) {
          return false;
        }

        currentCap = cap;
      }

      currentCap -= w;
    }

    // Return true in the end
    return true;
  }
}

public class _1011_Capacity_To_Ship_Packages_Within_D_Days {
  // Main method to test shipWithinDays
  public static void main(String[] args) {
    int[] weights = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int days = 5;

    int result = new Solution().shipWithinDays(weights, days);

    System.out.println(
        "The least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within "
            + days + " days is : " + result);
  }
}
