/*
LeetCode Problem: https://leetcode.com/problems/find-pivot-index/

Question: 724. Find Pivot Index

Problem Statement: Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.

Example 1:
Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11

Example 2:
Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.

Example 3:
Input: nums = [2,1,-1]
Output: 0
Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0

Constraints:

1 <= nums.length <= 10^4
-1000 <= nums[i] <= 1000

Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/
 */

/*
Approach: Prefix Sum Comparison

Goal:
Find the pivot index where the sum of elements to the left equals
the sum of elements to the right.

Key Idea:
Maintain a running left sum and compute the right sum using the total sum.

Algorithm:
1. Compute totalSum of all elements.
2. Initialize leftSum = 0.
3. Traverse the array:
   - rightSum = totalSum − leftSum − nums[i]
   - If leftSum == rightSum → return i.
   - Update leftSum += nums[i].
4. If no pivot index is found, return −1.

Why It Works:
- Total sum allows constant-time computation of right sum.
- Single traversal efficiently checks all indices.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _724_Find_Pivot_Index {
    // Method to find the calculate the pivot index of this array
    public static int pivotIndex(int[] nums) {
        // Intialize the total sum of array and left sum
        int totalSum = 0, leftSum = 0;

        // Get the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }

        // Iterate over the nums array for finding the pivot
        for (int i = 0; i < nums.length; i++) {
            // Get the right sum
            int rightSum = totalSum - leftSum - nums[i];

            // If left sum and right sum are equal then return the index
            if (leftSum == rightSum) {
                return i;
            }

            // Update the left sum
            leftSum += nums[i];
        }

        // Return negative one if no piviot is found
        return -1;
    }

    // Main method to test pivotIndex
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 7, 3, 6, 5, 6 };

        int result = pivotIndex(nums);

        System.out.println("The calculate the pivot index of the array is : " + result);
    }
}
