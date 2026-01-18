/*
LeetCode Problem: https://leetcode.com/problems/number-of-good-pairs/

Question: 1512. Number of Good Pairs

Problem Statement: Given an array of integers nums, return the number of good pairs.

A pair (i, j) is called good if nums[i] == nums[j] and i < j.

Example 1:
Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.

Example 2:
Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.

Example 3:
Input: nums = [1,2,3]
Output: 0

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
*/

/*
Approach: Frequency Counting + Combinatorics

Goal:
Count the number of good pairs (i, j) such that:
- nums[i] == nums[j]
- i < j

Key Idea:
For each distinct value that appears k times, the number of valid pairs
is the number of ways to choose 2 elements from k:
  C(k, 2) = k × (k − 1) / 2

Algorithm:
1. Create a frequency array of size 101 to count occurrences of each number.
2. Traverse nums and fill the frequency array.
3. For each value with frequency > 1:
   - Add C(frequency, 2) to the total pairs.
4. Return the total count.

Why It Works:
- Each pair of identical values contributes exactly one good pair.
- Combinatorics avoids nested loops.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1512_Number_of_Good_Pairs {
    // Method to find the number of good pairs
    public static int numIdenticalPairs(int[] nums) {
        // Initialize the frequency array of size 101
        int[] frequency = new int[101];

        // Iterate over the nums array for filling the frequecy array
        for (int num : nums) {
            frequency[num]++;
        }

        // Initialize a variable for storing the number of pairs
        int pairs = 0;

        // Iterate over the frequecy array for finding out the pairs
        for (int i = 0; i < 101; i++) {
            if (frequency[i] > 1) {
                pairs += (frequency[i] * (frequency[i] - 1) / 2);
            }
        }

        // Return the paris
        return pairs;
    }

    // Main method to test numIdenticalPairs
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 1, 1, 3 };

        int result = numIdenticalPairs(nums);

        System.out.println("The number of good pairs is : " + result);
    }
}
