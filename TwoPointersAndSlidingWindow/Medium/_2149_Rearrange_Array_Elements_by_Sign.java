/*
LeetCode Problem: https://leetcode.com/problems/rearrange-array-elements-by-sign/

Question: 2149. Rearrange Array Elements by Sign

Problem Statement: You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.

You should return the array of nums such that the array follows the given conditions:

Every consecutive pair of integers have opposite signs.
For all integers with the same sign, the order in which they were present in nums is preserved.
The rearranged array begins with a positive integer.
Return the modified array after rearranging the elements to satisfy the aforementioned conditions.

Example 1:
Input: nums = [3,1,-2,-5,2,-4]
Output: [3,-2,1,-5,2,-4]
Explanation:
The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.  

Example 2:
Input: nums = [-1,1]
Output: [1,-1]
Explanation:
1 is the only positive integer and -1 the only negative integer in nums.
So nums is rearranged to [1,-1].

Constraints:

2 <= nums.length <= 2 * 10^5
nums.length is even
1 <= |nums[i]| <= 10^5
nums consists of equal number of positive and negative integers.

It is not required to do the modifications in-place.
 */

/*
Approach: Two Pointers with Fixed Even/Odd Placement

Goal:
- Rearrange the array so that:
    - Positive numbers appear at even indices (0, 2, 4, ...)
    - Negative numbers appear at odd indices (1, 3, 5, ...)
- The relative order of positives and negatives must be preserved.

Key Assumption:
- The array contains equal number of positive and negative elements.
- The final array must alternate signs starting with a positive number.

Strategy:

1. Create a new array `newNums` of the same size.

2. Maintain two indices:
   - positiveIndex = 0  → even positions
   - negativeIndex = 1  → odd positions

3. Traverse the original array once:
   - If nums[i] > 0:
        → place at newNums[positiveIndex]
        → positiveIndex += 2
   - Else:
        → place at newNums[negativeIndex]
        → negativeIndex += 2

Why This Works:
- Even and odd positions guarantee alternating signs.
- Since we iterate left to right, relative order is preserved.
- No sorting or extra grouping needed.

Time Complexity:
- O(n)

Space Complexity:
- O(n) (new result array)

Result:
- Returns an array with alternating positive and negative numbers.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _2149_Rearrange_Array_Elements_by_Sign {
    // Method to modified array after rearranging the elements to satisfy the
    // aforementioned conditions
    public static int[] rearrangeArray(int[] nums) {
        // Initialize the new array to return
        int[] newNums = new int[nums.length];

        // Initialize two pointer positive and negative index
        int positiveIndex = 0, negativeIndex = 1;

        // Iterate over the nums to fill the new nums array
        for (int i = 0; i < nums.length; i++) {
            // Fill the newNums accordingly
            if (nums[i] > 0) {
                // Fill the positive index with nums[i]
                newNums[positiveIndex] = nums[i];

                // Update the positive index
                positiveIndex += 2;
            } else {
                // Fill the negative index with nums[i]
                newNums[negativeIndex] = nums[i];

                // Update the negative index
                negativeIndex += 2;
            }
        }

        // Retrun the newNums
        return newNums;
    }

    // Main method to test rearrangeArray
    public static void main(String[] args) {
        int[] nums = { 2, 3, 3, 4, 6, 7 };

        int[] result = rearrangeArray(nums);

        System.out.println(
                "The modified array after rearranging the elements to satisfy the aforementioned conditions is : "
                        + Arrays.toString(result));
    }
}
