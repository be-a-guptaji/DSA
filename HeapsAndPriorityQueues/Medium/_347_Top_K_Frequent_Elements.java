/*
LeetCode Problem: https://leetcode.com/problems/top-k-frequent-elements/

Question: 347. Top K Frequent Elements

Problem Statement: Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Example 3:
Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
Output: [1,2]


Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

/*
Approach:
This solution uses the Bucket Sort technique to find the top k frequent elements in an integer array.
The key idea is to first count the frequency of each element using a HashMap, then use an array of lists (called "buckets") where the index represents the frequency.
Each bucket at index i holds a list of numbers that appear exactly i times in the input array.
Since the maximum possible frequency of any number is equal to the size of the array, we create size + 1 buckets to accommodate all frequencies.

After building the frequency buckets, we iterate from the end of the bucket array (highest frequency) to the beginning, collecting elements until we have found the top k most frequent elements.

This avoids the need for sorting or using a priority queue, making the solution efficient for large inputs.

Time Complexity: O(n), where n is the number of elements in the input array.
- Building the frequency map takes O(n).
- Filling the buckets also takes O(n).
- Collecting the top k elements can take up to O(n) in the worst case.
Space Complexity: O(n), for storing the frequency map and bucket structure.

*/

package HeapsAndPriorityQueues.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class _347_Top_K_Frequent_Elements {
    // Method to find the top k frequent element
    public static int[] topKFrequent(int[] nums, int k) {
        // Initialize the size of the nums array
        int size = nums.length;

        // Initialize the array for storing the top k frequent element
        int[] result = new int[k];

        // Initialize a list to store the frequency as the index of array
        ArrayList<ArrayList<Integer>> frequency = new ArrayList<>();

        // Initialize all list with the empty list
        for (int i = 0; i <= size; i++) {
            frequency.add(new ArrayList<>());
        }

        // Initialize a Hash map to store the frequency and the value of the element
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        // Store the frequency of the element in the frequency map
        for (int i = 0; i < size; i++) {
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
        }

        // Get the key value of the frequency map and store in the frequency map
        for (HashMap.Entry<Integer, Integer> en : frequencyMap.entrySet()) {
            // Get the key value of the map
            Integer key = en.getKey();
            Integer value = en.getValue();

            // Add the value in the frequency list
            frequency.get(value).add(key);
        }

        // Initialize the index for the tracking of the value of the result index
        int index = 0;

        // Collect the top k frequent elements from the buckets
        for (int i = size; i >= 0 && index < k; i--) {
            for (int num : frequency.get(i)) {
                if (index < k) {
                    result[index++] = num;
                } else {
                    break;
                }
            }
        }

        // Return the result array
        return result;
    }

    // Main method to test topKFrequent
    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int k = 2;

        int[] result = topKFrequent(nums, k);

        System.out.println("The top k frequent element are : " + Arrays.toString(result));
    }
}
