/*
LeetCode Problem: https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/

Question: 1760. Minimum Limit of Balls in a Bag

Problem Statement: You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.

You can perform the following operation at most maxOperations times:

Take any bag of balls and divide it into two new bags with a positive number of balls.
For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.

Return the minimum possible penalty after performing the operations.

Example 1:
Input: nums = [9], maxOperations = 2
Output: 3
Explanation: 
- Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
- Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.

Example 2:
Input: nums = [2,4,8,2], maxOperations = 4
Output: 2
Explanation:
- Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
The bag with the most number of balls has 2 balls, so your penalty is 2, and you should return 2.

Constraints:

1 <= nums.length <= 10^5
1 <= maxOperations, nums[i] <= 10^9
*/

/*
Approach: Binary Search on Answer (Greedy Operation Counting)

Goal:
- Minimize the maximum number of balls in any bag
  after performing at most maxOperations splits.

Core Idea:
- The answer (maximum balls per bag) lies between:
    1 → smallest possible size
    max(nums) → largest initial bag
- Use binary search to find the smallest feasible penalty.
- For a given x, compute required operations:
    operations += (num - 1) / x
- If total operations ≤ maxOperations → x is feasible.

Algorithm Steps:
1. Initialize bounds:
   - left = 1
   - right = max(nums)
2. Perform binary search:
   - mid = candidate maximum size.
3. Check feasibility:
   - For each num:
       operations += (num - 1) / mid
   - If operations ≤ maxOperations → valid.
4. If valid:
   - Search smaller values (right = mid).
5. Else:
   - Increase lower bound (left = mid + 1).
6. Return the minimum feasible value.

Time Complexity:
- O(n log M)
  n = number of bags
  M = max(nums)

Space Complexity:
- O(1)

Result:
- Returns the minimum possible penalty (maximum balls in any bag).
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
    // Method to find the minimum possible penalty after performing the operations
    public int minimumSize(int[] nums, int maxOperations) {
        // Initialize the left and right bound
        int left = 1, right = 0;

        // Get the right bound
        for (int num : nums) {
            right = Math.max(right, num);
        }

        // Initialize the minimumPenalty variable
        int minimumPenalty = right;

        // Iterate over the bound
        while (left < right) {
            // Find the mid value
            int mid = left + ((right - left) >> 1);

            // Update the bound and minimumX accordingly
            if (this.canDivide(nums, maxOperations, mid)) {
                right = mid;
                minimumPenalty = mid;
            } else {
                left = mid + 1;
            }
        }

        // Return minimumPenalty
        return minimumPenalty;
    }

    // Helper method to find if x is valid or not
    private boolean canDivide(int[] nums, int maxOperations, int x) {
        // Iterate over the nums for finding the minimum of x
        for (int num : nums) {
            // Update the maxOperation
            maxOperations -= (num - 1) / x;

            // If maxOperation is negative then return false
            if (maxOperations < 0) {
                return false;
            }
        }

        // Return the true in the end
        return true;
    }
}

public class _1760_Minimum_Limit_of_Balls_in_a_Bag {
    // Main method to test minimumSize
    public static void main(String[] args) {
        int[] nums = new int[] { 9 };
        int maxOperations = 2;

        int result = new Solution().minimumSize(nums, maxOperations);

        System.out.println("The minimum possible penalty after performing the operations is : " + result);
    }
}