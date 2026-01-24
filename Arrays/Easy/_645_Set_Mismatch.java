/*
LeetCode Problem: https://leetcode.com/problems/set-mismatch/

Question: 645. Set Mismatch

Problem Statement: You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]

Example 2:
Input: nums = [1,1]
Output: [1,2]

Constraints:

2 <= nums.length <= 10^4
1 <= nums[i] <= 10^4
*/

/*
Approach: In-Place Sign Marking

Goal:
Find the number that appears twice (duplicate) and the number that is missing
from an array containing numbers from 1 to n.

Key Idea:
Use the array itself to mark visited numbers by flipping the sign at the
corresponding index.

Algorithm:
1. Initialize result array of size 2:
   - result[0] → duplicate
   - result[1] → missing
2. First pass (marking):
   - For each number x:
       • Let idx = |x| − 1
       • If nums[idx] is already negative:
           → x is the duplicate → store in result[0]
       • Else:
           → Mark nums[idx] as negative.
3. Second pass (find missing):
   - Traverse the array:
       • The index i where nums[i] is positive corresponds to the missing number (i + 1).
       • Store it in result[1].
4. Return result.

Why It Works:
- Each number maps to a unique index.
- Duplicate causes an index to be visited twice.
- Missing number leaves its index unmarked.

Time Complexity: O(n)  
Space Complexity: O(1) extra space
*/

package Arrays.Easy;

public class _645_Set_Mismatch {
    // Method to find that occurs twice and the number that is missing
    public static int[] findErrorNums(int[] nums) {
        // Initialize the result vairable
        int[] result = new int[2];

        // Iterate over the nums array for negative marking
        for (int i = 0; i < nums.length; i++) {
            // Get the absolute value of the value
            int absoluteValue = Math.abs(nums[i]);

            // If the value is negative then add the value to the missing value else change
            // the sign of the number
            if (nums[absoluteValue - 1] < 0) {
                result[0] = absoluteValue;
            } else {
                nums[absoluteValue-1] *= -1;
            }
        }

        // Iterate over the nums array to find the missing value
        for (int i = 0; i < nums.length; i++) {
            // If nums[i] is positive and next index is not duplicate number then add the
            // value to the duplicate value and return the array
            if (nums[i] > 0 && i + 1 != result[0]) {
                result[1] = i + 1;
                return result;
            }
        }

        // Return the null
        return null;
    }

    // Main method to test findErrorNums
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 2, 4 };

        int[] result = findErrorNums(nums);

        System.out.println(
                "The number that occurs twice is " + result[0] + " the number that is missing is " + result[1] + ".");
    }
}
