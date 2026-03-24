/*
LeetCode Problem: https://leetcode.com/problems/most-beautiful-item-for-each-query/

Question: 2070. Most Beautiful Item for Each Query

Problem Statement: You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.

You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the maximum beauty of an item whose price is less than or equal to queries[j]. If no such item exists, then the answer to this query is 0.

Return an array answer of the same length as queries where answer[j] is the answer to the jth query.

Example 1:
Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
Output: [2,4,5,5,6,6]
Explanation:
- For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
- For queries[1]=2, the items which can be considered are [1,2] and [2,4]. 
  The maximum beauty among them is 4.
- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
  The maximum beauty among them is 5.
- For queries[4]=5 and queries[5]=6, all items can be considered.
  Hence, the answer for them is the maximum beauty of all items, i.e., 6.

Example 2:
Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
Output: [4]
Explanation: 
The price of every item is equal to 1, so we choose the item with the maximum beauty 4. 
Note that multiple items can have the same price and/or beauty.  

Example 3:
Input: items = [[10,1000]], queries = [5]
Output: [0]
Explanation:
No item has a price less than or equal to 5, so no item can be chosen.
Hence, the answer to the query is 0.

Constraints:

1 <= items.length, queries.length <= 10^5
items[i].length == 2
1 <= pricei, beautyi, queries[j] <= 10^9
 */

/*
Approach: Sorting + Prefix Maximum + Binary Search

Goal:
- For each query, find the maximum beauty of an item
  whose price is ≤ query value.

Core Idea:
- Sort items by price.
- Convert beauty values into prefix maximums so that
  items[i][1] stores the best beauty up to that price.
- For each query, use binary search to find the rightmost
  item with price ≤ query.

Algorithm Steps:
1. Sort items based on price.
2. Build prefix maximum:
   - items[i][1] = max(items[i][1], items[i-1][1]).
3. For each query:
   - Perform binary search to find the largest index
     where items[index][0] ≤ query.
   - Use prefix max to get the answer.
4. Store results in answer array.

Time Complexity:
- O(n log n + q log n)
  n = number of items, q = number of queries

Space Complexity:
- O(1) (excluding output array)

Result:
- Returns an array where each element represents
  the maximum beauty for the corresponding query.
*/

package BinarySearchAndSorting.Medium;

import java.util.Arrays;
import java.util.Comparator;

// Solution Class
class Solution {
  // Method to find the maximum beauty of an item whose price is less than or
  // equal to queries[j]
  public int[] maximumBeauty(int[][] items, int[] queries) {
    // Initialize the result array
    int[] answer = new int[queries.length];

    // Sort the items array
    Arrays.sort(items, Comparator.comparingInt(a -> a[0]));

    // Fix the maxBeauty array
    int maxBeauty = 0;

    // Update the items array
    for (int i = 0; i < items.length; i++) {
      maxBeauty = Math.max(maxBeauty, items[i][1]);
      items[i][1] = maxBeauty;
    }

    // Iterate over the queries array
    for (int i = 0; i < queries.length; i++) {
      // Get the target
      int target = queries[i];

      // Initialize the left, right and positional variable
      int left = 0, right = items.length - 1, result = 0;

      // Logic to find the first index
      while (left <= right) {
        // Find the mid value
        int mid = left + (right - left) / 2;

        // Update the left, right and index accordingly
        if (items[mid][0] <= target) {
          result = items[mid][1];
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }

      // Update the answer
      answer[i] = result;
    }

    // Return the answer array
    return answer;
  }
}

public class _2070_Most_Beautiful_Item_for_Each_Query {
  // Main method to test maximumBeauty
  public static void main(String[] args) {
    int[][] items = new int[][] { { 1, 2 }, { 3, 2 }, { 2, 4 }, { 5, 6 }, { 3, 5 } };
    int[] queries = new int[] { 1, 2, 3, 4, 5, 6 };

    int[] result = new Solution().maximumBeauty(items, queries);

    System.out.println("The maximum beauty of queries array is : " + Arrays.toString(result));
  }
}
