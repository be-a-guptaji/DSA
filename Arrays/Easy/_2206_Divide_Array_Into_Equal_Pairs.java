/*
LeetCode Problem: https://leetcode.com/problems/divide-array-into-equal-pairs/

Question: 2206. Divide Array Into Equal Pairs

Problem Statement: You are given an integer array nums consisting of 2 * n integers.

You need to divide nums into n pairs such that:

Each element belongs to exactly one pair.
The elements present in a pair are equal.
Return true if nums can be divided into n pairs, otherwise return false.

Example 1:
Input: nums = [3,2,3,2,2,2]
Output: true
Explanation: 
There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.

Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation: 
There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.

Constraints:

nums.length == 2 * n
1 <= n <= 500
1 <= nums[i] <= 500
*/

/*
Approach: Frequency Parity Check

Goal:
Determine whether the array can be divided into pairs such that
each pair contains equal elements.

Key Idea:
For pairing to be possible, every number must appear an even number of times.

Algorithm:
1. Create a boolean array isOdd[] to track parity of frequency for each value.
2. Traverse nums:
   - Toggle isOdd[num] each time the number appears.
3. After traversal:
   - If any entry in isOdd[] is true → that number appeared odd times → return false.
4. If all entries are false → all frequencies are even → return true.

Why It Works:
- Pairing requires two identical elements.
- Even frequency is both necessary and sufficient.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _2206_Divide_Array_Into_Equal_Pairs {
    // Method to determine if nums can be divided into n pairs
    public static boolean divideArray(int[] nums) {
        // Initialize the boolean array for keeping track of the odd number
        boolean[] isOdd = new boolean[501];

        // Iterate over the nums array
        for (int num : nums) {
            // Toggle the boolean index
            isOdd[num] = !isOdd[num];
        }

        // Iterate over the boolean array for find the odd number
        for (boolean odd : isOdd) {
            // If odd is true then return false
            if (odd) {
                return false;
            }
        }

        // Retrun true in the end
        return true;
    }

    // Main method to test divideArray
    public static void main(String[] args) {
        int[] nums = new int[] { 3, 2, 3, 2, 2, 2 };

        boolean result = divideArray(nums);

        System.out.println("The array nums can" + (result ? " " : " not ") + "be divided into n pairs.");
    }
}
