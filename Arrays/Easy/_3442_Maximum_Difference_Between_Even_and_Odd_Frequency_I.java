/*
LeetCode Problem: https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/

Question: 3442. Maximum Difference Between Even and Odd Frequency I

Problem Statement: You are given a string s consisting of lowercase English letters.

Your task is to find the maximum difference diff = freq(a1) - freq(a2) between the frequency of characters a1 and a2 in the string such that:

a1 has an odd frequency in the string.
a2 has an even frequency in the string.
Return this maximum difference.

Example 1:
Input: s = "aaaaabbc"
Output: 3
Explanation:
The character 'a' has an odd frequency of 5, and 'b' has an even frequency of 2.
The maximum difference is 5 - 2 = 3.

Example 2:
Input: s = "abcabcab"
Output: 1
Explanation:
The character 'a' has an odd frequency of 3, and 'c' has an even frequency of 2.
The maximum difference is 3 - 2 = 1.

Constraints:

3 <= s.length <= 100
s consists only of lowercase English letters.
s contains at least one character with an odd frequency and one with an even frequency.
 */

/*
Approach: Frequency Counting with Parity Tracking

Goal:
Find the maximum difference between:
- the highest odd frequency, and
- the lowest even frequency
among characters in the string.

Key Idea:
Count the frequency of each character, then separately track:
- the maximum frequency that is odd,
- the minimum frequency that is even.

Algorithm:
1. Create a frequency array of size 26 for lowercase letters.
2. Traverse the string and populate the frequency array.
3. Initialize:
   - oddMax = −∞
   - evenMax = +∞
4. Iterate through the frequency array:
   - Skip frequencies equal to 0.
   - If frequency is odd:
       oddMax = max(oddMax, frequency)
   - If frequency is even:
       evenMax = min(evenMax, frequency)
5. Return oddMax − evenMax.

Why It Works:
- Separating odd and even frequencies allows direct comparison.
- Tracking max odd and min even ensures the largest possible difference.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _3442_Maximum_Difference_Between_Even_and_Odd_Frequency_I {
    // Method to find the maximum difference between even and odd frequency
    public static int maxDifference(String s) {
        // Convert the string into the character array
        char[] str = s.toCharArray();

        // Initialize the frequency array
        int[] frequency = new int[26];

        // Initialize the oddMax and evenMax variable
        int oddMax = Integer.MIN_VALUE, evenMax = Integer.MAX_VALUE;

        // Iterate over the str array
        for (char ch : str) {
            // Increment the frequency
            frequency[ch - 'a']++;
        }

        // Iterate over the frequency array to find out oddMax and evenMax
        for (int freq : frequency) {
            // If freq is zero then skip the iteration
            if (freq == 0) {
                continue;
            }

            if ((freq % 2) == 1) {
                oddMax = Math.max(oddMax, freq);
            } else {
                evenMax = Math.min(evenMax, freq);
            }
        }

        // Return the differenc of the oddMax and evenMax
        return oddMax - evenMax;
    }

    // Main method to test maxDifference
    public static void main(String[] args) {
        String s = "aaaaabbc";

        int result = maxDifference(s);

        System.out.println("The maximum difference between even and odd frequency is : " + result);
    }
}
