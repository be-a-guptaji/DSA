/*
LeetCode Problem: https://leetcode.com/problems/count-number-of-bad-pairs/

Question: 2364. Count Number of Bad Pairs

Problem Statement: You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].

Return the total number of bad pairs in nums.

Example 1:
Input: nums = [4,1,3,3]
Output: 5
Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
There are a total of 5 bad pairs, so we return 5.

Example 2:
Input: nums = [1,2,3,4,5]
Output: 0
Explanation: There are no bad pairs.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/

/*
Approach: HashMap + Mathematical Transformation

Goal:
- Count the total number of bad pairs in nums.

Core Idea:
- A pair (i, j) is good if:
    j - i == nums[j] - nums[i]
- Rearranging:
    nums[i] - i == nums[j] - j
- Therefore:
   - Pairs with same value of (nums[i] - i)
     are good pairs.
- Count good pairs using frequency map.
- Bad pairs =
    total pairs - good pairs

Algorithm Steps:
1. Initialize HashMap for:
   key = nums[i] - i
   value = frequency
2. Traverse nums:
   - Compute key.
   - Existing frequency contributes to goodPairs.
   - Increment frequency in map.
   - Add i to totalPairs
     (number of previous indices)
3. Return:
   totalPairs - goodPairs

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns total number of bad pairs.
*/

package Arrays.Medium;

import java.util.HashMap;

// Solution Class
class Solution {
    // Method to find the total number of bad pairs in nums
    public long countBadPairs(int[] nums) {
        // Initialize the hash map for counting the same numbers
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        // Get the total number of pairs
        long totalPairs = 0, goodPairs = 0;

        // Update the hash map to the frequency of the numbers
        for (int i = 0; i < nums.length; i++) {
            // Get the key
            int key = nums[i] - i;

            // Get the key current value
            int currentValue = frequencyMap.getOrDefault(key, 0);

            // Update the goodPair
            goodPairs += currentValue;

            // Update the hash map
            frequencyMap.put(key, currentValue + 1);

            // Update the totalParis
            totalPairs += i;
        }

        // Return the badPairs
        return totalPairs - goodPairs;
    }
}

public class _2364_Count_Number_of_Bad_Pairs {
    // Main method to test countBadPairs
    public static void main(String[] args) {
        int[] nums = new int[] { 0, 1, 1, 1, 1, 1, 0, 0, 0 };

        long result = new Solution().countBadPairs(nums);

        System.out.println("The total number of bad pairs in nums is : " + result);
    }
}
