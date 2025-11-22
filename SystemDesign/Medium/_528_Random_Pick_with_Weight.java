/*
LeetCode Problem: https://leetcode.com/problems/random-pick-with-weight/

Question: 528. Random Pick with Weight

Problem Statement: You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.

You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).

For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).

Example 1:
Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.

Example 2:
Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.

Constraints:

1 <= w.length <= 10^4
1 <= w[i] <= 10^5
pickIndex will be called at most 10^4 times.
 */

/*
Approach:
1. We are given an array of weights `w` where the goal is to pick an index
   randomly, but the chance of picking an index must be proportional to its weight.

2. To achieve this, we convert the weight array into a prefix-sum array:
       prefix[i] = w[0] + w[1] + ... + w[i]
   This allows each index to occupy a continuous range in the total weight space.

3. The total weight is the last value of the prefix-sum array.  
   We generate a random number from 1 to totalWeight (both inclusive):
       random ∈ [1, totalSum]

4. To determine which index this random value maps to, we perform a binary search
   on the prefix-sum array to find the first index such that:
       prefix[index] >= random
   That index is the result because the random value falls inside that range.

5. This ensures that:
       • Larger weights occupy a larger range
       • Smaller weights occupy a smaller range
       • Random selection is perfectly proportional to the given weights

Time Complexity:
    • O(n) to build the prefix-sum array
    • O(log n) for each pickIndex() call due to binary search

Space Complexity:
    • O(n) for storing the prefix-sum array
*/

package SystemDesign.Medium;

import java.util.concurrent.ThreadLocalRandom;

public class _528_Random_Pick_with_Weight {
    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(w);
     * int param_1 = obj.pickIndex();
     */

    // Class to make the Solution
    private static class Solution {
        // Initialize the array to store the prefix sum of weights
        private final int[] prefixSum;

        // Initialize the total sum of all weights
        private final int totalSum;

        public Solution(int[] w) {
            int length = w.length;

            // Initialize the prefixSum array
            this.prefixSum = new int[length];

            // Initialize totalSum
            int sum = 0;

            // Build the prefix sum array
            for (int i = 0; i < length; i++) {
                sum += w[i];
                this.prefixSum[i] = sum;
            }

            // Store the total sum
            this.totalSum = sum;
        }

        public int pickIndex() {
            // Generate a random number between 1 and totalSum
            int random = ThreadLocalRandom.current().nextInt(1, this.totalSum + 1);

            // Initialize left and right for binary search
            int left = 0, right = this.prefixSum.length - 1;

            // Find the first index where prefixSum[index] >= random
            while (left < right) {
                int mid = (left + right) / 2;

                if (this.prefixSum[mid] >= random) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // Return the left index
            return left;
        }
    }

    // Main method to test Solution
    public static void main(String[] args) {
        int[] w = new int[] { 1, 3 };
        int pickIndexCalls = 5;

        // Create an instance of Solution
        Solution solution = new Solution(w);

        for (int i = 0; i < pickIndexCalls; i++) {
            System.out.println(solution.pickIndex());
        }
    }
}
