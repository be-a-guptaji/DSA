/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/

Question: 1963. Minimum Number of Swaps to Make the String Balanced

Problem Statement: You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.

A string is called balanced if and only if:

It is the empty string, or
It can be written as AB, where both A and B are balanced strings, or
It can be written as [C], where C is a balanced string.
You may swap the brackets at any two indices any number of times.

Return the minimum number of swaps to make s balanced.

Example 1:
Input: s = "][]["
Output: 1
Explanation: You can make the string balanced by swapping index 0 with index 3.
The resulting string is "[[]]".

Example 2:
Input: s = "]]][[["
Output: 2
Explanation: You can do the following to make the string balanced:
- Swap index 0 with index 4. s = "[]][][".
- Swap index 1 with index 5. s = "[[][]]".
The resulting string is "[[][]]".

Example 3:
Input: s = "[]"
Output: 0
Explanation: The string is already balanced.

Constraints:
n == s.length
2 <= n <= 10^6
n is even.
s[i] is either '[' or ']'.
The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
*/

/*
Approach: Unmatched Bracket Counting

Goal:
- Find the minimum number of swaps required
  to balance the bracket string.

Core Idea:
- Track unmatched opening brackets.
- For each ']':
   - If there is an unmatched '[' available, match it.
   - Otherwise, it remains unbalanced.
- At the end, stackSize represents unmatched '['.
- Every swap can fix two unmatched brackets.

Algorithm Steps:
1. Initialize stackSize = 0.
2. Traverse the string:
   - If '[' → increment stackSize.
   - Else if stackSize > 0 → decrement stackSize.
3. Remaining stackSize gives unmatched opening brackets.
4. Minimum swaps needed:
   - (stackSize + 1) / 2

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the minimum swaps required to make the string balanced.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the minimum number of swaps to make s balanced
  public int minSwaps(String s) {
    // Convert s into character string
    char[] str = s.toCharArray();

    // Initialize the stack size
    int stackSize = 0;

    // Iterate over the str
    for (int i = 0; i < str.length; i++) {
      if (str[i] == '[') {
        stackSize++;
      } else if (stackSize != 0) {
        stackSize--;
      }
    }

    // Return the stackSize by 2
    return (stackSize + 1) >> 1;
  }
}

public class _1963_Minimum_Number_of_Swaps_to_Make_the_String_Balanced {
  // Main method to test minSwaps
  public static void main(String[] args) {
    String s = "]]][[[";

    int result = new Solution().minSwaps(s);

    System.out.println("The minimum number of swaps to make " + s + " balanced is : " + result);
  }
}
