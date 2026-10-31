/*
LeetCode Problem: https://leetcode.com/problems/filling-bookcase-shelves/

Question: 1105. Filling Bookcase Shelves

Problem Statement: You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book. You are also given an integer shelfWidth.

We want to place these books in order onto bookcase shelves that have a total width shelfWidth.

We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth, then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down. We repeat this process until there are no more books to place.

Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.

For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.

Example 1:
Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.

Example 2:
Input: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
Output: 4

Constraints:
1 <= books.length <= 1000
1 <= thicknessi <= shelfWidth <= 1000
1 <= heighti <= 1000
*/

/*
Approach: Top-down Memoized DP over Shelf Partitions
Goal:
- Place books in the given order onto shelves of
  fixed width, minimizing total bookshelf height.
Core Idea:
- dfs(index) = minimum total height to place books
  index..n-1 on shelves, given a fresh shelf
  starts at index.
- From index, extend the current shelf book by book
  while total width fits. Shelf height is the max
  height of its books. Try every valid end point i
  and add the best result for the remainder.
- Memoize per start index; each state depends only
  on later indices.
Algorithm Steps:
1. If index == n, return 0.
2. Return memo[index] if already computed.
3. Set currentWidth = shelfWidth, maxHeight = 0.
4. For i from index to n - 1:
   - Subtract books[i] width; break if negative.
   - maxHeight = max(maxHeight, books[i] height).
   - best = min(best, maxHeight + dfs(i + 1)).
5. Cache and return best.
Time Complexity:
- O(n^2)
n states, each scanning up to n books forward.
Space Complexity:
- O(n)
memo array plus recursion stack.
Result:
- Returns the minimum total height of the bookshelf.
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the minimum possible height that the total bookshelf can be
  // after placing shelves in this manner
  public int minHeightShelves(int[][] books, int shelfWidth) {
    // Initialize the memo array
    int[] memo = new int[books.length];

    // Initialize the memo with Integer.MIN_VALUE
    Arrays.fill(memo, Integer.MIN_VALUE);

    // Call the helper dfs method
    return this.dfs(0, books, shelfWidth, memo);
  }

  // Helper method for the dfs
  private int dfs(int index, int[][] books, int shelfWidth, int[] memo) {
    // If index is equal to books length then return zero
    if (index == books.length) {
      return 0;
    }

    // If value is in the cache then return that
    if (memo[index] != Integer.MIN_VALUE) {
      return memo[index];
    }

    // Initialize the memo[index] to Integer.MAX_VALUE
    memo[index] = Integer.MAX_VALUE;

    // Initialize the currentWidth and maxHeight
    int currentWidth = shelfWidth;
    int maxHeight = 0;

    // Iterate over the books
    for (int i = index; i < books.length; i++) {
      // Initialize the width and heigth
      int width = books[i][0];
      int height = books[i][1];

      // Update the currentWidth
      currentWidth -= width;

      // If currentWidth is less then 0 then break out of the loop
      if (currentWidth < 0) {
        break;
      }

      // Update the maxHeight
      maxHeight = Math.max(height, maxHeight);

      // Update the memo[index]
      memo[index] = Math.min(memo[index], this.dfs(i + 1, books, shelfWidth, memo) + maxHeight);
    }

    // Return the memo[index]
    return memo[index];
  }
}

public class _1105_Filling_Bookcase_Shelves {
  // Main method to test minHeightShelves
  public static void main(String[] args) {
    int[][] books = new int[][] { { 1, 1 }, { 2, 3 }, { 2, 3 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 2 } };
    int shelfWidth = 4;

    int result = new Solution().minHeightShelves(books, shelfWidth);

    System.out.println(
        "The minimum possible height that the total bookshelf can be after placing shelves in this manner is : "
            + result);
  }
}