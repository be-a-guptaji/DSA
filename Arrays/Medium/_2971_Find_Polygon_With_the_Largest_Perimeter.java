/*
LeetCode Problem: https://leetcode.com/problems/find-polygon-with-the-largest-perimeter/

Question: 2971. Find Polygon With the Largest Perimeter

Problem Statement: You are given an array of positive integers nums of length n.

A polygon is a closed plane figure that has at least 3 sides. The longest side of a polygon is smaller than the sum of its other sides.

Conversely, if you have k (k >= 3) positive real numbers a1, a2, a3, ..., ak where a1 <= a2 <= a3 <= ... <= ak and a1 + a2 + a3 + ... + ak-1 > ak, then there always exists a polygon with k sides whose lengths are a1, a2, a3, ..., ak.

The perimeter of a polygon is the sum of lengths of its sides.

Return the largest possible perimeter of a polygon whose sides can be formed from nums, or -1 if it is not possible to create a polygon.

Example 1:
Input: nums = [5,5,5]
Output: 15
Explanation: The only possible polygon that can be made from nums has 3 sides: 5, 5, and 5. The perimeter is 5 + 5 + 5 = 15.

Example 2:
Input: nums = [1,12,1,2,5,50,3]
Output: 12
Explanation: The polygon with the largest perimeter which can be made from nums has 5 sides: 1, 1, 2, 3, and 5. The perimeter is 1 + 1 + 2 + 3 + 5 = 12.
We cannot have a polygon with either 12 or 50 as the longest side because it is not possible to include 2 or more smaller sides that have a greater sum than either of them.
It can be shown that the largest possible perimeter is 12.

Example 3:
Input: nums = [5,5,50]
Output: -1
Explanation: There is no possible way to form a polygon from nums, as a polygon has at least 3 sides and 50 > 5 + 5.

Constraints:
3 <= n <= 10^5
1 <= nums[i] <= 10^9
*/

/*
Approach: Sorting + Greedy Prefix Sum

Goal:
- Find the largest possible perimeter of a polygon
  that can be formed using elements of nums.

Core Idea:
- For a valid polygon:
    sum of all smaller sides > largest side
- After sorting:
   - Maintain running sum of previous sides.
   - If:
       total > nums[i]
     then a valid polygon can be formed using
     all sides up to i.
- Update maximum perimeter greedily.

Algorithm Steps:
1. Sort nums array.
2. Initialize:
   - total = 0
   - result = -1
3. Traverse nums:
   - If total > nums[i]:
       result = total + nums[i]
   - Add nums[i] to total.
4. Return result.

Time Complexity:
- O(n log n)
  due to sorting

Space Complexity:
- O(1)

Result:
- Returns the maximum polygon perimeter,
  or -1 if no valid polygon can be formed.
*/

package Arrays.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the largest possible perimeter of a polygon whose sides can be
    // formed from nums
    public long largestPerimeter(int[] nums) {
        // Sort the array
        Arrays.sort(nums);

        // Initialize the result and total varaible
        long result = -1;
        long total = 0;

        // Iterate over the nums array
        for (int i = 0; i < nums.length; i++) {
            // Update the result if total is greater then the nums[i] 
            if (total > nums[i]) {
                result = total + nums[i];
            }

            // Update the total variable
            total += nums[i];
        }

        // Return the result variable
        return result;
    }
}

public class _2971_Find_Polygon_With_the_Largest_Perimeter {
    // Main method to test largestPerimeter
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 12, 1, 2, 5, 50, 3 };

        long result = new Solution().largestPerimeter(nums);

        System.out.println(
                "The largest possible perimeter of a polygon whose sides can be formed from nums is : " + result);
    }
}
