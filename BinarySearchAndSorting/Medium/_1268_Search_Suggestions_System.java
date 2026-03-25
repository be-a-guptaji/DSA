/*
LeetCode Problem: https://leetcode.com/problems/search-suggestions-system/

Question: 1268. Search Suggestions System

Problem Statement: You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.

Example 1:
Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].

Example 2:
Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.

Constraints:

1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 10^4
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.
*/

/*
Approach: Sorting + Binary Search (Prefix Range Narrowing)

Goal:
- For each prefix of searchWord, return up to 3 lexicographically
  smallest product suggestions matching that prefix.

Core Idea:
- Sort products lexicographically.
- For each prefix, use binary search to find the first index
  where products[i] ≥ prefix.
- From that index, check the next up to 3 products for prefix match.
- Reuse previous start index to reduce search space.

Algorithm Steps:
1. Sort the products array.
2. Initialize start = 0 and an empty prefix.
3. For each character in searchWord:
   - Append character to prefix.
   - Use lowerBound to find first index ≥ prefix.
   - From that index, check next 3 products:
       - If product starts with prefix → add to result.
4. Add collected suggestions for each prefix.

Time Complexity:
- O(n log n + m log n)
  n = number of products, m = length of searchWord

Space Complexity:
- O(1) (excluding output)

Result:
- Returns a list of suggestions for each prefix of searchWord.
*/

package BinarySearchAndSorting.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Solution Class
class Solution {
    // Method to find a list of lists of the suggested products after each character
    // of searchWord is typed
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // Sort the products array
        Arrays.sort(products);

        // Initialize the list of lists of string for the result
        List<List<String>> suggestions = new ArrayList<>();

        // Convert search word into the character array
        char[] searchWordArray = searchWord.toCharArray();

        // Initialize the start pointer
        int start = 0;

        // Initialize the string builder
        StringBuilder prefix = new StringBuilder();

        // Iterate over the searchWordArray to find the matching value of each index
        for (int i = 0; i < searchWordArray.length; i++) {
            // Add the current character to the string builder
            prefix.append(searchWordArray[i]);

            // Convert the prefix to string
            String prefixString = prefix.toString();

            // Find the left and right index
            start = this.lowerBound(products, start, prefixString);

            // Initialize the list for the suggestions
            List<String> list = new ArrayList<>();

            // Add the value to the list
            for (int j = start; j < Math.min(start + 3, products.length); j++) {
                if (products[j].startsWith(prefixString)) {
                    list.add(products[j]);
                }
            }

            // Add the list to the suggestions list
            suggestions.add(list);
        }

        // Retrun the suggestion list
        return suggestions;
    }

    // Helper method to find the lower bound
    private int lowerBound(String[] products, int start, String prefix) {
        // Initialize the left and right pointer
        int left = start, right = products.length;

        // Iterate over the bound to find the lower bound
        while (left < right) {
            // Get the mid value
            int mid = (left + right) >> 1;

            // Update the bound
            if (products[mid].compareTo(prefix) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Return left
        return left;
    }
}

public class _1268_Search_Suggestions_System {
    // Main method to test suggestedProducts
    public static void main(String[] args) {
        String[] products = new String[] { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
        String searchWord = "mouse";

        List<List<String>> result = new Solution().suggestedProducts(products, searchWord);

        System.out.println(
                "A list of lists of the suggested products after each character of searchWord is typed is : " + result);
    }
}