/*
LeetCode Problem: https://leetcode.com/problems/range-sum-query-immutable/

Question: 303. Range Sum Query - Immutable

Problem Statement: Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

Example 1:
Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]
Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3

Constraints:

1 <= nums.length <= 10^4
-10^5 <= nums[i] <= 10^5
0 <= left <= right < nums.length
At most 10^4 calls will be made to sumRange.
 */

/*
Approach: Prefix Sum Array

Goal:
Efficiently compute the sum of elements between indices left and right (inclusive)
for multiple queries.

Key Idea:
Precompute cumulative sums so that each range sum query can be answered in O(1) time.

Algorithm:
1. Build a prefix sum array:
   - prefixSum[0] = 0
   - prefixSum[i + 1] = prefixSum[i] + nums[i]
   - prefixSum[k] stores the sum of elements from index 0 to k − 1.

2. To compute sumRange(left, right):
   - Use the formula:
       sum = prefixSum[right + 1] − prefixSum[left]

Why It Works:
- Subtracting prefix sums removes the contribution of elements before left.
- Each query avoids recomputation of sums.

Time Complexity:
- Preprocessing: O(n)
- Each sumRange query: O(1)

Space Complexity: O(n)
*/

package SystemDesign.Easy;

import java.util.ArrayList;
import java.util.List;

public class _303_Range_Sum_Query_Immutable {
    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */

    // Class to make the NumArray
    private static class NumArray {
        // Initialize the prefix sum for the nums array
        private final int[] prefixSum;

        public NumArray(int[] nums) {
            // Initialize the prefix sum length equal to the length of the nums array
            this.prefixSum = new int[nums.length + 1];

            // Fill the prefix sum array
            for (int i = 0; i < nums.length; i++) {
                this.prefixSum[i + 1] = this.prefixSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            // Return the difference of the indices
            return this.prefixSum[right + 1] - this.prefixSum[left];
        }
    }

    // Main method to test NumArray
    public static void main(String[] args) {
        String[] operations = { "NumArray", "sumRange", "sumRange", "sumRange" };

        List<int[]> values = new ArrayList<>();
        values.add(new int[] { -2, 0, 3, -5, 2, -1 }); // constructor input
        values.add(new int[] { 0, 2 });
        values.add(new int[] { 2, 5 });
        values.add(new int[] { 0, 5 });

        NumArray numArray = new NumArray(new int[] { -2, 0, 3, -5, 2, -1 });

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("NumArray")) {
                numArray = new NumArray(values.get(i));
                System.out.println("null");
            }

            if (operation.equals("sumRange")) {
                int left = values.get(i)[0];
                int right = values.get(i)[1];
                System.out.println(numArray.sumRange(left, right));
            }
        }
    }
}
