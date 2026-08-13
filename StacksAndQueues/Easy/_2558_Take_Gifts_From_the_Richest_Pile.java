/*
LeetCode Problem: https://leetcode.com/problems/take-gifts-from-the-richest-pile/

Question: 2558. Take Gifts From the Richest Pile

Problem Statement: You are given an integer array gifts denoting the number of gifts in various piles. Every second, you do the following:

    Choose the pile with the maximum number of gifts.
    If there is more than one pile with the maximum number of gifts, choose any.
    Reduce the number of gifts in the pile to the floor of the square root of the original number of gifts in the pile.

Return the number of gifts remaining after k seconds.

Example 1:
Input: gifts = [25,64,9,4,100], k = 4
Output: 29
Explanation: 
The gifts are taken in the following way:
- In the first second, the last pile is chosen and 10 gifts are left behind.
- Then the second pile is chosen and 8 gifts are left behind.
- After that the first pile is chosen and 5 gifts are left behind.
- Finally, the last pile is chosen again and 3 gifts are left behind.
The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.

Example 2:
Input: gifts = [1,1,1,1], k = 4
Output: 4
Explanation: 
In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile. 
That is, you can't take any pile with you. 
So, the total gifts remaining are 4.

Constraints:
    1 <= gifts.length <= 10^3
    1 <= gifts[i] <= 10^9
    1 <= k <= 10^3
*/

/*
Approach: Max-Heap with Repeated Square Root Replacement
Goal:
- After k seconds, each second replace the largest
  pile with its floor square root, then return the
  total remaining gifts.
Core Idea:
- Always replacing the current maximum is optimal
  since it reduces the largest pile as aggressively
  as possible each second.
- A max-heap gives O(log n) access to the maximum
  element, making each of the k replacements
  efficient.
Algorithm Steps:
1. Insert all gift pile values into a max-heap.
2. Repeat k times:
   - Poll the maximum value.
   - Push floor(sqrt(max)) back into the heap.
3. Sum all remaining values in the heap.
4. Return the sum.
Time Complexity:
- O((n + k) log n)
where n is the number of piles. Building the heap
is O(n log n), and each of the k replacement
operations costs O(log n).
Space Complexity:
- O(n)
for the max-heap storing all pile values.
Result:
- Returns the total number of gifts remaining after
  k seconds of optimal replacements.
*/

package StacksAndQueues.Easy;

import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the number of gifts remaining after k seconds
  public long pickGifts(int[] gifts, int k) {
    // Initialize the result varaible
    long result = 0;

    // Initialize the max heap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    // Add all element to the max heap
    for (int i = 0; i < gifts.length; i++) {
      maxHeap.offer(gifts[i]);
    }

    // Iterate over the max heap k times
    for (int i = 0; i < k; i++) {
      maxHeap.offer((int) Math.sqrt(maxHeap.poll()));
    }

    // Iterate over the max heap to add the value of heap
    while (!maxHeap.isEmpty()) {
      result += maxHeap.poll();
    }

    // Return the result
    return result;
  }
}

// Main Class
public class _2558_Take_Gifts_From_the_Richest_Pile {
  // Main method to test pickGifts
  public static void main(String[] args) {
    int[] gifts = new int[] { 25, 64, 9, 4, 100 };
    int k = 4;

    long result = new Solution().pickGifts(gifts, k);

    System.out.println("The number of gifts remaining after " + k + " seconds is : " + result);
  }
}
