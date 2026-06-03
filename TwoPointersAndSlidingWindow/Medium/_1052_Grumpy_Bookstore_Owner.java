/*
LeetCode Problem: https://leetcode.com/problems/grumpy-bookstore-owner/

Question: 1052. Grumpy Bookstore Owner

Problem Statement: There is a bookstore owner that has a store open for n minutes. You are given an integer array customers of length n where customers[i] is the number of the customers that enter the store at the start of the ith minute and all those customers leave after the end of that minute.

During certain minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.

When the bookstore owner is grumpy, the customers entering during that minute are not satisfied. Otherwise, they are satisfied.

The bookstore owner knows a secret technique to remain not grumpy for minutes consecutive minutes, but this technique can only be used once.

Return the maximum number of customers that can be satisfied throughout the day.

Example 1:
Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
Output: 16
Explanation:
The bookstore owner keeps themselves not grumpy for the last 3 minutes.
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

Example 2:
Input: customers = [1], grumpy = [0], minutes = 1
Output: 1

Constraints:
n == customers.length == grumpy.length
1 <= minutes <= n <= 2 * 10^4
0 <= customers[i] <= 1000
grumpy[i] is either 0 or 1.
*/

/*
Approach: Sliding Window + Baseline Contribution

Goal:
- Maximize the number of satisfied customers by using
  the owner's secret technique for exactly 'minutes'
  consecutive minutes.

Core Idea:
1. Customers during non-grumpy minutes are always satisfied.
   Compute this as the baseline satisfaction.
2. For grumpy minutes:
   - The technique can convert dissatisfied customers
     into satisfied customers.
   - Find the window of length 'minutes' that provides
     the maximum additional satisfaction.
3. Answer:
      baselineSatisfaction + maximumGain

Algorithm Steps:
1. Compute baseline satisfaction:
   - Sum customers[i] where grumpy[i] == 0.
2. Build first window gain:
   - Sum customers[i] where grumpy[i] == 1
     inside the first 'minutes' positions.
3. Slide the window:
   - Add entering contribution:
       customers[i] * grumpy[i]
   - Remove leaving contribution:
       customers[i-minutes] * grumpy[i-minutes]
4. Track maximum gain.
5. Return:
      baselineSatisfaction + maximumGain

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the maximum number of satisfied customers.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the maximum number of customers that can be satisfied
  // throughout the day
  public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
    // Initialize the base satisfaction
    int baseSatisfaction = 0;

    // Calculate the customers who are already satisfied
    for (int i = 0; i < customers.length; i++) {
      // If the owner is not grumpy, all customers are satisfied
      if (grumpy[i] == 0) {
        baseSatisfaction += customers[i];
      }
    }

    // Initialize the current gain variable
    int currentGain = 0;

    // Calculate the gain for the first window
    for (int i = 0; i < minutes; i++) {
      currentGain += customers[i] * grumpy[i];
    }

    // Initialize the maximum gain variable
    int maxGain = currentGain;

    // Slide the window across the customers array
    for (int i = minutes; i < customers.length; i++) {
      // Get the index that leaves the window
      int index = i - minutes;

      // Add the current element to the window gain
      currentGain += customers[i] * grumpy[i];

      // Remove the element that leaves the window
      currentGain -= customers[index] * grumpy[index];

      // Update the maximum gain
      maxGain = Math.max(maxGain, currentGain);
    }

    // Return the total satisfied customers
    return baseSatisfaction + maxGain;
  }
}

public class _1052_Grumpy_Bookstore_Owner {
  // Main method to test maxSatisfied
  public static void main(String[] args) {
    int[] customers = new int[] { 1, 0, 1, 2, 1, 1, 7, 5 };
    int[] grumpy = new int[] { 0, 1, 0, 1, 0, 1, 0, 1 };
    int minutes = 3;

    int result = new Solution().maxSatisfied(customers, grumpy, minutes);

    System.out
        .println("The maximum number of customers that can be satisfied throughout the day is : " + result);
  }
}
