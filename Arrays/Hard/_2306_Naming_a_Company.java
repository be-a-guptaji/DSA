/*
LeetCode Problem: https://leetcode.com/problems/naming-a-company/

Question: 2306. Naming a Company

Problem Statement: You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:

Choose 2 distinct names from ideas, call them ideaA and ideaB.
Swap the first letters of ideaA and ideaB with each other.
If both of the new names are not found in the original ideas, then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
Otherwise, it is not a valid name.
Return the number of distinct valid names for the company.

Example 1:
Input: ideas = ["coffee","donuts","time","toffee"]
Output: 6
Explanation: The following selections are valid:
- ("coffee", "donuts"): The company name created is "doffee conuts".
- ("donuts", "coffee"): The company name created is "conuts doffee".
- ("donuts", "time"): The company name created is "tonuts dime".
- ("donuts", "toffee"): The company name created is "tonuts doffee".
- ("time", "donuts"): The company name created is "dime tonuts".
- ("toffee", "donuts"): The company name created is "doffee tonuts".
Therefore, there are a total of 6 distinct company names.

The following are some examples of invalid selections:
- ("coffee", "time"): The name "toffee" formed after swapping already exists in the original array.
- ("time", "toffee"): Both names are still the same after swapping and exist in the original array.
- ("coffee", "toffee"): Both names formed after swapping already exist in the original array.

Example 2:
Input: ideas = ["lack","back"]
Output: 0
Explanation: There are no valid selections. Therefore, 0 is returned.

Constraints:
2 <= ideas.length <= 5 * 10^4
1 <= ideas[i].length <= 10
ideas[i] consists of lowercase English letters.
All the strings in ideas are unique.
*/

/*
Approach: Grouping by First Character + Set Intersection

Goal:
- Count the number of distinct valid company names formed by
  swapping the first letters of two ideas.

Core Idea:
- For each starting character:
   store all suffixes in a HashSet.
- When comparing two groups:
   - Common suffixes are invalid because swapping
     creates existing names.
- Valid swaps:
    (unique suffixes in group A) *
    (unique suffixes in group B)
- Multiply by 2 because:
   (a, b) and (b, a) are distinct names.

Algorithm Steps:
1. Create 26 HashSets:
   suffixes[i] stores suffixes of words
   starting with character ('a' + i).
2. Fill suffix groups:
   - Remove first character using substring(1).
3. For every pair of groups (i, j):
   - Count common suffixes (intersection).
4. Valid combinations:
   (sizeA - intersection) *
   (sizeB - intersection)
5. Add result × 2.
6. Return final count.

Time Complexity:
- O(26² * average suffix comparisons)

Space Complexity:
- O(n)

Result:
- Returns total number of distinct valid company names.
*/

package Arrays.Hard;

import java.util.HashSet;

// Solution Class
class Solution {
    // Method to find the number of distinct valid names for the company
    public long distinctNames(String[] ideas) {
        // Initialize the hashset array for the suffixes
        HashSet<String>[] suffixes = new HashSet[26];

        // Initialize hashset for all the alphabets
        for (int i = 0; i < 26; i++) {
            suffixes[i] = new HashSet<>();
        }

        // Fill the hashset array
        for (int i = 0; i < ideas.length; i++) {
            suffixes[ideas[i].charAt(0) - 'a'].add(ideas[i].substring(1));
        }

        // Initialize the result variable
        long result = 0;

        // Itarate over the suffixes array
        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                // Initialize the intersection variable
                int intersection = 0;

                // Check for the intersection
                for (String s : suffixes[i]) {
                    // If suffixes[j] contains(s) then increment the intersection
                    if (suffixes[j].contains(s)) {
                        intersection++;
                    }
                }

                // Update the result
                result += ((suffixes[i].size() - intersection) * (suffixes[j].size() - intersection));
            }
        }

        // Retrun the result
        return result << 1;
    }
}

public class _2306_Naming_a_Company {
    // Main method to test the distinctNames
    public static void main(String[] args) {
        String[] ideas = new String[] { "coffee", "donuts", "time", "toffee" };

        long result = new Solution().distinctNames(ideas);

        System.out.println("The number of distinct valid names for the company is : " + result);
    }
}
