/*
LeetCode Problem: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/

Question: 632. Smallest Range Covering Elements from K Lists

Problem Statement: You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.

Example 1:
Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Example 2:
Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]

Constraints:
    nums.length == k
    1 <= k <= 3500
    1 <= nums[i].length <= 50
    -10^5 <= nums[i][j] <= 10^5
    nums[i] is sorted in non-decreasing order.
*/

/*
Approach: K-Way Merge + Min Heap

Goal:
- Find the smallest range [a, b] such that
  the range contains at least one number from
  every list.

Core Idea:
- Maintain one selected element from each list.
- The current range is determined by:
      minimum selected value
      maximum selected value
- Use a min heap to efficiently obtain the
  current minimum.
- Track the current maximum separately.
- Repeatedly advance the list contributing the
  minimum value.

Data Structure:
---------------
Min Heap stores:

    { value, listIndex, elementIndex }

where:
- value        = current number
- listIndex    = source list
- elementIndex = position inside that list

Invariant:
----------
The heap always contains exactly one element
from each list.

Algorithm Steps:
1. Insert the first element of every list into
   the min heap.
2. Track:

      currentMax

   among these elements.
3. Initial candidate range:

      [heapMin, currentMax]

4. Repeatedly:
   a. Extract the minimum element.
   b. Current range becomes:

         [minValue, currentMax]

   c. Update the answer if:
      - range is smaller, or
      - same size but smaller left endpoint.
   d. Advance to the next element in the same list.
   e. Insert the next element into the heap.
   f. Update currentMax if needed.
5. Stop when any list is exhausted because it is
   no longer possible to include all lists.

Why It Works:
- At every step, the current minimum is the only
  value that can potentially be improved.
- Moving any other pointer cannot shrink the
  current range's left boundary.
- Therefore advancing the list that owns the
  minimum value explores all optimal candidates.

Time Complexity:
- Let:
      k = number of lists
      N = total elements across all lists

- Each element enters and leaves the heap once.

      O(N log k)

Space Complexity:
- O(k)

for the heap.

Result:
- Returns the smallest range containing at least
  one element from every list.
*/

package TwoPointersAndSlidingWindow.Hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the smallest range that includes at least one number from each
  // of the k lists
  public int[] smallestRange(List<List<Integer>> nums) {
    // Initialize the length variable
    final int length = nums.size();

    // Initialize the currentMax variable
    int currentMax = Integer.MIN_VALUE;

    // Initialize the minHeap for storing { Number, List Index, Pointer Index }
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

    // Push the first element of every list into the heap
    for (int i = 0; i < length; i++) {
      // Get the current number
      int num = nums.get(i).get(0);

      // Update the currentMax
      currentMax = Math.max(currentMax, num);

      // Push the value into the minHeap
      minHeap.offer(new int[] { num, i, 0 });
    }

    // Initialize the range variable
    int[] range = new int[] { minHeap.peek()[0], currentMax };

    // Iterate until one of the lists gets exhausted
    while (true) {
      // Get the minimum element from the heap
      int[] arr = minHeap.poll();

      // Get the num, listIndex and pointerIndex variables
      int num = arr[0];
      int listIndex = arr[1];
      int pointerIndex = arr[2];

      // If the current range is smaller then update the range
      if (currentMax - num < range[1] - range[0] || (currentMax - num == range[1] - range[0] && num < range[0])) {
        range[0] = num;
        range[1] = currentMax;
      }

      // Move the pointer to the next position
      pointerIndex++;

      // If pointerIndex is out of bound then break out of the loop
      if (pointerIndex == nums.get(listIndex).size()) {
        break;
      }

      // Get the next number of the list
      int nextNum = nums.get(listIndex).get(pointerIndex);

      // Push the new value into the minHeap
      minHeap.offer(new int[] { nextNum, listIndex, pointerIndex });

      // Update the currentMax
      currentMax = Math.max(currentMax, nextNum);
    }

    // Return the range
    return range;
  }
}

public class _632_Smallest_Range_Covering_Elements_from_K_Lists {
  // Main method to test smallestRange
  public static void main(String[] args) {
    List<List<Integer>> nums = new LinkedList<>();

    nums.add(new LinkedList<>(Arrays.asList(4, 10, 15, 24, 26)));
    nums.add(new LinkedList<>(Arrays.asList(0, 9, 12, 20)));
    nums.add(new LinkedList<>(Arrays.asList(5, 18, 22, 30)));

    int[] result = new Solution().smallestRange(nums);

    System.out.println("The smallest range that includes at least one number from each of the k lists is : "
        + Arrays.toString(result));
  }
}
