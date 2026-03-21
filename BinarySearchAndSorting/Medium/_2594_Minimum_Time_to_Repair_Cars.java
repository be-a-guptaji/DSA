/*
LeetCode Problem: https://leetcode.com/problems/minimum-time-to-repair-cars/

Question: 2594. Minimum Time to Repair Cars

Problem Statement: You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.

You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.

Return the minimum time taken to repair all the cars.

Note: All the mechanics can repair the cars simultaneously.

Example 1:
Input: ranks = [4,2,3,1], cars = 10
Output: 16
Explanation: 
- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​

Example 2:
Input: ranks = [5,1,8], cars = 6
Output: 16
Explanation: 
- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​

Constraints:

1 <= ranks.length <= 10^5
1 <= ranks[i] <= 100
1 <= cars <= 10^6
*/

/*
Approach: Binary Search on Answer (Work Rate Feasibility)

Goal:
- Find the minimum time required to repair all cars
  given mechanics with different repair rates.

Core Idea:
- A mechanic with rank r can repair:
    ⌊sqrt(time / r)⌋ cars in given time.
- Total repaired cars = sum across all mechanics.
- Use binary search to find the minimum time such that
  total repaired cars ≥ required cars.

Algorithm Steps:
1. Initialize bounds:
   - left = 1
   - right = minRank * cars^2 (upper bound worst case).
2. Perform binary search:
   - mid = candidate time.
3. Check feasibility:
   - For each mechanic:
       repaired += floor(sqrt(mid / rank))
   - If repaired ≥ cars → valid.
4. If valid:
   - Update result and search smaller time.
5. Else:
   - Increase time.
6. Return minimum valid time.

Time Complexity:
- O(m log T)
  m = number of mechanics
  T = search space of time

Space Complexity:
- O(1)

Result:
- Returns the minimum time required to repair all cars.
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
    // Method to find the minimum time taken to repair all the cars
    public long repairCars(int[] ranks, int cars) {
        // Initialize the left and right bound
        long left = 1, right = (long) ranks[0] * cars * cars;

        // Initialize the minimumTime variable
        long minimumTime = right;

        // Iterate over the bound
        while (left <= right) {
            // Find the mid value
            long mid = left + ((right - left) >> 1);

            // Update the bound and minimumTime accordingly
            if (this.canRepair(ranks, cars, mid)) {
                right = mid - 1;
                minimumTime = Math.min(minimumTime, mid);
            } else {
                left = mid + 1;
            }
        }

        // Return minimumTime
        return minimumTime;
    }

    // Helper method to find if x is valid or not
    private boolean canRepair(int[] ranks, int cars, long time) {
        // Iterate over the nums for finding the minimum of time
        for (int rank : ranks) {
            // Update the maxOperation
            cars -= (int) Math.sqrt(time / rank);

            // If cars is negative then return true
            if (cars <= 0) {
                return true;
            }
        }

        // Return the false in the end
        return false;
    }
}

public class _2594_Minimum_Time_to_Repair_Cars {
    // Main method to test repairCars
    public static void main(String[] args) {
        int[] ranks = new int[] { 4, 2, 3, 1 };
        int cars = 10;

        long result = new Solution().repairCars(ranks, cars);

        System.out.println("The minimum time taken to repair all the cars is : " + result);
    }
}