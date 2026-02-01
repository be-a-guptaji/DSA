/*
LeetCode Problem: https://leetcode.com/problems/apply-operations-to-an-array/

Question: 2460. Apply Operations to an Array

Problem Statement: You are given a 0-indexed array nums of size n consisting of non-negative integers.

You need to apply n - 1 operations to this array where, in the ith operation (0-indexed), you will apply the following on the ith element of nums:

If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0. Otherwise, you skip this operation.
After performing all the operations, shift all the 0's to the end of the array.

For example, the array [1,0,2,0,0,1] after shifting all its 0's to the end, is [1,2,1,0,0,0].
Return the resulting array.

Note that the operations are applied sequentially, not all at once.

Example 1:
Input: nums = [1,2,2,1,1,0]
Output: [1,4,2,0,0,0]
Explanation: We do the following operations:
- i = 0: nums[0] and nums[1] are not equal, so we skip this operation.
- i = 1: nums[1] and nums[2] are equal, we multiply nums[1] by 2 and change nums[2] to 0. The array becomes [1,4,0,1,1,0].
- i = 2: nums[2] and nums[3] are not equal, so we skip this operation.
- i = 3: nums[3] and nums[4] are equal, we multiply nums[3] by 2 and change nums[4] to 0. The array becomes [1,4,0,2,0,0].
- i = 4: nums[4] and nums[5] are equal, we multiply nums[4] by 2 and change nums[5] to 0. The array becomes [1,4,0,2,0,0].
After that, we shift the 0's to the end, which gives the array [1,4,2,0,0,0].

Example 2:
Input: nums = [0,1]
Output: [1,0]
Explanation: No operation can be applied, we just shift the 0 to the end.

Constraints:

2 <= nums.length <= 2000
0 <= nums[i] <= 1000
*/

/*
Approach: One-Pass Simulation + Compaction

Problem:
Apply the given operation on the array:
- If nums[i] == nums[i+1], replace nums[i] with nums[i] * 2 and nums[i+1] with 0
- After processing, move all non-zero elements to the front while preserving order
- Fill remaining positions with zeros (implicitly via default array)

Core Idea:
- Traverse the array once.
- Apply the merge operation on adjacent equal non-zero elements.
- Directly place valid values into a result array using an index pointer.
- Skip merged elements to avoid double counting.

Algorithm:
1. Initialize a result array with the same length as nums.
2. Use an index pointer to track the next position to fill in result.
3. Traverse nums from left to right:
   - If nums[i] is non-zero:
     - If nums[i] == nums[i+1], store nums[i] * 2 and skip the next element.
     - Else, store nums[i] as-is.
4. Handle the last element separately if it was not processed.
5. Return the result array (remaining positions stay 0 by default).

Why It Works:
- Merging is done before shifting, matching the problem requirement.
- Writing directly to result avoids a second pass for shifting zeros.
- Order of non-zero elements is preserved.

Time Complexity:
- O(n)

Space Complexity:
- O(n) for the result array
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

public class _2460_Apply_Operations_to_an_Array {
    // Method to form the array after preforming operations
    public static int[] applyOperations(int[] nums) {
        // Initialize the array for the result
        int[] result = new int[nums.length];

        // Initialzie the index for the result array
        int index = 0, i;

        // Iterate over the nums array
        for (i = 0; i < nums.length - 1; i++) {
            // If number is non zero preform the operation
            if (nums[i] != 0) {
                if (nums[i] == nums[i + 1]) {
                    result[index++] = nums[i] * 2;
                    i++;
                } else {
                    result[index++] = nums[i];
                }
            }
        }

        // If last element is not zero add it to the array
        if (i != nums.length) {
            result[index] = nums[nums.length - 1];
        }

        // Retrun the result array
        return result;
    }

    // Main method to test applyOperations
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 2, 1, 1, 0 };

        int[] result = applyOperations(nums);

        System.out.println("The array after preforming operations is : " + Arrays.toString(result));
    }
}
