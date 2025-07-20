/*
LeetCode Problem: https://leetcode.com/problems/3sum/

Question: 15. 3Sum

Problem Statement: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:
Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

Constraints:

3 <= nums.length <= 3000
-10^5 <= nums[i] <= 10^5
 */

/*
Approach:
The solution uses sorting and the two-pointer technique to find all unique triplets in the array that sum up to zero.

1. Sort the input array to efficiently handle duplicates and use two pointers.
2. Iterate over the array with index `i` (from 0 to n-3):
   - Skip duplicates to avoid repeating the same triplet.
   - Use two pointers:
     - `left` starts from `i + 1`
     - `right` starts from the end of the array
   - Compute the sum of nums[i] + nums[left] + nums[right]:
     - If the sum is zero, we found a valid triplet. Add it to the result list and move both pointers, skipping duplicates.
     - If the sum is less than zero, move the `left` pointer forward to increase the sum.
     - If the sum is greater than zero, move the `right` pointer backward to decrease the sum.

This approach ensures that all valid triplets are found without duplicates.

Time Complexity: O(n^2)
- Sorting the array takes O(n log n)
- The main loop runs in O(n), and the inner two-pointer scan runs in O(n) in total per i

Space Complexity: O(1) (excluding the space used to store the result)
- No extra data structures are used apart from the output list.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_3Sum {
    // Method to find all triplets that sum to zero
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Sort the array first
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];

                if (total == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (total < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        // Return the result
        return result;
    }

    // Main method to test threeSum
    public static void main(String[] args) {
        int[] nums = { -1, 0, 1, 2, -1, -4 };

        List<List<Integer>> triplets = threeSum(nums);

        System.out.println("Triplets that sum to zero: " + triplets);
    }
}
