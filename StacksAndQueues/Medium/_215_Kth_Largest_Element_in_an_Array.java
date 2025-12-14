/*
LeetCode Problem: https://leetcode.com/problems/kth-largest-element-in-an-array/

Question: 215. Kth Largest Element in an Array

Problem Statement: Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Constraints:

1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
 */

/*
Approach: Min Heap of Size K

To find the k-th largest element in an array efficiently, we maintain a min heap
that stores only the k largest elements encountered.

Algorithm:
- Initialize a min heap.
- Iterate through each number in the array:
  - Add the number to the heap.
  - If heap size exceeds k, remove the smallest element (heap root).
- After processing all numbers, the heap root represents the k-th largest element,
  because:
    - The heap contains exactly the k largest elements.
    - The smallest among them is the k-th largest overall.

Why This Works:
- Ensures we don't store unnecessary elements.
- Efficient compared to sorting the entire array.

Time Complexity: O(n log k)  
Space Complexity: O(k)
*/

package StacksAndQueues.Medium;

import java.util.PriorityQueue;

public class _215_Kth_Largest_Element_in_an_Array {
    // Method to find the kth largest element in the array
    public static int findKthLargest(int[] nums, int k) {
        // Initialize the min heap to find the kth largest number in the array
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all the elements of the array to the nums
        for (int num : nums) {
            // Add num to the min heap
            minHeap.offer(num);

            // If the size of min heap is greater than the k then remove the top element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Retrun the top element of the min heap
        return minHeap.peek();
    }

    // Main method to test findKthLargest
    public static void main(String[] args) {
        int[] nums = new int[] { 3, 2, 1, 5, 6, 4 };
        int k = 2;

        int result = findKthLargest(nums, k);

        System.out.println("The " + k + "th largest element in the array is : " + result);
    }
}
