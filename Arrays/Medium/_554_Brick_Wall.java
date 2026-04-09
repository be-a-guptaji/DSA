/*
LeetCode Problem: https://leetcode.com/problems/brick-wall/

Question: 554. Brick Wall

Problem Statement: There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the same.

Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick, then the brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after drawing such a vertical line.

Example 1:
Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
Output: 2

Example 2:
Input: wall = [[1],[1],[1]]
Output: 3

Constraints:
n == wall.length
1 <= n <= 10^4
1 <= wall[i].length <= 10^4
1 <= sum(wall[i].length) <= 2 * 10^4
sum(wall[i]) is the same for each row i.
1 <= wall[i][j] <= 2^31 - 1
 */

/*
Approach: Prefix Sum + HashMap (Edge Alignment Counting)

Goal:
- Find the minimum number of bricks crossed by a vertical line.

Core Idea:
- A vertical line crosses fewer bricks if it passes through
  the edges (gaps) between bricks.
- Count how many times each edge position occurs across rows.
- The best position is the one with maximum aligned edges.
- Result = total rows - max aligned edges.

Algorithm Steps:
1. Initialize a HashMap to store edge frequency.
2. For each row:
   - Compute prefix sum (exclude last brick to avoid right boundary).
   - Increment frequency of each edge position.
3. Track maximum frequency of any edge.
4. Return:
   wall.size() - maxFrequency

Time Complexity:
- O(n * m)
  n = number of rows, m = average bricks per row

Space Complexity:
- O(n)

Result:
- Returns minimum number of bricks crossed by the vertical line.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the minimum number of crossed bricks after drawing such a
  // vertical line
  public int leastBricks(List<List<Integer>> wall) {
    // Initialize the hash map for count
    HashMap<Integer, Integer> map = new HashMap<>();

    // Initialize the maxGap
    int maxGap = 0;

    // Get the frequncy sum for the each row
    for (int i = 0; i < wall.size(); i++) {
      // Get the current list
      List<Integer> w = wall.get(i);

      // Initialize the sum variable
      int sum = 0;

      // Find the prefix sum of the current row
      for (int j = 0; j < w.size() - 1; j++) {
        // Update the sum
        sum += w.get(j);

        // Initialize the gap variable
        int gap;

        // Update map frequncy count of sum index
        map.put(sum, gap = map.getOrDefault(sum, 0) + 1);

        // Update the max count
        maxGap = Math.max(gap, maxGap);
      }
    }

    // Return the maxGap by subtracting it to wall size
    return wall.size() - maxGap;
  }
}

public class _554_Brick_Wall {
  // Main method to test leastBricks
  public static void main(String[] args) {
    List<List<Integer>> wall = new ArrayList<>();

    int result = new Solution().leastBricks(wall);

    System.out.println("The minimum number of crossed bricks after drawing such a vertical line is : " + result);
  }
}
