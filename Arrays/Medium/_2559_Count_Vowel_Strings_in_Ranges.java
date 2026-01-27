/*
LeetCode Problem: https://leetcode.com/problems/count-vowel-strings-in-ranges/

Question: 2559. Count Vowel Strings in Ranges

Problem Statement: You are given a 0-indexed array of strings words and a 2D array of integers queries.

Each query queries[i] = [li, ri] asks us to find the number of strings present at the indices ranging from li to ri (both inclusive) of words that start and end with a vowel.

Return an array ans of size queries.length, where ans[i] is the answer to the ith query.

Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

Example 1:
Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
Output: [2,3,0]
Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
The answer to the query [0,2] is 2 (strings "aba" and "ece").
to query [1,4] is 3 (strings "ece", "aa", "e").
to query [1,1] is 0.
We return [2,3,0].

Example 2:
Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
Output: [3,2,1]
Explanation: Every string satisfies the conditions, so we return [3,2,1].

Constraints:

1 <= words.length <= 10^5
1 <= words[i].length <= 40
words[i] consists only of lowercase English letters.
sum(words[i].length) <= 3 * 10^5
1 <= queries.length <= 10^5
0 <= li <= ri < words.length
*/

/*
Approach: Prefix Sum + Bitmask

Goal:
For each query [l, r], count how many words in words[l..r]
start and end with a vowel.

Key Ideas:
1. Vowel Check using Bitmask
   - Precompute a bitmask where bits for vowels (a, e, i, o, u) are set.
   - A character is a vowel if (1 << (ch - 'a')) & vowels != 0.

2. Prefix Sum
   - Build a prefixSum array where:
     prefixSum[i] = number of valid vowel-strings in words[0..i-1].
   - A word is valid if:
     • first character is a vowel
     • last character is a vowel

3. Answer Queries in O(1)
   - For query [l, r]:
     ans = prefixSum[r + 1] - prefixSum[l]

Algorithm:
1. Build prefixSum array while checking each word.
2. For each query, compute result using prefixSum difference.
3. Return result array.

Time Complexity:
- Preprocessing: O(n)
- Each query: O(1)
- Total: O(n + q)

Space Complexity:
- O(n) for prefixSum
*/

package Arrays.Medium;

import java.util.Arrays;

public class _2559_Count_Vowel_Strings_in_Ranges {
    // Method to find an array ans of size queries.length, where ans[i] is the
    // answer to the ith query
    public static int[] vowelStrings(String[] words, int[][] queries) {
        // Initialize the vowel bitmask
        int vowels = 1065233;

        // Initialize the prefix sum array one more than the words length
        int[] prefixSum = new int[words.length + 1];

        // Iterate over the words array for filling the prefix sum array
        for (int i = 0; i < words.length; i++) {
            // Fill the prefix sum array
            prefixSum[i + 1] = prefixSum[i] + (((((1 << (words[i].charAt(0) - 'a')) & vowels) != 0)
                    && (((1 << (words[i].charAt(words[i].length() - 1) - 'a')) & vowels) != 0)) ? 1 : 0);
        }

        // Initialize the result with length equal to queries length
        int[] result = new int[queries.length];

        // Iterate over the prefix sum array for filling the result array
        for (int i = 0; i < queries.length; i++) {
            // Fill the result array
            result[i] = prefixSum[queries[i][1] + 1] - prefixSum[queries[i][0]];
        }

        // Return the result
        return result;
    }

    // Main method to test vowelStrings
    public static void main(String[] args) {
        String[] words = new String[] { "aba", "bcb", "ece", "aa", "e" };
        int[][] queries = new int[][] { { 0, 2 }, { 1, 4 }, { 1, 1 } };

        int[] result = vowelStrings(words, queries);

        System.out.println(
                "An array ans of size queries.length, where ans[i] is the answer to the ith query is : "
                        + Arrays.toString(result));
    }
}
