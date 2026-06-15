/*
LeetCode Problem: https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/

Question: 3306. Count of Substrings Containing Every Vowel and K Consonants II

Problem Statement: You are given a string word and a non-negative integer k.

Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.

Example 1:
Input: word = "aeioqq", k = 1
Output: 0
Explanation:
There is no substring with every vowel.

Example 2:
Input: word = "aeiou", k = 0
Output: 1
Explanation:
The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".

Example 3:
Input: word = "ieaouqqieaouqq", k = 1
Output: 3
Explanation:
The substrings with every vowel and one consonant are:
word[0..5], which is "ieaouq".
word[6..11], which is "qieaou".
word[7..12], which is "ieaouq".

Constraints:
5 <= word.length <= 2 * 10^5
word consists only of lowercase English letters.
0 <= k <= word.length - 5
*/

/*
Approach: Sliding Window + Inclusion-Exclusion

Goal:
- Count substrings that:
   1. Contain all five vowels:
         a, e, i, o, u
      at least once.
   2. Contain exactly k consonants.

Core Idea:
- Counting "exactly k" is difficult directly.
- Instead use:

      exactly(k)
    = atLeast(k)
      - atLeast(k + 1)

- The helper function atLeast(x) counts
  substrings containing:
      all five vowels
      and at least x consonants.

Helper Function: atLeast(x)
---------------------------
Maintain a sliding window with:
- vowel frequencies
- consonant count

Expand the window with right.

Whenever:
- every vowel appears at least once
- consonants >= x

the current window is valid.

Then every extension of this window to the right
is also valid, so add:

      n - right

to the answer.

Shrink from the left while the window remains valid.

Algorithm Steps:
1. Compute:
      atLeast(k)
2. Compute:
      atLeast(k + 1)
3. Return:

      atLeast(k) - atLeast(k + 1)

Why It Works:
- Every substring counted by:
      atLeast(k)
  either has exactly k consonants,
  exactly k+1 consonants,
  etc.

- Subtracting:
      atLeast(k + 1)
  removes all substrings having more than k
  consonants.

- What remains are substrings having exactly
  k consonants.

Sliding Window Invariant:
- Window tracks:
      count(a), count(e), count(i),
      count(o), count(u)
      consonantCount

- Valid window condition:

      all vowels present
      &&
      consonantCount >= k

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Notes:
- The use of CompletableFuture does not change
  the asymptotic complexity.
- On platforms such as LeetCode it often provides
  little or no benefit because:
   - the helper is already O(n)
   - thread creation/scheduling introduces overhead
   - execution environments may limit parallelism

Result:
- Returns the number of substrings containing
  every vowel at least once and exactly k
  consonants.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.concurrent.CompletableFuture;

// Solution Class
class Solution {
  // Method to find the total number of substrings of word that contain every
  // vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants
  public long countOfSubstrings(String word, int k) {
    // Convert the word into the character array
    char[] str = word.toCharArray();

    // Complete the functions parallely
    CompletableFuture<Long> atLeastK = CompletableFuture.supplyAsync(() -> this.atLeast(str, k));
    CompletableFuture<Long> atLeastKPlus1 = CompletableFuture.supplyAsync(() -> this.atLeast(str, k + 1));

    // Return the atLeastK - atLeastKPlus1
    return atLeastK.join() - atLeastKPlus1.join();
  }

  // Helper method to find atLeast k consonat in the string
  private long atLeast(char[] str, int k) {
    // Initialize the totalNumberOfSubstring variable
    long totalNumberOfSubstring = 0;

    // Initialize the variable to count the frequency of the vowel
    int numberOfA = 0;
    int numberOfE = 0;
    int numberOfI = 0;
    int numberOfO = 0;
    int numberOfU = 0;

    // Iterate over the str array
    for (int left = 0, right = 0, currentConstantsCount = 0; right < str.length; right++) {
      // Update the frequency variable
      switch (str[right]) {
        case 'a' -> {
          numberOfA++;
        }
        case 'e' -> {
          numberOfE++;
        }
        case 'i' -> {
          numberOfI++;
        }
        case 'o' -> {
          numberOfO++;
        }
        case 'u' -> {
          numberOfU++;
        }
        default -> {
          currentConstantsCount++;
        }
      }

      // Update the frequency if currentConstantsCount is greater than k
      while (numberOfA > 0 && numberOfE > 0 && numberOfI > 0 && numberOfO > 0 && numberOfU > 0
          && currentConstantsCount >= k) {
        // Update the frequency variable
        switch (str[left]) {
          case 'a' -> {
            numberOfA--;
          }
          case 'e' -> {
            numberOfE--;
          }
          case 'i' -> {
            numberOfI--;
          }
          case 'o' -> {
            numberOfO--;
          }
          case 'u' -> {
            numberOfU--;
          }
          default -> {
            currentConstantsCount--;
          }
        }

        // Increment the left variable
        left++;

        // Update the totalNumberOfSubstring
        totalNumberOfSubstring += (str.length - right);
      }
    }

    // Return the totalNumberOfSubstring
    return totalNumberOfSubstring;
  }
}

public class _3306_Count_of_Substrings_Containing_Every_Vowel_and_K_Consonants_II {
  // Main method to test countOfSubstrings
  public static void main(String[] args) {
    String word = "aeioqq";
    int k = 1;

    long result = new Solution().countOfSubstrings(word, k);

    System.out.println(
        "The total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants is : "
            + result);
  }
}
