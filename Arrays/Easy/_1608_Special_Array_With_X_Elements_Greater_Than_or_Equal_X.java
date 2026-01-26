/*
LeetCode Problem: https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/

Question: 1608. Special Array With X Elements Greater Than or Equal X

Problem Statement: You are given an array nums of non-negative integers. nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.

Notice that x does not have to be an element in nums.

Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.

Example 1:
Input: nums = [3,5]
Output: 2
Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.

Example 2:
Input: nums = [0,0]
Output: -1
Explanation: No numbers fit the criteria for x.
If x = 0, there should be 0 numbers >= x, but there are 2.
If x = 1, there should be 1 number >= x, but there are 0.
If x = 2, there should be 2 numbers >= x, but there are 0.
x cannot be greater since there are only 2 numbers in nums.

Example 3:
Input: nums = [0,4,3,0,4]
Output: 3
Explanation: There are 3 values that are greater than or equal to 3.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */

/*
Approach: Counting + Reverse Accumulation

Goal:
Find a special number x such that exactly x elements in the array
are greater than or equal to x.
If no such number exists, return −1.

Key Idea:
Count how many numbers are ≥ each possible value using a counting array,
then accumulate counts from the right.

Algorithm:
1. Create a count array of size n + 1, where n = nums.length.
   - For each num in nums:
       • Increment count[min(num, n)].
2. Initialize totalRight = 0.
3. Traverse i from n down to 0:
   - totalRight += count[i]  (numbers ≥ i)
   - If totalRight == i, return i.
4. If no match is found, return −1.

Why It Works:
- count[i] tracks how many numbers are at least i.
- Reverse accumulation gives exact counts of elements ≥ i.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package Arrays.Easy;

public class _1608_Special_Array_With_X_Elements_Greater_Than_or_Equal_X {
    // Method to find the special number in the array
    public static int specialArray(int[] nums) {
        // Initialize the count array equal to the length of the nums array
        int[] count = new int[nums.length + 1];
        
        // Iterate over the nums array for the frequency
        for (int num : nums) {
            count[Math.min(num, nums.length)]++;
        }
        
        // Initialize the total right for the sum
        int totalRight = 0;

        // Iterate over the nums array for the special number
        for (int i = nums.length; i >= 0; i--) {
            // Add the total right of the count index
            totalRight += count[i];

            // If index is equal to the totalRight then return the totalRight
            if (i == totalRight) {
                return totalRight;
            }
        }

        // Return the negative one in the end
        return -1;
    }

    // Main method to test specialArray
    public static void main(String[] args) {
        int[] nums = new int[] { 0, 4, 3, 0, 4 };

        int result = specialArray(nums);

        System.out.println("The special number in the array is : " + result);
    }
}
