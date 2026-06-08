/*
LeetCode Problem: https://leetcode.com/problems/find-k-closest-elements/

Question: 658. Find K Closest Elements

Problem Statement: Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b

Example 1:
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]

Example 2:
Input: arr = [1,1,2,3,4,5], k = 4, x = -1
Output: [1,1,2,3]

Constraints:
1 <= k <= arr.length
1 <= arr.length <= 10^4
arr is sorted in ascending order.
-10^4 <= arr[i], x <= 10^4
*/

/*
Approach: Binary Search on Window Start

Goal:
- Find the k elements closest to x in a sorted array.

Core Idea:
- The answer is always a contiguous window of size k.
- Instead of choosing elements individually,
  binary search the starting index of the window.
- For a candidate window starting at mid:

      [mid, mid + k - 1]

  compare:
      x - arr[mid]
  and
      arr[mid + k] - x

- If the left side is farther,
  shift the window right.
- Otherwise,
  keep the window on the left.

Algorithm Steps:
1. Search range for window start:
      0 ... n - k
2. While left < right:
   - mid = (left + right) / 2
   - Compare distances:
         x - arr[mid]
         arr[mid + k] - x
   - If left endpoint is farther:
         left = mid + 1
     Else:
         right = mid
3. After binary search:
   - left is the optimal window start.
4. Collect k elements:
      arr[left ... left + k - 1]
5. Return the list.

Why It Works:
- Since arr is sorted, the optimal window of
  size k is contiguous.
- The distance comparison determines which side
  of two adjacent windows is better.
- This monotonic property allows binary search.

Time Complexity:
- O(log(n - k) + k)

Space Complexity:
- O(k)
  for the output list

Result:
- Returns the k closest elements to x
  in ascending order.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the k closest integers to x in the array
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    // Initialize the left and right pointer
    int left = 0, right = arr.length - k;

    // Search the window
    while (left < right) {
      // Initialize the mid variable
      int mid = (left + right) >> 1;

      // Update the pointers
      if (x - arr[mid] > arr[mid + k] - x) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    // Initialize the arraylist for the result
    ArrayList<Integer> result = new ArrayList<>();

    // Fill the array list
    for (int i = left; i < left + k; i++) {
      result.add(arr[i]);
    }

    // Return the result list
    return result;
  }
}

public class _658_Find_K_Closest_Elements {
  // Main method to test findClosestElements
  public static void main(String[] args) {
    int[] arr = new int[] { 1, 2, 3, 4, 5 };
    int k = 4;
    int x = 3;

    List<Integer> result = new Solution().findClosestElements(arr, k, x);

    System.out.println("The k closest integers to x in the array is : " + result);
  }
}
