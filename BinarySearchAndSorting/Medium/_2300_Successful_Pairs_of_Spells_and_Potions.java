/*
LeetCode Problem: https://leetcode.com/problems/successful-pairs-of-spells-and-potions/

Question: 2300. Successful Pairs of Spells and Potions

Problem Statement: You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.

You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.

Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.

Example 1:
Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
Output: [4,0,3]
Explanation:
- 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
Thus, [4,0,3] is returned.

Example 2:
Input: spells = [3,1,2], potions = [8,5,8], success = 16
Output: [2,0,2]
Explanation:
- 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
- 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful. 
- 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful. 
Thus, [2,0,2] is returned.

Constraints:

n == spells.length
m == potions.length
1 <= n, m <= 10^5
1 <= spells[i], potions[i] <= 10^5
1 <= success <= 10^10
 */

/*
Approach: Sorting + Binary Search

Goal:
- For each spell, determine how many potions form a successful pair
  such that spell * potion ≥ success.

Core Idea:
- Sort the potions array.
- For each spell, compute the minimum potion value required.
- Use binary search to find the first potion meeting this requirement.
- All potions to the right of that index are valid pairs.

Algorithm Steps:
1. Sort the potions array.
2. For each spell:
   - Compute target = ceil(success / spell).
3. Perform binary search on potions to find the first index
   where potion ≥ target.
4. Number of valid potions = potions.length - index.
5. Store the result for each spell.

Time Complexity:
- O(n log m)
  n = number of spells, m = number of potions.

Space Complexity:
- O(1) (excluding output array).

Result:
- Returns an array where each element represents the count
  of successful potion pairs for the corresponding spell.
*/

package BinarySearchAndSorting.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the number of potions that will form a successful pair
  public int[] successfulPairs(int[] spells, int[] potions, long success) {
    // Initialize the result variable for getting the result
    int[] result = new int[spells.length];

    // Sort the potions arrays
    Arrays.sort(potions);

    // Iterate over the spells array
    for (int index = 0; index < spells.length; index++) {
      // Get the target which we want in the potions array
      long target = (success + spells[index] - 1) / spells[index];

      // Initialize the left, right and positional variable
      int left = 0, right = potions.length - 1;

      // Iterate over the potions array
      while (left <= right) {
        // Get the middle index
        int mid = (left + right) >> 1;

        if (potions[mid] >= target) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }

      // Update the result
      result[index] = potions.length - left;
    }

    // Return the result
    return result;
  }
}

public class _2300_Successful_Pairs_of_Spells_and_Potions {
  // Main method to test successfulPairs
  public static void main(String[] args) {
    int[] spells = new int[] { 5, 1, 3 };
    int[] potions = new int[] { 1, 2, 3, 4, 5 };
    long success = 7;

    int[] result = new Solution().successfulPairs(spells, potions, success);

    System.out.println("The number of potions that will form a successful pair is : " + Arrays.toString(result));
  }
}
