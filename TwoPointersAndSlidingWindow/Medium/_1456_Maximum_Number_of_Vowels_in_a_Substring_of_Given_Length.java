/*
LeetCode Problem: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/

Question: 1456. Maximum Number of Vowels in a Substring of Given Length

Problem Statement: Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Example 1:
Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.

Example 2:
Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.

Example 3:
Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.

Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.
1 <= k <= s.length
*/

/*
Approach: Fixed-Size Sliding Window

Goal:
- Find the maximum number of vowels present in
  any substring of length k.

Core Idea:
- Maintain a sliding window of size k.
- Track the number of vowels currently inside
  the window.
- When the window moves:
   - Add contribution of incoming character.
   - Remove contribution of outgoing character.
- Keep the maximum vowel count seen.

Algorithm Steps:
1. Initialize:
   - left pointer
   - currentWindowVowels = 0
   - maxVowels = 0
2. Expand window using right pointer:
   - If s[right] is a vowel:
       increment currentWindowVowels.
3. If window size exceeds k:
   - Remove contribution of s[left].
   - Increment left.
4. When window size equals k:
   - Update maximum vowel count.
5. Return maximum count.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the maximum number of vowels
  contained in any substring of length k.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the maximum number of vowel letters in any substring of s with
  // length k
  public int maxVowels(String s, int k) {
    // Convert the s to character array
    char[] str = s.toCharArray();

    // Initialize the vowels varaible
    int vowels = 0;

    // Iterate over the string character s
    for (int left = 0, right = 0, currentWindowVowels = 0; right < str.length; right++) {
      // Get the right index character from the s
      char ch = str[right];

      // If ch is vowel then increment currentWindowVowels
      if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
        currentWindowVowels++;
      }

      // If window is bigger then the k then shirink the window
      if ((right - left + 1) > k) {
        // Get the left index character from the s
        char c = str[left];

        // If c is vowel then decrement currentWindowVowels
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
          currentWindowVowels--;
        }

        // Increment the left index
        left++;
      }

      // If window is
      if ((right - left + 1) == k) {
        // Update the vowel if needed
        if (currentWindowVowels > vowels) {
          vowels = currentWindowVowels;
        }
      }
    }

    // Return the vowels
    return vowels;
  }
}

public class _1456_Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length {
  // Main method to test maxVowels
  public static void main(String[] args) {
    String s = "abciiidef";
    int k = 3;

    int result = new Solution().maxVowels(s, k);

    System.out.println("The maximum number of vowel letters in any substring of s with length k is : " + result);
  }
}
