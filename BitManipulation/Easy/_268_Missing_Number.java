/*
LeetCode Problem: https://leetcode.com/problems/missing-number/

Question: 268. Missing Number

Problem Statement: Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example 1:
Input: nums = [3,0,1]
Output: 2
Explanation:
n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.

Example 2:
Input: nums = [0,1]
Output: 2
Explanation:
n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.

Example 3:
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation:
n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.

Constraints:

n == nums.length
1 <= n <= 10^4
0 <= nums[i] <= n
All the numbers of nums are unique.

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
*/

/*
Approach: Mathematical Sum Formula

Goal:
Given an array containing n distinct numbers from the range [0, n],
find the single missing number.

Key Insight:
The sum of numbers from 0 to n is known:
- totalSum = n × (n + 1) / 2

Algorithm:
1. Compute the expected sum of numbers from 0 to n.
2. Subtract each element in the array from this sum.
3. The remaining value is the missing number.

Why It Works:
- Exactly one number is missing from the complete range.
- All other numbers cancel out during subtraction.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package BitManipulation.Easy;

public class _268_Missing_Number {
    // Method find the missing number in the array
    public static int missingNumber(int[] nums) {
        // Initialize the length of the nums array
        int length = nums.length;

        // Get the total sum from zero to n
        int sum = (length * (length + 1)) / 2;

        // Iterate over the nums array and subtract the current number from the sum
        for (int num : nums) {
            sum -= num;
        }

        // Return the sum
        return sum;
    }

    // Main method to test missingNumber
    public static void main(String[] args) {
        int[] nums = new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 };

        int result = missingNumber(nums);

        System.out.println("The missing number in the array is : " + result);
    }
}
