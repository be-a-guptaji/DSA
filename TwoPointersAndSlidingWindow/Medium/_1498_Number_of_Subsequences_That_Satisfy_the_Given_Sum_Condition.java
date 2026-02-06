/*
LeetCode Problem: https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/

Question: 1498. Number of Subsequences That Satisfy the Given Sum Condition

Problem Statement: You are given an array of integers nums and an integer target.

Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: nums = [3,5,6,7], target = 9
Output: 4
Explanation: There are 4 subsequences that satisfy the condition.
[3] -> Min value + max value <= target (3 + 3 <= 9)
[3,5] -> (3 + 5 <= 9)
[3,5,6] -> (3 + 6 <= 9)
[3,6] -> (3 + 6 <= 9)

Example 2:
Input: nums = [3,3,6,8], target = 10
Output: 6
Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]

Example 3:
Input: nums = [2,3,3,4,6,7], target = 12
Output: 61
Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
Number of valid subsequences (63 - 2 = 61).

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
1 <= target <= 10^6
 */

/*
Approach: Two Pointers + Precomputed Powers (Greedy Counting)

Problem Idea:
Count non-empty subsequences such that:
min(subsequence) + max(subsequence) ≤ target

Key Observations:
1. Order does NOT matter in a subsequence, but min and max do.
2. Sorting the array lets us fix the minimum and reason about the maximum.
3. For a fixed minimum at index `left`, we want the largest possible `right`
   such that nums[left] + nums[right] ≤ target.

Algorithm Steps:

1. Sort the array
   - Ensures nums[left] is the minimum and nums[right] is the maximum.

2. Precompute powers of 2
   - powerOfTwo[i] = 2^i % mod
   - Reason: For elements between (left, right), each element can be
     either chosen or not → 2^(right - left) subsequences.

3. Two-pointer technique
   - Initialize:
     left = 0, right = n - 1
   - While left ≤ right:
     a) If nums[left] + nums[right] ≤ target:
        - Fix nums[left] as minimum.
        - Any subset of elements between left and right can be chosen.
        - Count = 2^(right - left)
        - Add to answer.
        - Move left forward.
     b) Else:
        - nums[right] too large, decrease right.

Why It Works:
- Fixing the minimum ensures the constraint only depends on the maximum.
- Sorting guarantees all elements between left and right are ≥ nums[left]
  and ≤ nums[right].
- Power-of-two counts efficiently enumerate all valid subsets.

Example:
nums = [3,5,6,7], target = 9

Sorted: [3,5,6,7]
left=0, right=2 → 3+6 ≤ 9
Valid subsequences with min=3:
[3], [3,5], [3,6], [3,5,6] → 2^(2) = 4

Complexity:
- Time: O(n log n) (sorting) + O(n) (two pointers)
- Space: O(n) (power array)

Modulo:
- Required due to large counts (2^n growth).
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _1498_Number_of_Subsequences_That_Satisfy_the_Given_Sum_Condition {
    // Method to find the number of subsequences whose sum of minimum and maximum
    // element is less than or equal to target
    public static int numSubseq(int[] nums, int target) {
        // Sort the array
        Arrays.sort(nums);

        // Define the modulo value
        int mod = 1000000007;

        // Get the length of the array
        int n = nums.length;

        // Precompute the power of 2 values modulo mod
        // powerOfTwo[i] = 2^i % mod
        int[] powerOfTwo = new int[n];
        powerOfTwo[0] = 1;

        // Fill the powerOfTwo array
        for (int i = 1; i < n; i++) {
            powerOfTwo[i] = (powerOfTwo[i - 1] * 2) % mod;
        }

        // Initialize the total number of subsequences variable
        long totalNumberOfSubSequences = 0;

        // Initialize the left and right pointers
        int left = 0;
        int right = n - 1;

        // Iterate until the left pointer crosses the right pointer
        while (left <= right) {
            // If the sum of the minimum and maximum element
            // is less than or equal to target
            if (nums[left] + nums[right] <= target) {
                // All subsequences formed by fixing nums[left]
                // and choosing any combination of elements
                // between left and right are valid
                totalNumberOfSubSequences = (totalNumberOfSubSequences + powerOfTwo[right - left]) % mod;

                // Move the left pointer to the right
                left++;
            } else {
                // If the sum exceeds the target,
                // decrease the right pointer
                right--;
            }
        }

        // Return the total number of subsequences
        return (int) totalNumberOfSubSequences;
    }

    // Main method to test numSubseq
    public static void main(String[] args) {
        int[] nums = { 2, 3, 3, 4, 6, 7 };
        int target = 12;

        int result = numSubseq(nums, target);

        System.out.println("The sum of the minimum and maximum element on it is less or equal to target is " + result);
    }
}
