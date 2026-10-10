/*
LeetCode Problem: https://leetcode.com/problems/detonate-the-maximum-bombs/

Question: 2101. Detonate the Maximum Bombs

Problem Statement: You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.

Example 1:
Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.

Example 2:
Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.

Example 3:
Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
Output: 5
Explanation:
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.

Constraints:
1 <= bombs.length <= 100
bombs[i].length == 3
1 <= xi, yi, ri <= 10^5
*/

/*
Approach: Directed Graph DFS with Chain Detonation Count
Goal:
- Find the maximum number of bombs that can be
  detonated by initially triggering exactly one
  bomb, where a bomb detonates another if the
  second lies within the first's blast radius.
Core Idea:
- Model detonation reachability as a directed
  graph: add edge i -> j if bomb i's blast radius
  covers bomb j's center.
- DFS from each bomb counts how many bombs are
  reachable (transitively detonatable) from it.
- The answer is the maximum count across all
  starting bombs.
Algorithm Steps:
1. Build directed adjacency list: for each pair
   (i, j), add i -> j if distance(i, j)^2 <=
   radius[i]^2 (integer arithmetic avoids sqrt).
2. For each bomb i, run DFS from i with a fresh
   visited array; count all reachable nodes.
3. Return the maximum count found.
Why It Works:
- Edge direction encodes asymmetric blast coverage:
  i may reach j without j reaching i.
- DFS naturally accumulates all transitively
  reachable bombs from the starting node.
- Using squared distances avoids floating-point
  precision issues from sqrt.
Time Complexity:
- O(n^2) for graph construction and O(n * (n + e))
  for n DFS calls, overall O(n^3) worst case.
Space Complexity:
- O(n^2) for the adjacency list and O(n) per DFS
  for the visited array and call stack.
Result:
- Returns the maximum number of bombs detonated
  from a single starting bomb.
*/

package Graphs.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Method to find the maximum number of bombs that can be detonated if you are
  // allowed to detonate only one bomb
  public int maximumDetonation(int[][] bombs) {
    // Initialize the array of arraylist
    ArrayList<Integer>[] adjList = new ArrayList[bombs.length];

    // Fill the adjList
    for (int i = 0; i < bombs.length; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Fill the array of arraylist
    for (int i = 0; i < bombs.length; i++) {
      for (int j = 0; j < bombs.length; j++) {
        // If i is not equal to j and can detonoate other bomb then add it to the list
        if (i != j && canDetonate(bombs[i], bombs[j])) {
          adjList[i].add(j);
        }
      }
    }

    // Initialize the result variable
    int result = 1;

    // Iterate over bombs
    for (int i = 0; i < bombs.length; i++) {
      // Update the result varaible
      result = Math.max(result, this.dfs(i, adjList, new boolean[bombs.length]));
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private int dfs(int node, ArrayList<Integer>[] adjList, boolean[] visited) {
    // Set the visited to true
    visited[node] = true;

    // Initialize the count variable
    int count = 1;

    // Iterate over the neighbor
    for (int neighbor : adjList[node]) {
      if (!visited[neighbor]) {
        count += this.dfs(neighbor, adjList, visited);
      }
    }

    // Return the count
    return count;
  }

  // Helper method to find if bomb detonates other
  private boolean canDetonate(int[] bomb1, int[] bomb2) {
    // Get the distance
    long dx = bomb1[0] - bomb2[0];
    long dy = bomb1[1] - bomb2[1];

    // Return the conditon
    return dx * dx + dy * dy <= (long) bomb1[2] * bomb1[2];
  }
}

public class _2101_Detonate_the_Maximum_Bombs {
  // Main method to test maximumDetonation
  public static void main(String[] args) {
    int[][] bombs = new int[][] { { 1, 2, 3 }, { 2, 3, 1 }, { 3, 4, 2 }, { 4, 5, 3 }, { 5, 6, 4 } };

    int result = new Solution().maximumDetonation(bombs);

    System.out
        .println("The maximum number of bombs that can be detonated if you are allowed to detonate only one bomb is : "
            + result);
  }
}
