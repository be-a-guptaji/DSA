/*
LeetCode Problem: https://leetcode.com/problems/concatenation-of-array/

Question: 1929. Concatenation of Array

Problem Statement: Given an integer array nums of length n, you want to create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).

Specifically, ans is the concatenation of two nums arrays.

Return the array ans.

Example 1:
Input: nums = [1,2,1]
Output: [1,2,1,1,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
- ans = [1,2,1,1,2,1]

Example 2:
Input: nums = [1,3,2,1]
Output: [1,3,2,1,1,3,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
- ans = [1,3,2,1,1,3,2,1]

Constraints:

n == nums.length
1 <= n <= 1000
1 <= nums[i] <= 1000
 */

/*
Approach: Direct Array Copy (Concatenation)

Goal:
Create a new array that is the concatenation of the given array with itself.

Algorithm:
1. Let n be the length of the input array.
2. Create a new array of size 2n.
3. For each index i from 0 to n − 1:
   - Set result[i] = nums[i]
   - Set result[i + n] = nums[i]
4. Return the resulting array.

Why It Works:
- Each element is copied twice at the correct positions.
- No additional complex logic is required.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package Arrays.Easy;

import java.util.Arrays;

public class _1929_Concatenation_of_Array {
    // Method to concatenate a array twice
    public static int[] getConcatenation(int[] nums) {
        // Get the length of the array
        int lenght = nums.length;

        // Initialize the new array twice of its length
        int[] result = new int[2 * lenght];

        // Iterate over the nums array to concatenate the array
        for (int i = 0; i < lenght; i++) {
            result[i] = result[lenght + i] = nums[i];
        }

        // Return the result
        return result;
    }

    // Main method to test getConcatenation
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, 2, 1 };

        int[] result = getConcatenation(nums);

        System.out.println("The concatenation of " + Arrays.toString(nums) + " is : " + Arrays.toString(result));
    }
}
