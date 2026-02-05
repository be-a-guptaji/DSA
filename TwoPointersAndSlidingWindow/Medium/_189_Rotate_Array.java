/*
LeetCode Problem: https://leetcode.com/problems/rotate-array/

Question: 189. Rotate Array

Problem Statement: Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:

1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
0 <= k <= 10^5

Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 */

/*
Approach: Reversal Algorithm (In-Place Array Rotation)

Goal:
Rotate the array to the right by k steps.

Key Insight:
A right rotation can be achieved using a sequence of reversals.
This avoids extra space and runs in linear time.

Steps:
1. Normalize k:
   - k = k % n (to handle cases where k > array length).

2. Reverse the entire array.
   - This brings the elements that should move to the front closer to their target positions.

3. Reverse the first k elements.
   - This fixes the order of the first k elements after rotation.

4. Reverse the remaining n − k elements.
   - This restores the correct order of the rest of the array.

Example:
nums = [1,2,3,4,5,6,7], k = 3

Step 1: Reverse whole array
→ [7,6,5,4,3,2,1]

Step 2: Reverse first k elements
→ [5,6,7,4,3,2,1]

Step 3: Reverse remaining elements
→ [5,6,7,1,2,3,4]

Why It Works:
- Reversal rearranges elements while preserving relative order within segments.
- Combining three reversals produces the exact right rotation.

Complexity:
- Time: O(n)
- Space: O(1) (in-place)
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _189_Rotate_Array {
  // Method to rotate the nums array
  public static void rotate(int[] nums, int k) {
    // Initialize the length of the nums array
    int length = nums.length;

    // Get the k in range form 0 to nums length
    k %= length;

    // Rotate the array
    rotate(nums, 0, length - 1);
    rotate(nums, 0, k - 1);
    rotate(nums, k, length - 1);
  }

  // Helper method to rotate the array at the particular indices
  private static void rotate(int[] nums, int start, int end) {
    // Swap the nums index from both side
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;

      // Update the indices
      start++;
      end--;
    }
  }

  // Main method to test rotate
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
    int k = 10;

    rotate(nums, k);

    System.out.println("The rotated array is : " + Arrays.toString(nums));
  }
}
