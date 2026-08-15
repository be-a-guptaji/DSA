/*
LeetCode Problem: https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/

Question: 1481. Least Number of Unique Integers after K Removals

Problem Statement: Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

Example 1:
Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.

Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.

Constraints:
    1 <= arr.length <= 10^5
    1 <= arr[i] <= 10^9
    0 <= k <= arr.length
*/

/*
Approach: Frequency Bucketing with Greedy Removal
Goal:
- Remove exactly k elements from arr to minimize
  the number of remaining unique integers.
Core Idea:
- To minimize unique integers, remove elements
  with the lowest frequencies first, as they cost
  the fewest removals to eliminate entirely.
- Rather than sorting the unique elements by
  frequency, bucket-count the frequencies
  themselves: freq_bucket[f] = number of unique
  integers that appear exactly f + 1 times.
- Reuse the original arr as the bucket array to
  avoid extra space allocation.
- Greedily sweep buckets from lowest frequency to
  highest, eliminating as many unique integers as
  possible within the k removal budget.
Algorithm Steps:
1. Build frequencyMap: element -> occurrence count.
2. Reset arr to zero, then populate frequency
   buckets: for each frequency f in frequencyMap,
   increment arr[f - 1] (number of uniques with
   frequency f).
3. Initialize result = frequencyMap.size() (total
   unique integers).
4. Sweep arr from index 0 upward (frequency 1, 2,
   ...):
   a. remove = arr[i] (unique integers at this
      frequency).
   b. Total cost to eliminate all of them =
      remove * (i + 1).
   c. If k >= remove * (i + 1):
      - Subtract the cost from k.
      - Subtract remove from result (all eliminated).
   d. Else:
      - Only k / (i + 1) uniques can be fully
        eliminated within remaining budget.
      - Subtract that count from result and stop.
5. Return result.
Why It Works:
- Greedy removal of lowest-frequency elements first
  is optimal: eliminating a frequency-1 element
  costs 1 removal and saves one unique; no other
  removal achieves a better unique-per-removal
  ratio.
- Bucketing avoids sorting unique elements
  individually, collapsing all elements of the same
  frequency into one group processed in O(1) per
  bucket.
Time Complexity:
- O(n)
where n is the length of arr. Frequency map
construction is O(n); bucket sweep is O(n) since
bucket indices are bounded by n.
Space Complexity:
- O(n)
for the frequency map. The bucket array reuses
the input arr in-place, adding no extra space
beyond O(1) variables.
Result:
- Returns the minimum number of unique integers
  remaining after exactly k removals.
*/

package StacksAndQueues.Easy;

import java.util.Arrays;
import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find the least number of unique integers after removing exactly k
  // elements
  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    // Initialize the hash map for the frequency
    HashMap<Integer, Integer> frequencyMap = new HashMap<>();

    // Fill the frequencyMap from the arr
    for (int i = 0; i < arr.length; i++) {
      frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);
    }

    // Reset the arr array to zero
    Arrays.fill(arr, 0);

    // Fill the arr frequency
    for (int freq : frequencyMap.values()) {
      arr[freq - 1]++;
    }

    // Initialize the result variable
    int result = frequencyMap.size();

    // Iterate over the arr array
    for (int i = 0; i < arr.length; i++) {
      // Get the remove frequency
      int remove = arr[i];

      // Update the result accordingly
      if (k > (remove * (i + 1))) {
        k -= (remove * (i + 1));
        result -= remove;
      } else {
        remove = k / (i + 1);
        result -= remove;
        break;
      }
    }

    // Return the result variable
    return result;
  }
}

// Main Class
public class _1481_Least_Number_of_Unique_Integers_after_K_Removals {
  // Main method to test findLeastNumOfUniqueInts
  public static void main(String[] args) {
    int[] arr = new int[] { 2, 1, 3, 5, 6 };
    int k = 5;

    int result = new Solution().findLeastNumOfUniqueInts(arr, k);

    System.out.println("The least number of unique integers after removing exactly " + k + " elements is : "
        + result);
  }
}
