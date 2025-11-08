/*
LeetCode Problem: https://leetcode.com/problems/palindrome-partitioning/

Question: 131. Palindrome Partitioning

Problem Statement: Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */

/*
Approach:
1. We are given a string `s` and need to find all possible ways to partition it such that every substring in each partition is a palindrome.

2. To solve this, we use a **backtracking (DFS)** approach where we recursively explore all possible substring partitions of the input string.

3. The main idea:
   - Start from the first index of the string.
   - For every possible end index `i` (from current `index` to `s.length() - 1`):
       • Check if the substring `s[index...i]` is a palindrome.
       • If it is a palindrome, include this substring in the current partition.
       • Recursively continue exploring from the next index (`i + 1`).
   - Once we reach the end of the string, add the current list of substrings to the result.

4. The helper function `isPalindromicSubString(char[] str, int left, int right)` checks whether a substring is palindromic by comparing characters inward from both ends.

5. The algorithm builds partitions incrementally and backtracks by removing the last added substring after each recursive exploration.

6. Using a `char[]` array improves performance slightly, as character comparisons are faster than multiple substring operations.

7. This ensures that:
   - Every possible palindrome partition is generated.
   - Each recursive branch represents a unique partitioning path.
   - No duplicate partitions are produced.

Time Complexity: O(N * 2^N), Each character can either mark a cut or not (2^N combinations) and checking palindrome for each substring takes O(N) in the worst case.

Space Complexity: O(N), For recursion depth and current partition storage.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class _131_Palindrome_Partitioning {
   // Method to find all possible palindrome partitioning of a given string
   public static List<List<String>> partition(String s) {
      // Initialize the list to store all valid palindrome partitions
      List<List<String>> result = new ArrayList<>();

      // Convert the string into the character array
      char[] str = s.toCharArray();

      // Start the recursive backtracking process to generate all possible partitions
      backtrack(0, s, str, new ArrayList<>(), result);

      // Return the final list of palindrome partitions
      return result;
   }

   // Helper method to generate all possible palindrome partitions using
   // backtracking
   private static void backtrack(int index, String s, char[] str, List<String> partition, List<List<String>> list) {
      // Base case: if the index reaches the end of the string, add the current
      // partition to the list
      if (index == s.length()) {
         list.add(new ArrayList<>(partition));
         return;
      }

      // Iterate through all possible substring partitions starting from the current
      // index
      for (int i = index; i < s.length(); i++) {
         // If the current substring is a valid palindrome, continue exploring this path
         if (isPalindromicSubString(str, index, i)) {
            // Add the current palindromic substring to the partition list
            partition.add(s.substring(index, i + 1));

            // Recursive call to find further palindrome partitions starting from the next
            // index
            backtrack(i + 1, s, str, partition, list);

            // Remove the last added substring to backtrack and explore other partitions
            partition.remove(partition.size() - 1);
         }
      }
   }

   // Helper method to check if the given substring is palindromic or not
   private static boolean isPalindromicSubString(char[] str, int left, int right) {
      // Traverse inward and compare characters to check for palindrome property
      while (left < right) {
         // If mismatch occurs, return false as it’s not a palindrome
         if (str[left] != str[right]) {
            return false;
         }

         left++; // Increment left pointer
         right--; // Decrement right pointer
      }

      // Return true if the entire substring is palindromic
      return true;
   }

   // Main method to test partition
   public static void main(String[] args) {
      String s = "aab";

      List<List<String>> result = partition(s);

      System.out.print("The all possible palindrome partitioning of " + s + " is : " + result);
   }
}
