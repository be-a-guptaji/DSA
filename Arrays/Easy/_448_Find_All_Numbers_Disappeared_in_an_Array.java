/*
LeetCode Problem: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

Question: 448. Find All Numbers Disappeared in an Array

Problem Statement: Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.

Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]

Example 2:
Input: nums = [1,1]
Output: [2]

Constraints:

n == nums.length
1 <= n <= 10^5
1 <= nums[i] <= n

Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */

/*
Approach: In-Place Index Marking (Sign Flipping)

Goal:
Find all numbers in the range [1, n] that do not appear in the array,
where n = array length.

Key Insight:
Each value v in the array corresponds to index (v − 1).
We can use the sign of elements to mark presence without extra space.

Algorithm:
1. Traverse the array:
   - For each number num:
       • Compute index = |num| − 1
       • Mark nums[index] as negative (if not already)
       → This indicates that (index + 1) exists in the array.

2. Traverse the array again:
   - If nums[i] is positive:
       • (i + 1) was never marked → missing number.
       • Add (i + 1) to the result list.

Why It Works:
- Negative marking records presence in-place.
- Positive values after marking indicate missing indices.

Time Complexity: O(n)  
Space Complexity: O(1) extra space (excluding output list)
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.List;

public class _448_Find_All_Numbers_Disappeared_in_an_Array {
    // Method to find the missing numbers in array
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        // Place the number to its index and mark them negative
        for (int num : nums) {
            // Get the index for the number
            int index = Math.abs(num) - 1;

            // Place to the index
            nums[index] = -Math.abs(nums[index]);
        }

        // Initialize the array list for the result
        ArrayList<Integer> result = new ArrayList<>();

        // Check for all the positive index values as they are not in the array
        for (int i = 0; i < nums.length; i++) {
            // If number is positve then add to the result array
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        // Return the result
        return result;
    }

    // Main method to test findDisappearedNumbers
    public static void main(String[] args) {
        int[] nums = new int[] { 4, 3, 2, 7, 8, 2, 3, 1 };

        List<Integer> result = findDisappearedNumbers(nums);

        System.out.println("The missing numbers in array is : " + result);
    }
}
