/*
LeetCode Problem: https://leetcode.com/problems/majority-element-ii/

Question: 229. Majority Element II

Problem Statement: Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:
Input: nums = [3,2,3]
Output: [3]

Example 2:
Input: nums = [1]
Output: [1]

Example 3:
Input: nums = [1,2]
Output: [1,2]

Constraints:
1 <= nums.length <= 5 * 10^4
-10^9 <= nums[i] <= 10^9

Follow up: Could you solve the problem in linear time and in O(1) space?
*/

/*
Approach: Boyer-Moore Voting Algorithm (Extended for n/3)

Goal:
- Find all elements that appear more than ⌊n/3⌋ times.

Core Idea:
- There can be at most 2 such elements.
- Maintain two candidates and their vote counts.
- First pass:
    - Identify potential candidates using vote cancellation.
- Second pass:
    - Verify actual frequencies.

Algorithm Steps:
1. Initialize:
   - candidate1, candidate2, vote1 = 0, vote2 = 0.
2. First pass:
   - If current == candidate1 → vote1++
   - Else if current == candidate2 → vote2++
   - Else if vote1 == 0 → candidate1 = current, vote1 = 1
   - Else if vote2 == 0 → candidate2 = current, vote2 = 1
   - Else → vote1--, vote2--
3. Reset vote counts.
4. Second pass:
   - Count occurrences of both candidates.
5. Add candidates to result if count > n/3.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns all elements appearing more than ⌊n/3⌋ times.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the majority element
  public List<Integer> majorityElement(int[] nums) {
    // Initialize the array list for the result
    ArrayList<Integer> res = new ArrayList<>();

    // Initialize the two candidate and vote for them
    int candidate1 = 0, candidate2 = 0, vote1 = 0, vote2 = 0;

    // Get the length of the array
    int length = nums.length;

    // Iterate over the nums array
    for (int i = 0; i < length; i++) {
      // Get the current element
      int currentElement = nums[i];

      // Update the variable
      if (currentElement == candidate1) {
        vote1++;
      } else if (currentElement == candidate2) {
        vote2++;
      } else if (vote1 == 0) {
        candidate1 = currentElement;
        vote1++;
      } else if (vote2 == 0) {
        candidate2 = currentElement;
        vote2++;
      } else {
        vote1--;
        vote2--;
      }
    }

    // Update the count array
    vote1 = vote2 = 0;

    // Check the votes
    for (int i = 0; i < length; i++) {
      // Get the current element
      int currentElement = nums[i];

      if (candidate1 == currentElement) {
        vote1++;
      } else if (candidate2 == currentElement) {
        vote2++;
      }
    }

    // If vote is more than the lenght/3 then add to the result array
    if (vote1 > length / 3) {
      res.add(candidate1);
    }

    if (vote2 > length / 3) {
      res.add(candidate2);
    }

    // Return the result array
    return res;
  }
}

public class _229_Majority_Element_II {
  // Main method to test majorityElement
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3, 4, 5 };

    List<Integer> result = new Solution().majorityElement(nums);

    System.out.println("The maximum profit you can achieve is : " + result);
  }
}
