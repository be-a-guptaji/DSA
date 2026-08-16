/*
LeetCode Problem: https://leetcode.com/problems/furthest-building-you-can-reach/

Question: 1642. Furthest Building You Can Reach

Problem Statement: You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.

You start your journey from building 0 and move to the next building by possibly using bricks or ladders.

While moving from building i to building i+1 (0-indexed),

    If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
    If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.

Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.

Example 1:
Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4
Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.

Example 2:
Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
Output: 7

Example 3:
Input: heights = [14,3,19,3], bricks = 17, ladders = 0
Output: 3

Constraints:
    1 <= heights.length <= 10^5
    1 <= heights[i] <= 10^6
    0 <= bricks <= 10^9
    0 <= ladders <= heights.length
*/

/*
Approach: Greedy Brick-first with Max-Heap Ladder Substitution
Goal:
- Find the furthest building index reachable using
  bricks and ladders optimally, where ladders cover
  any climb for free and bricks cover a climb equal
  to their count.
Core Idea:
- Ladders should cover the largest climbs since they
  have no cost limit; bricks should cover smaller
  climbs.
- Rather than deciding upfront which climbs get
  ladders, greedily pay every positive climb with
  bricks first, tracking all climbs in a max-heap.
- Whenever bricks go negative (budget exceeded),
  retroactively assign a ladder to the largest
  brick-paid climb so far (top of max-heap),
  recovering those bricks.
- If no ladders remain when bricks go negative,
  the current building is unreachable.
Algorithm Steps:
1. Initialize a max-heap to track positive climb
   sizes paid with bricks.
2. For each consecutive pair of buildings i-1 to i:
   a. Compute difference = heights[i] - heights[i-1].
   b. If difference <= 0, no resource needed, continue.
   c. Subtract difference from bricks and push
      difference onto the max-heap.
   d. If bricks < 0 (over budget):
      - If ladders == 0, return i - 1 (can't reach
        building i).
      - Otherwise decrement ladders and add the
        max-heap's top back to bricks (replace the
        largest brick climb with a ladder).
3. If the loop completes, return heights.length - 1
   (all buildings reachable).
Why It Works:
- Paying bricks first and substituting a ladder
  retroactively for the largest climb is optimal:
  ladders eliminate cost entirely, so they should
  always be assigned to the highest-cost climbs
  seen so far, which the max-heap tracks.
- This lazy assignment strategy defers the ladder
  decision until a budget violation forces it,
  naturally aligning ladders with the largest
  climbs without lookahead.
Time Complexity:
- O(n log l)
where n is the number of buildings and l is the
number of ladders. The heap size is bounded by l
since each ladder use removes one element.
Space Complexity:
- O(l)
for the max-heap, bounded by the number of ladders.
Result:
- Returns the index of the furthest reachable
  building given optimal use of bricks and ladders.
*/

package StacksAndQueues.Medium;

import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the furthest building index (0-indexed) you can reach if you
  // use the given ladders and bricks optimally
  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    // Initialize the max heap for the bricks
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    // Iterate over the heights array
    for (int i = 1; i < heights.length; i++) {
      // Get the difference of the adjacent height
      int difference = heights[i] - heights[i - 1];

      // If difference is less than equal to zero then increment the index and skip
      // the iteration
      if (difference <= 0) {
        continue;
      }

      // Initially use the bricks for the climb
      bricks -= difference;
      maxHeap.offer(difference);

      // If bricks went negative, use a ladder to recover
      // the largest climb paid for with bricks.
      if (bricks < 0) {
        if (ladders == 0) {
          return i - 1;
        }

        ladders--;
        bricks += maxHeap.poll();
      }
    }

    // Return the heights.length - 1
    return heights.length - 1;
  }
}

// Main Class
public class _1642_Furthest_Building_You_Can_Reach {
  // Main method to test furthestBuilding
  public static void main(String[] args) {
    int[] heights = new int[] { 2, 1, 3, 5, 6 };
    int bricks = 5;
    int ladders = 5;

    int result = new Solution().furthestBuilding(heights, bricks, ladders);

    System.out.println(
        "The furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally is : "
            + result);
  }
}
