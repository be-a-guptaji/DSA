/*
LeetCode Problem: https://leetcode.com/problems/number-of-pairs-of-interchangeable-rectangles/

Question: 2001. Number of Pairs of Interchangeable Rectangles

Problem Statement: You are given n rectangles represented by a 0-indexed 2D integer array rectangles, where rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.

Two rectangles i and j (i < j) are considered interchangeable if they have the same width-to-height ratio. More formally, two rectangles are interchangeable if widthi/heighti == widthj/heightj (using decimal division, not integer division).

Return the number of pairs of interchangeable rectangles in rectangles.

Example 1:
Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
Output: 6
Explanation: The following are the interchangeable pairs of rectangles by index (0-indexed):
- Rectangle 0 with rectangle 1: 4/8 == 3/6.
- Rectangle 0 with rectangle 2: 4/8 == 10/20.
- Rectangle 0 with rectangle 3: 4/8 == 15/30.
- Rectangle 1 with rectangle 2: 3/6 == 10/20.
- Rectangle 1 with rectangle 3: 3/6 == 15/30.
- Rectangle 2 with rectangle 3: 10/20 == 15/30.

Example 2:
Input: rectangles = [[4,5],[7,8]]
Output: 0
Explanation: There are no interchangeable pairs of rectangles.
 
Constraints:
n == rectangles.length
1 <= n <= 10^5
rectangles[i].length == 2
1 <= widthi, heighti <= 10^5
*/

/*
Approach: Ratio Frequency Counting with HashMap

Goal:
- Count pairs of rectangles that have the same width-to-height ratio.

Core Idea:
- Two rectangles are interchangeable if:
    width1 / height1 == width2 / height2
- For each rectangle:
   - Compute its ratio.
   - The number of new pairs formed equals the number of
     previous rectangles with the same ratio.

Algorithm Steps:
1. Initialize a HashMap:
   - key   = width / height ratio
   - value = frequency of that ratio
2. Traverse rectangles:
   - Compute ratio = width / height.
   - Add current frequency of ratio to result.
   - Increment frequency in map.
3. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns total number of interchangeable rectangle pairs.

Note:
- Using floating point ratios may introduce precision issues.
- A more robust approach uses reduced fractions:
    width / gcd(width, height), height / gcd(width, height)
*/

package Arrays.Medium;

import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find the number of pairs of interchangeable rectangles in
  // rectangles
  public long interchangeableRectangles(int[][] rectangles) {
    // Initialize the result variable
    long result = 0;

    // Initialize the hash map for the frequency of the interchangeable
    // rectangles
    HashMap<Double, Integer> interchangeableRectanglesMap = new HashMap<>();

    // Fill the hash map
    for (int i = 0; i < rectangles.length; i++) {
      // Get the ratio current width and height
      double ratio = (double) rectangles[i][0] / rectangles[i][1];

      // Get the frequency of the current ratio
      int freq = interchangeableRectanglesMap.getOrDefault(ratio, 0);

      // Update the hash map
      interchangeableRectanglesMap.put(ratio, freq + 1);

      // Update the result
      result += freq;
    }

    // Return the result
    return result;
  }
}

public class _2001_Number_of_Pairs_of_Interchangeable_Rectangles {
  // Main method to test interchangeableRectangles
  public static void main(String[] args) {
    int[][] rectangles = new int[][] { { 4, 8 }, { 3, 6 }, { 10, 20 }, { 15, 30 } };

    long result = new Solution().interchangeableRectangles(rectangles);

    System.out.println("The number of pairs of interchangeable rectangles in rectangles is : " + result);
  }
}
