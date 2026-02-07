/*
LeetCode Problem: https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/

Question: 1968. Array With Elements Not Equal to Average of Neighbors

Problem Statement: You are given a 0-indexed array nums of distinct integers. You want to rearrange the elements in the array such that every element in the rearranged array is not equal to the average of its neighbors.

More formally, the rearranged array should have the property such that for every i in the range 1 <= i < nums.length - 1, (nums[i-1] + nums[i+1]) / 2 is not equal to nums[i].

Return any rearrangement of nums that meets the requirements.

Example 1:
Input: nums = [1,2,3,4,5]
Output: [1,2,4,5,3]
Explanation:
When i=1, nums[i] = 2, and the average of its neighbors is (1+4) / 2 = 2.5.
When i=2, nums[i] = 4, and the average of its neighbors is (2+5) / 2 = 3.5.
When i=3, nums[i] = 5, and the average of its neighbors is (4+3) / 2 = 3.5.

Example 2:
Input: nums = [6,2,0,9,7]
Output: [9,7,6,2,0]
Explanation:
When i=1, nums[i] = 7, and the average of its neighbors is (9+6) / 2 = 7.5.
When i=2, nums[i] = 6, and the average of its neighbors is (7+2) / 2 = 4.5.
When i=3, nums[i] = 2, and the average of its neighbors is (6+0) / 2 = 3.
Note that the original array [6,2,0,9,7] also satisfies the conditions.

Constraints:

3 <= nums.length <= 10^5
0 <= nums[i] <= 10^5
 */

/*
Approach: Greedy Zig-Zag (Wave) Rearrangement

Goal:
Rearrange the array so that for every index i (1 ≤ i < n-1):
(nums[i-1] + nums[i+1]) / 2 ≠ nums[i]

Key Insight:
If the array follows a strict zig-zag (wave) pattern:
nums[0] < nums[1] > nums[2] < nums[3] > ...
or
nums[0] > nums[1] < nums[2] > nums[3] < ...
then nums[i] can never be the average of its neighbors.

Why?
- In a zig-zag pattern, nums[i] is always either a local peak or a local valley.
- The average of two numbers lies strictly between them.
- A peak or valley cannot equal the average of its neighbors.

Algorithm:
1. Decide the initial trend using nums[0] and nums[1]:
   - increase = true  → expect nums[i] < nums[i+1]
   - increase = false → expect nums[i] > nums[i+1]

2. Iterate from index 1 to n-2:
   - If the current pair violates the expected trend:
     - Swap nums[i] and nums[i+1]
   - Toggle the expected trend after each step.

3. This enforces a zig-zag order in one pass.

Why Greedy Works:
- Fixing local order (i, i+1) is enough to satisfy the condition at index i.
- Swapping locally does not break previously fixed positions.

Time Complexity: O(n)  
Space Complexity: O(1) (in-place)

Result:
The array satisfies:
(nums[i-1] + nums[i+1]) / 2 ≠ nums[i] for all valid i.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _1968_Array_With_Elements_Not_Equal_to_Average_of_Neighbors {
    // Method to rearranged array should have the property such that for every i in
    // the range 1 <= i < nums.length - 1, (nums[i-1] + nums[i+1]) / 2 is not equal
    // to nums[i]
    public static int[] rearrangeArray(int[] nums) {
        // Initialize the boolean variable to check if the array is intially increasing
        // or not
        boolean increase = nums[0] < nums[1];

        // Iterate over the nums array form 1 to n - 1 to swap the variables
        for (int i = 1; i < nums.length - 1; i++) {
            if ((increase && nums[i] < nums[i + 1]) || (!increase && nums[i] > nums[i + 1])) {
                // Swap the numbers
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }

            // Toggle the increasing variable
            increase = !increase;
        }

        // Retrun the modified nums
        return nums;
    }

    // Main method to test rearrangeArray
    public static void main(String[] args) {
        int[] nums = { 2, 3, 3, 4, 6, 7 };

        int[] result = rearrangeArray(nums);

        System.out.println(
                "The rearranged array should have the property such that for every i in the range 1 <= i < nums.length - 1, (nums[i-1] + nums[i+1]) / 2 is not equal to nums[i] is : "
                        + Arrays.toString(result));
    }
}
