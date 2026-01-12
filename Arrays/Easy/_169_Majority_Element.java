/*
LeetCode Problem: https://leetcode.com/problems/majority-element/

Question: 169. Majority Element

Problem Statement: Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:

n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9
The input is generated such that a majority element will exist in the array.

Follow-up: Could you solve the problem in linear time and in O(1) space?
*/

/*
Approach: Boyer–Moore Majority Vote Algorithm

Goal:
Find the element that appears more than ⌊n / 2⌋ times in the array.

Key Insight:
- The majority element occurs more times than all other elements combined.
- Pairing different elements cancels them out, leaving the majority element.

Algorithm:
1. Initialize:
   - candidate = 0
   - vote = 0
2. Traverse the array:
   - If vote == 0:
       • Set candidate = current element
       • vote = 1
   - Else:
       • Increment vote if current element == candidate
       • Decrement vote otherwise
3. After traversal, candidate holds the majority element.

Why It Works:
- Non-majority elements are canceled out through decrementing votes.
- The majority element survives because it appears more than half the time.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

import java.util.Arrays;

public class _169_Majority_Element {
    // Method to find the majority element in the array
    public static int majorityElement(int[] nums) {
        // Initialize the candidate and vote variable
        int candidate = 0, vote = 0;

        // Iterate over the nums array
        for (int num : nums) {
            // If vote become zero change the candidate and increase the vote else increase
            // the vote if candidate and num is same else decrement
            if (vote == 0) {
                candidate = num;
                vote++;
            } else {
                vote += (candidate == num) ? 1 : -1;
            }
        }

        // Retrun the candidate
        return candidate;
    }

    // Main method to test majorityElement
    public static void main(String[] args) {
        int[] nums = new int[] { 2, 2, 1, 1, 1, 2, 2 };

        int result = majorityElement(nums);

        System.out.println("The majority element in the array " + Arrays.toString(nums) + " is : " + result);
    }
}
