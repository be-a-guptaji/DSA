/*
LeetCode Problem: https://leetcode.com/problems/sort-array-by-increasing-frequency/

Question: 1636. Sort Array by Increasing Frequency

Problem Statement: Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

Example 1:
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.

Example 2:
Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.

Example 3:
Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]

Constraints:
1 <= nums.length <= 100
-100 <= nums[i] <= 100
 */

/*
Approach: Frequency Counting + Min Heap (Custom Comparator)

Goal:
- Sort the array such that:
  1. Elements with lower frequency come first.
  2. If frequencies are equal, larger numbers come first.

Core Idea:
- Count frequency using a fixed-size array (since range is bounded).
- Use a min heap to sort based on:
    - frequency (ascending)
    - value (descending for ties)
- Extract elements from heap and rebuild array.

Algorithm Steps:
1. Initialize frequency array of size 201 (offset by +100).
2. Count frequency of each number.
3. Build a min heap with pairs (number, frequency):
   - Comparator:
       a. frequency ascending
       b. number descending (tie-breaker)
4. Extract from heap:
   - Insert each number into result array
     as many times as its frequency.
5. Return the modified array.

Time Complexity:
- O(n log k)
  n = size of nums, k = number of unique elements

Space Complexity:
- O(k)

Result:
- Returns array sorted by increasing frequency,
  and by decreasing value for equal frequencies.
*/

package Arrays.Easy;

import java.util.Arrays;
import java.util.PriorityQueue;

// Solution Class
class Solution {
    // Method to find the sorted the array in increasing order based on the
    // frequency of the values
    public int[] frequencySort(int[] nums) {
        // Initialize the bucket array for the frequency
        int[] frequency = new int[201];

        // Fill the frequency array
        for (int i = 0; i < nums.length; i++) {
            frequency[nums[i] + 100]++;
        }

        // Initialize the priority queue
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.frequency != b.frequency) {
                return a.frequency - b.frequency;
            } else {
                return b.number - a.number;
            }
        });

        // Make the minHeap from the frequency array
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0) {
                minHeap.offer(new Pair(i - 100, frequency[i]));
            }
        }

        // Initialize the index vairable
        int index = 0;

        // Fill the nums array
        while (!minHeap.isEmpty()) {
            // Get the pair
            Pair p = minHeap.poll();

            // Fill the pair frequency to the nums array
            while (p.frequency-- != 0) {
                nums[index++] = p.number;
            }
        }

        // Return the modified nums array
        return nums;
    }

    // Pair Class for the frequency and number
    private class Pair {
        // Initialize the variable for frequency and the number
        public int number;
        public int frequency;

        public Pair(int number, int frequency) {
            this.number = number;
            this.frequency = frequency;
        }
    }
}

public class _1636_Sort_Array_by_Increasing_Frequency {
    // Main method to test frequencySort
    public static void main(String[] args) {
        int[] nums = new int[] { 180, 165, 170 };

        int[] result = new Solution().frequencySort(nums);

        System.out.println("The sorted the array in increasing order based on the frequency of the values is : "
                + Arrays.toString(result));
    }
}
