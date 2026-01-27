/*
LeetCode Problem: https://leetcode.com/problems/sort-an-array/

Question: 912. Sort an Array

Problem Statement: Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.

Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).

Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessarily unique.

Constraints:

1 <= nums.length <= 5 * 10^4
-5 * 10^4 <= nums[i] <= 5 * 10^4
*/

/*
Approach: Heap Sort using Min-Heap

Idea:
Use a min-heap to always extract the smallest remaining element and rebuild
the array in sorted order.

Steps:
1. Insert all elements of the array into a PriorityQueue (min-heap).
2. Repeatedly remove the smallest element from the heap and place it back
   into the array from left to right.
3. The array becomes sorted in ascending order.

Why it works:
- A min-heap always gives the smallest element in O(log n) time.
- Extracting elements one by one guarantees sorted order.

Time Complexity:
- Building heap: O(n)
- Removing n elements: O(n log n)
- Overall: O(n log n)

Space Complexity:
- O(n) extra space for the heap.

Note:
This is not in-place sorting. Java’s Arrays.sort is more space-efficient,
but this approach is clear and reliable.
*/

package Arrays.Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _912_Sort_an_Array {
    // Method to find the sorted array
    public static int[] sortArray(int[] nums) {
        // Initialize the priority queue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Add all element to the min heap
        for (int num : nums) {
            minHeap.add(num);
        }

        // Iterate over the nums array to sort them
        for (int i = 0; i < nums.length; i++) {
            nums[i] = minHeap.remove();
        }

        // Return nums
        return nums;
    }

    // Main method to test sortArray
    public static void main(String[] args) {
        int[] nums = new int[] {};

        int[] result = sortArray(nums);

        System.out.println("The sorted array is: " + Arrays.toString(result));
    }
}
