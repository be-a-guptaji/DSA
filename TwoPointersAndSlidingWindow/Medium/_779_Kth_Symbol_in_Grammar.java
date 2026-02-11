/*
LeetCode Problem: https://leetcode.com/problems/k-th-symbol-in-grammar/

Question: 779. K-th Symbol in Grammar

Problem Statement: We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.

Example 1:
Input: n = 1, k = 1
Output: 0
Explanation: row 1: 0

Example 2:
Input: n = 2, k = 1
Output: 0
Explanation: 
row 1: 0
row 2: 01

Example 3:
Input: n = 2, k = 2
Output: 1
Explanation: 
row 1: 0
row 2: 01

Constraints:

1 <= n <= 30
1 <= k <= 2^(n - 1)
 */

/*
Approach: Binary Tree Interpretation with Range Halving

Problem Insight:
- Grammar starts with "0".
- Each row is formed by replacing:
    0 → 01
    1 → 10
- This forms a perfect binary tree where:
    - Left child keeps the same value
    - Right child flips the value

Key Observation:
- We do NOT need to build the string.
- Each row has length = 2^(n-1).
- The kth symbol depends on how many times we move to the “right half”,
  because every move to the right flips the bit.

Algorithm Explanation:

1. Initialization:
   - currentNode = 0 (root value at row 1).
   - Range [left, right) = [0, 2^(n-1)] represents the row segment.

2. Traverse Down the Tree (n−1 steps):
   - At each level, split the range into two halves.
   - mid = (left + right) / 2
   - If k lies in the left half:
       → value stays the same
   - If k lies in the right half:
       → flip the value (0 ↔ 1)
       → move to right half

3. After n−1 decisions:
   - currentNode holds the kth symbol.

Why This Works:
- Each level corresponds to one grammar expansion.
- Moving right means taking the flipped version.
- Total flips determine the final value.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Efficiently computes the kth grammar symbol without constructing strings.
*/

package TwoPointersAndSlidingWindow.Medium;

public class _779_Kth_Symbol_in_Grammar {
    // Method to find the kth symbol of the grammar
    public static int kthGrammar(int n, int k) {
        // Initialize the current node to zero
        int currentNode = 0;

        // Initialize the left and right pointer
        int left = 0, right = 1 << (n - 1);

        // Iterate over the range 1 to n - 1
        for (int i = 0; i < n - 1; i++) {
            // Get the middle index of the range
            int mid = (left + right) / 2;

            // Update the pointer according to the mid
            if (k <= mid) {
                right = mid;
            } else {
                left = mid + 1;
                currentNode = (currentNode == 0) ? 1 : 0;
            }
        }

        // Return the current node
        return currentNode;
    }

    // Main method to test kthGrammar
    public static void main(String[] args) {
        int n = 23;
        int k = 32333124;

        int result = kthGrammar(n, k);

        System.out.println("The kth symbol of the grammar is : " + result);
    }
}
