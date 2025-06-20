/*
LeetCode Problem: https://leetcode.com/problems/two-sum/

Question: 1. Two Sum

Problem Statement: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.


Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */

/*
Approach: We can solve this problem using a HashMap to store the numbers and their indices.
We iterate through the array and check if the complement of the current number exists in the HashMap.
If it does, we return the indices of the current number and the complement.
If the complement does not exist, we add the current number to the HashMap with its index as the value.

Time Complexity: O(n), where n is the number of elements in the array.
Space Complexity: O(n), where n is the number of elements in the array.
 */

package Arrays.Easy;

public class _1_Two_Sum {

    // Method to find two indices of numbers adding up to target
    public static int[] twoSum(int[] nums, int target) {
        // Use a hashmap to store number and its index
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                // Found the pair
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }

        // If no solution found, return null or throw an exception
        return null;
    }

    // Main method to test twoSum
    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        int[] result = twoSum(nums, target);

        if (result != null) {
            System.out.println("Indices: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("No two sum solution found.");
        }
    }
}
