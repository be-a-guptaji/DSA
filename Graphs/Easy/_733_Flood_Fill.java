/*
LeetCode Problem: https://leetcode.com/problems/flood-fill/

Question: 733. Flood Fill

Problem Statement: You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.

Example 1:
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation:
From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.

Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.

Example 2:
Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]
Explanation:
The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.

Constraints:
m == image.length
n == image[i].length
1 <= m, n <= 50
0 <= image[i][j], color < 2^16
0 <= sr < m
0 <= sc < n
*/

/*
Approach: Recursive DFS Flood Fill with Target Color Guard
Goal:
- Replace all cells connected to (sr, sc) that
  share the same original color with the new color,
  using 4-directional connectivity.
Core Idea:
- DFS from the starting cell, recoloring each
  visited cell and recursing into its four
  neighbors if they share the original target color.
- Guard against infinite recursion when the new
  color equals the original color by terminating
  early if the cell has already been recolored
  (image[sr][sc] == color).
Algorithm Steps:
1. Record target = image[sr][sc] (original color).
2. Call dfs(image, sr, sc, color, target).
3. In dfs(sr, sc, color, target):
   a. Return if out of bounds, cell color != target,
      or cell color already == color (already filled
      or no-op guard).
   b. Set image[sr][sc] = color.
   c. Recurse in all four directions.
4. Return the modified image.
Why It Works:
- Checking image[sr][sc] != target ensures only
  connected cells of the original color are filled.
- Checking image[sr][sc] == color before recursing
  prevents revisiting already-filled cells, which
  also handles the edge case where color == target
  (avoids infinite recursion without a separate
  visited array).
Time Complexity:
- O(m * n)
where m and n are the image dimensions, since each
cell is visited at most once.
Space Complexity:
- O(m * n)
for the recursive call stack in the worst case of
a fully connected same-color image.
Result:
- Returns the image with the flood fill applied
  in-place.
*/

package Graphs.Easy;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the modified image after performing the flood fill
  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    this.dfs(image, sr, sc, color, image[sr][sc]);
    return image;
  }

  // Helper method for the dfs
  private void dfs(int[][] image, int sr, int sc, int color, int target) {
    // Return if out of bound
    if (sr < 0 || sr > image.length - 1 || sc < 0 || sc > image[0].length - 1 || image[sr][sc] != target
        || image[sr][sc] == color) {
      return;
    }

    // Set the new color
    image[sr][sc] = color;

    // Call the recursive dfs method
    this.dfs(image, sr + 1, sc, color, target);
    this.dfs(image, sr - 1, sc, color, target);
    this.dfs(image, sr, sc + 1, color, target);
    this.dfs(image, sr, sc - 1, color, target);
  }
}

public class _733_Flood_Fill {
  // Main method to test floodFill
  public static void main(String[] args) {
    int[][] image = new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
    int sr = 1;
    int sc = 1;
    int color = 2;

    int[][] result = new Solution1().floodFill(image, sr, sc, color);

    System.out.println("The modified image after performing the flood fill is : " + Arrays.deepToString(result));
  }
}
