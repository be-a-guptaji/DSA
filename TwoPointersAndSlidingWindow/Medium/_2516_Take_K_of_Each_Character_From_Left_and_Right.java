/*
LeetCode Problem: https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/

Question: 2516. Take K of Each Character From Left and Right

Problem Statement: You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.

Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.

Example 1:
Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation: 
Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.

Example 2:
Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.

Constraints:
1 <= s.length <= 10^5
s consists of only the letters 'a', 'b', and 'c'.
0 <= k <= s.length
*/

/*
Approach: Sliding Window on the Middle Substring

Goal:
- Take characters only from the left and right ends
  so that at least k occurrences of 'a', 'b', and 'c'
  are taken.
- Return the minimum number of characters taken.

Key Insight:
- Instead of choosing characters to take,
  find the longest substring to KEEP.

- Let:
      totalA, totalB, totalC
  be the frequencies in the entire string.

- After taking at least k of each character,
  the remaining middle substring may contain at most:

      totalA - k
      totalB - k
      totalC - k

  of each character.

- Therefore:
  Find the longest substring whose counts satisfy:

      a <= totalA - k
      b <= totalB - k
      c <= totalC - k

- The answer is:

      n - longestValidSubstring

Algorithm Steps:
1. Count total frequencies of:
      a, b, c
2. If any frequency is less than k:
      return -1
3. Compute allowed counts inside the kept substring:
      maxA = totalA - k
      maxB = totalB - k
      maxC = totalC - k
4. Use a sliding window:
      [left, right]
5. Expand right and update character counts.
6. While any count exceeds its allowed limit:
      shrink from the left.
7. Track the maximum valid window length.
8. Return:
      n - maxWindowLength

Why It Works:
- Characters not inside the kept window are exactly
  the characters taken from the two ends.
- Ensuring the kept window contains at most
  (totalCount - k) of each character guarantees
  that at least k of each character remain outside
  the window and are therefore taken.
- Maximizing the kept window minimizes the number
  of characters taken.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the minimum number of characters that
  must be taken from the left and right ends,
  or -1 if it is impossible.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the minimum number of minutes needed for you to take at least
  // k of each character
  public int takeCharacters(String s, int k) {
    // Convert the string s into character array
    char[] str = s.toCharArray();

    // Initialize the minimumNumberOfMinutes variable
    int minimumNumberOfMinutes = 0;

    // Initialize the a, b and c variable for frequency
    int numberOfA = 0;
    int numberOfB = 0;
    int numberOfC = 0;

    // Count the frequency of the variable
    for (int i = 0; i < str.length; i++) {
      // Update the frequency variable
      switch (str[i]) {
        case 'a' -> {
          numberOfA++;
        }
        case 'b' -> {
          numberOfB++;
        }
        case 'c' -> {
          numberOfC++;
        }
      }
    }

    // Initialize the minimum a, b and c variable for frequency
    int minimumNumberOfA = numberOfA - k;
    int minimumNumberOfB = numberOfB - k;
    int minimumNumberOfC = numberOfC - k;

    // If minimum frequency of a, b and c variable is negative then return -1
    if (minimumNumberOfA < 0 || minimumNumberOfB < 0 || minimumNumberOfC < 0) {
      return -1;
    }

    // Reset the frequency of a, b and c variable
    numberOfA = 0;
    numberOfB = 0;
    numberOfC = 0;

    // Iterate over the str array
    for (int left = 0, right = 0; right < str.length; right++) {
      // Update the frequency variable
      switch (str[right]) {
        case 'a' -> {
          numberOfA++;
        }
        case 'b' -> {
          numberOfB++;
        }
        case 'c' -> {
          numberOfC++;
        }
      }

      // Check if the variable is atleat k or not
      while (numberOfA > minimumNumberOfA || numberOfB > minimumNumberOfB || numberOfC > minimumNumberOfC) {
        // Update the frequency variable
        switch (str[left]) {
          case 'a' -> {
            numberOfA--;
          }
          case 'b' -> {
            numberOfB--;
          }
          case 'c' -> {
            numberOfC--;
          }
        }

        // Increment the left variable
        left++;
      }

      // Update the minimumNumberOfMinutes variable
      minimumNumberOfMinutes = Math.max(minimumNumberOfMinutes, right - left + 1);

    }

    // Return the str.length - minimumNumberOfMinutes
    return str.length - minimumNumberOfMinutes;
  }
}

public class _2516_Take_K_of_Each_Character_From_Left_and_Right {
  // Main method to test takeCharacters
  public static void main(String[] args) {
    String s = "aabaaaacaabc";
    int k = 2;

    int result = new Solution().takeCharacters(s, k);

    System.out.println("The minimum number of minutes needed for you to take at least k of each character is : "
        + result);
  }
}
