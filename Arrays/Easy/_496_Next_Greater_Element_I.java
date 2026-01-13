/*
LeetCode Problem:https://leetcode.com/problems/next-greater-element-i/

Question: 496. Next Greater Element I

Problem Statement: The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.

Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.

Constraints:

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 10^4
All integers in nums1 and nums2 are unique.
All the integers of nums1 also appear in nums2.

Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */

/*
Approach: Monotonic Stack + Index Mapping

Goal:
For each element in nums1, find the next greater element in nums2.
If no such element exists, return -1.

Key Idea:
- Use a monotonic decreasing stack to process nums2.
- Use an index map to know which elements of nums2 are relevant (i.e., present in nums1)
  and where to store their answers.

Algorithm:
1. Build an index map:
   - indexMap[value] = index + 1 in nums1 (use +1 to distinguish from default 0).
2. Initialize result array with -1 for all positions.
3. Traverse nums2 from left to right:
   - While stack is not empty and current num > stack top:
       • Pop the stack value x.
       • Update result[indexMap[x] - 1] = num (num is the next greater element).
   - If current num exists in nums1 (indexMap[num] != 0):
       • Push num onto the stack.
4. Elements left in the stack have no next greater element → remain -1.

Why It Works:
- The monotonic stack ensures each element is pushed and popped at most once.
- Processing nums2 in order guarantees the first greater element to the right is found.
- Index mapping allows O(1) updates to the correct position in result.

Time Complexity: O(n + m)
- n = length of nums1
- m = length of nums2

Space Complexity: O(m)
*/

package Arrays.Easy;

import java.util.Arrays;

public class _496_Next_Greater_Element_I {
    // Method to find the next greater element from the different array
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Initialize index map (value -> index + 1)
        int[] indexMap = new int[10001];

        // Initialize the result array
        int[] result = new int[nums1.length];

        // Initialize the stack to store elements
        int[] stack = new int[nums2.length];

        // Initialize the stack pointer with negative one
        int top = -1;

        // Fill the index map and initialize result with -1
        for (int i = 0; i < nums1.length; i++) {
            // Store index + 1 to differentiate from default zero
            indexMap[nums1[i]] = i + 1;

            // Initialize result with negative one
            result[i] = -1;
        }

        // Iterate over the nums2 array
        for (int num : nums2) {

            // If current number is greater than stack top,
            // pop the element and update the result array
            while (top != -1 && num > stack[top]) {
                // Update the next greater element for popped value
                result[indexMap[stack[top--]] - 1] = num;
            }

            // If current number exists in nums1, push it to the stack
            if (indexMap[num] != 0) {
                stack[++top] = num;
            }
        }

        // Return the result array
        return result;
    }

    // Main method to test nextGreaterElement
    public static void main(String[] args) {
        int[] nums1 = new int[] { 4, 1, 2 }, nums2 = new int[] { 1, 3, 4, 2 };

        int[] result = nextGreaterElement(nums1, nums2);

        System.out.println("The next greater elements are : " + Arrays.toString(result));
    }
}
