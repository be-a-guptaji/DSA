/*
LeetCode Problem: https://leetcode.com/problems/make-sum-divisible-by-p/

Question: 1590. Make Sum Divisible by P

Problem Statement: Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.

Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.

A subarray is defined as a contiguous block of elements in the array.

Example 1:
Input: nums = [3,1,4,2], p = 6
Output: 1
Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.

Example 2:
Input: nums = [6,3,5,2], p = 9
Output: 2
Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.

Example 3:
Input: nums = [1,2,3], p = 3
Output: 0
Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= p <= 10^9
*/

/*
Approach: Prefix Sum + HashMap (Modulo Difference)

Goal:
- Find the minimum length subarray to remove so that
  the remaining array sum becomes divisible by p.

Core Idea:
- Let remainder = totalSum % p.
- Need a subarray whose sum % p == remainder.
- Use prefix sums modulo p:
    prefix[i] - prefix[j] ≡ remainder (mod p)
- Store latest index of each prefix remainder in a HashMap
  to minimize subarray length.

Algorithm Steps:
1. Compute totalSum % p.
2. If remainder == 0 → return 0.
3. Initialize map with {0 : -1}.
4. Traverse array:
   - currentSum = (currentSum + nums[i]) % p
   - target = (currentSum - remainder + p) % p
   - If target exists in map:
       → update minimum length.
   - Store current remainder with current index.
5. If no valid subarray found → return -1.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns length of smallest removable subarray,
  or -1 if impossible.
*/

package Arrays.Medium;

import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find the length of the smallest subarray that you need to remove
  public int minSubarray(int[] nums, int p) {
    // Initialize the totalSum variable
    long totalSum = 0;

    // Find the total sum of the nums array
    for (int i = 0; i < nums.length; i++) {
      totalSum += nums[i];
    }

    // Find the remainder of the totalSum
    int remainder = (int) (totalSum % p);

    // If remainder is zero then return zero
    if (remainder == 0) {
      return 0;
    }

    // Initialize the result variable to the length of the nums array
    int result = nums.length;

    // Initialize the remainderMap
    HashMap<Integer, Integer> remainderMap = new HashMap<>();

    // Initialize the remainderMap 0 to -1
    remainderMap.put(0, -1);

    // Initialize the currentSum variable
    long currentSum = 0;

    // Find the minimum sub array
    for (int i = 0; i < nums.length; i++) {
      // Update the currentSum
      currentSum = (currentSum + nums[i]) % p;

      // Get the prefix remainder
      int prefixRemainder = (int) (currentSum - remainder + p) % p;

      // If remainderMap contains prefixRemainder update the result variable
      if (remainderMap.containsKey(prefixRemainder)) {
        result = Math.min(result, i - remainderMap.get(prefixRemainder));
      }

      // Update the remainderMap
      remainderMap.put((int) currentSum, i);
    }

    // Return -1 if result lenght is equal to nums lenght else return the result
    return result == nums.length ? -1 : result;
  }
}

public class _1590_Make_Sum_Divisible_by_P {
  // Main method to test minSubarray
  public static void main(String[] args) {
    int[] nums = new int[] { 3, 1, 4, 2 };
    int p = 6;

    int result = new Solution().minSubarray(nums, p);

    System.out.println("The length of the smallest subarray that you need to remove is : " + result);
  }
}
