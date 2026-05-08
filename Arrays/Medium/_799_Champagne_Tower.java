/*
LeetCode Problem: https://leetcode.com/problems/champagne-tower/

Question: 799. Champagne Tower

Problem Statement: We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.  Each glass holds one cup of champagne.

Then, some champagne is poured into the first glass at the top.  When the topmost glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom row has its excess champagne fall on the floor.)

For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.

Now after pouring some non-negative integer cups of champagne, return how full the jth glass in the ith row is (both i and j are 0-indexed.)

Example 1:
Input: poured = 1, query_row = 1, query_glass = 1
Output: 0.00000
Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be no excess liquid so all the glasses under the top glass will remain empty.

Example 2:
Input: poured = 2, query_row = 1, query_glass = 1
Output: 0.50000
Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.

Example 3:
Input: poured = 100000009, query_row = 33, query_glass = 17
Output: 1.00000

Constraints:
0 <= poured <= 10^9
0 <= query_glass <= query_row < 100
*/

/*
Approach: Dynamic Programming (Row-by-Row Simulation)

Goal:
- Determine how much champagne is present in a specific glass
  after pouring champagne into the top glass.

Core Idea:
- Each glass can hold at most 1 unit.
- Excess champagne overflows equally to the two glasses below.
- Simulate the tower row by row using dynamic programming.

Algorithm Steps:
1. Initialize first row with poured amount.
2. For each row:
   - Create next row array.
   - For each glass:
       extra = currentGlass - 1
   - If extra > 0:
       distribute extra / 2 to both lower glasses.
3. Move to next row.
4. Return:
   min(value at query_glass, 1.0)

Time Complexity:
- O(query_row²)

Space Complexity:
- O(query_row)

Result:
- Returns amount of champagne in the target glass.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the champagne in the glass
  public double champagneTower(int poured, int query_row, int query_glass) {
    // Initialize the array for the dp
    double[] previousRow = new double[] { poured };

    // Iterate over the qurey row
    for (int row = 1; row <= query_row; row++) {
      // Initialize the currentRow variable
      double[] currentRow = new double[row + 1];

      // Fill the current glasses
      for (int glass = 0; glass < row; glass++) {
        // Get the current glass
        double extra = previousRow[glass] - 1.0;

        // If extra is present then update hte lower glasses
        if (extra > 0) {
          // Get the extra to 0.5
          extra *= 0.5;

          // Update the glasses
          currentRow[glass] += extra;
          currentRow[glass + 1] += extra;
        }
      }

      // Update the previousRow
      previousRow = currentRow;
    }

    // Return the query_glass
    return Math.min(previousRow[query_glass], 1.0);
  }
}

public class _799_Champagne_Tower {
  // Main method to test champagneTower
  public static void main(String[] args) {
    int poured = 100000009;
    int query_row = 33;
    int query_glass = 17;

    double result = new Solution().champagneTower(poured, query_row, query_glass);

    System.out.println("The champagne in the glass is : " + result);
  }
}
