/*
LeetCode Problem: https://leetcode.com/problems/minimum-penalty-for-a-shop/

Question: 2483. Minimum Penalty for a Shop

Problem Statement: You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':

if the ith character is 'Y', it means that customers come at the ith hour
whereas 'N' indicates that no customers come at the ith hour.
If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:

For every hour when the shop is open and no customers come, the penalty increases by 1.
For every hour when the shop is closed and customers come, the penalty increases by 1.
Return the earliest hour at which the shop must be closed to incur a minimum penalty.

Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.

Example 1:
Input: customers = "YYNY"
Output: 2
Explanation: 
- Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 penalty.
- Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 penalty.
- Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 penalty.
- Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 penalty.
- Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 penalty.
Closing the shop at 2nd or 4th hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.

Example 2:
Input: customers = "NNNNN"
Output: 0
Explanation: It is best to close the shop at the 0th hour as no customers arrive.

Example 3:
Input: customers = "YYYY"
Output: 4
Explanation: It is best to close the shop at the 4th hour as customers arrive at each hour.

Constraints:
1 <= customers.length <= 10^5
customers consists only of characters 'Y' and 'N'.
*/

/*
Approach: Prefix Penalty Optimization (Greedy Scan)

Goal:
- Find the earliest closing hour that minimizes penalty.

Core Idea:
- Closing before a 'Y' customer causes penalty.
- Keeping shop open during an 'N' hour also causes penalty.
- Traverse the string while maintaining a running score:
   - 'Y' → beneficial to stay open (+1)
   - 'N' → beneficial to close earlier (-1)
- Track the position where score becomes maximum.

Algorithm Steps:
1. Initialize:
   - penalty = 0
   - minimumPenalty = 0
   - result = 0
2. Traverse customer array:
   - If 'Y' → penalty++
   - Else → penalty--
3. If penalty exceeds minimumPenalty:
   - Update minimumPenalty
   - Set result = current index + 1
4. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the earliest hour that minimizes penalty.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the earliest hour at which the shop must be closed to incur a
  // minimum penalty
  public int bestClosingTime(String customers) {
    // Convert the customers string into the character array
    char[] str = customers.toCharArray();

    // Initialize the result, penalty and the minimumPenalty variable
    int result = 0, penalty = 0, minimumPenalty = 0;

    // Iterate over the str array
    for (int i = 0; i < str.length; i++) {
      // Update the penalty
      penalty += str[i] == 'Y' ? 1 : -1;

      // If penalty is more than the minimumPenalty then update the minimumPenalty and
      // result variable
      if (penalty > minimumPenalty) {
        minimumPenalty = penalty;
        result = i + 1;
      }
    }

    // Return the result variable
    return result;
  }
}

public class _2483_Minimum_Penalty_for_a_Shop {
  // Main method to test bestClosingTime
  public static void main(String[] args) {
    String customers = "";

    int result = new Solution().bestClosingTime(customers);

    System.out.println("The earliest hour at which the shop must be closed to incur a minimum penalty is : " + result);
  }
}
