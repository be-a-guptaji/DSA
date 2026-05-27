/*
LeetCode Problem: https://leetcode.com/problems/first-missing-positive/

Question: 41. First Missing Positive

Problem Statement: Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.

Constraints:
1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
*/

/*
Approach: In-Place Index Marking

Goal:
- Find the smallest missing positive integer
  in linear time and constant extra space.

Core Idea:
- Only values in range [1, n] matter.
- Use array indices as presence markers:
    value x → index x - 1
- Mark existing numbers by making corresponding
  indices negative.

Algorithm Steps:
1. Replace negative values with 0:
   - Ignore irrelevant numbers.
2. Traverse array again:
   - index = abs(nums[i]) - 1
   - If index in valid range:
       - Mark nums[index] as negative
       - Handle 0 separately because:
           0 cannot become negative
3. Traverse array:
   - First non-negative index i
     means number (i + 1) is missing.
4. If all indices marked:
   - Return n + 1.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the smallest missing positive integer.
*/

package Arrays.Hard;

// Solution Class
class Solution {
   // Method to find the number of distinct valid names for the company
   public int firstMissingPositive(int[] nums) {
      // Remove the negative number to zero
      for (int i = 0; i < nums.length; i++) {
         if (nums[i] < 0) {
            nums[i] = 0;
         }
      }

      // Negative mark the array
      for (int i = 0; i < nums.length; i++) {
         int index = Math.abs(nums[i]) - 1;

         if (index >= 0 && index < nums.length) {
            if (nums[index] == 0) {
               nums[index] = -(nums.length + 1);
            } else if (nums[index] > 0) {
               nums[index] *= -1;
            }
         }
      }

      // Find the smallest missing number
      for (int i = 0; i < nums.length; i++) {
         if (nums[i] >= 0) {
            return i + 1;
         }
      }

      // Return the nums.length + 1 in the end
      return nums.length + 1;
   }
}

public class _41_First_Missing_Positive {
   // Main method to test the firstMissingPositive
   public static void main(String[] args) {
      int[] nums = new int[] { 1, 2, 0 };

      long result = new Solution().firstMissingPositive(nums);

      System.out.println("The number of distinct valid names for the company is : " + result);
   }
}
