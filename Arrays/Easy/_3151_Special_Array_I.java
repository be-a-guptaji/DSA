/*
LeetCode Problem: https://leetcode.com/problems/special-array-i/

Question: 3151. Special Array I

Problem Statement: An array is considered special if the parity of every pair of adjacent elements is different. In other words, one element in each pair must be even, and the other must be odd.

You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.

Example 1:
Input: nums = [1]
Output: true
Explanation:
There is only one element. So the answer is true.

Example 2:
Input: nums = [2,1,4]
Output: true
Explanation:
There is only two pairs: (2,1) and (1,4), and both of them contain numbers with different parity. So the answer is true.

Example 3:
Input: nums = [4,3,1,6]
Output: false
Explanation:
nums[1] and nums[2] are both odd. So the answer is false.

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
 */

/*
Approach: Adjacent Parity Check

Goal:
Determine whether an array is “special”.
An array is special if every pair of adjacent elements has different parity
(one even, one odd).

Key Idea:
Compare the parity of each element with its previous neighbor.

Algorithm:
1. Traverse the array starting from index 1.
2. For each i:
   - Check parity using (num & 1).
   - If nums[i] and nums[i−1] have the same parity, return false.
3. If all adjacent pairs have different parity, return true.

Why It Works:
- Bitwise AND with 1 efficiently determines odd/even.
- A single pass is sufficient.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _3151_Special_Array_I {
    // Method to find if nums is a special array
    public static boolean isArraySpecial(int[] nums) {
        // Iterate over the nums array and check for the adjacent paraity
        for (int i = 1; i < nums.length; i++) {
            // If paraity is same then return false
            if ((nums[i - 1] & 1) == (nums[i] & 1)) {
                return false;
            }
        }

        // Retrurn true in the end
        return true;
    }

    // Main method to test isArraySpecial
    public static void main(String[] args) {
        int[] nums = new int[] { 2, 1, 4 };

        boolean result = isArraySpecial(nums);

        System.out.println("The array is" + (result ? " " : " not ") + "special.");
    }
}
