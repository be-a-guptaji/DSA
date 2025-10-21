/*
LeetCode Problem: https://leetcode.com/problems/delete-and-earn/

Question: 740. Delete and Earn

Problem Statement: You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.

Example 1:
Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.

Example 2:
Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.

Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i] <= 10^4
*/

/*
Approach: Transform the problem into the "House Robber" dynamic programming pattern.

1. We cannot take both a number `num` and `num ± 1` together. If we take `num`, we must delete all occurrences of `num - 1` and `num + 1`.
2. So, we group all occurrences of each number and sum their total contribution:
   - frequency[i] = total points gained by deleting all `i`s = i * count of i
3. This transforms the problem into a linear DP problem where:
   - At each number i, we can either:
       a) Take it → Add `frequency[i]` to the result of skipping i-1 (`dp[i] = dp[i-2] + frequency[i]`)
       b) Skip it → Take the result from i-1 (`dp[i] = dp[i-1]`)
4. We only keep track of the last two computed values (like House Robber).

Time Complexity: O(n + m), where n = number of elements in `nums`, m = maximum number in `nums`
Space Complexity: O(m), for the frequency array.
*/

package DynamicProgramming.Medium;

public class _740_Delete_and_Earn {
  // Method to find the most earning
  public static int deleteAndEarn(int[] nums) {
    // If nums length is one then return the nums first index
    if (nums.length == 1) {
      return nums[0];
    }

    // Initialize the max variable
    int max = 0;

    for (int num : nums) {
      max = Math.max(max, num);
    }

    // Make the frequency array
    int[] frequency = new int[max + 1];

    // Fill the frequency arrray
    for (int num : nums) {
      frequency[num]++;
    }

    // Initialize the dp variable
    int earn1 = frequency[0], earn2 = frequency[1], earn3 = Math.max(earn1, earn2);

    // Iterate over the frequency array
    for (int i = 2; i < max + 1; i++) {
      earn3 = Math.max(frequency[i] * i + earn1, earn2);
      earn1 = earn2;
      earn2 = earn3;
    }

    // Retrun the earn3
    return earn3;
  }

  // Main method to test deleteAndEarn
  public static void main(String[] args) {
    int[] nums = { 2, 2, 3, 3, 3, 4 };

    int result = deleteAndEarn(nums);

    System.out.println("The most earning is : " + result);
  }
}
