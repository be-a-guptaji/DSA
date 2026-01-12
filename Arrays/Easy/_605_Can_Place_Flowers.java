/*
LeetCode Problem: https://leetcode.com/problems/can-place-flowers/

Question: 605. Can Place Flowers

Problem Statement: Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true
Explanation:
The strings s and t can be made identical by:
Mapping 'e' to 'a'.
Mapping 'g' to 'd'.

Example 2:
Input: s = "foo", t = "bar"
Output: false
Explanation:
The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
*/

/*
Approach: Greedy Counting of Empty Slots

Goal:
Determine if n new flowers can be planted in the flowerbed without violating
the rule that no two flowers can be adjacent.

Key Idea:
Count consecutive empty slots (0’s) between planted flowers and calculate how
many flowers can be placed in those gaps.

Algorithm:
1. Initialize a counter `empty`:
   - If the first slot is empty, start with empty = 1 (virtual boundary).
2. Traverse the flowerbed:
   - If a planted flower (1) is encountered:
       • Flowers that can be placed in the previous empty stretch =
         (empty − 1) / 2
       • Subtract this from n.
       • Reset empty = 0.
   - If an empty slot (0) is encountered:
       • Increment empty.
3. After traversal:
   - Handle the trailing empty stretch:
       • n -= empty / 2
4. If n ≤ 0, return true; otherwise false.

Why It Works:
- Each group of consecutive empty slots determines how many flowers fit
  without adjacency conflicts.
- Treating boundaries carefully avoids special-case logic.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _605_Can_Place_Flowers {
    // Method to determine if n flowers can be placed or not
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        // Get the empty variable for the starting
        int empty = flowerbed[0] == 0 ? 1 : 0;

        // Iterate over the flowerbed array
        for (int space : flowerbed) {
            // If space is one then remove the half flower from n and set the empty to zero
            if (space == 1) {
                n -= (empty - 1) / 2;
                empty = 0;
            } else {
                empty++;
            }
        }

        // Remove the spaces from n
        n -= empty / 2;

        // Retrun true in the end
        return n <= 0;
    }

    // Main method to test canPlaceFlowers
    public static void main(String[] args) {
        int[] flowerbed = new int[] { 1, 0, 0, 0, 1 };
        int n = 1;

        boolean result = canPlaceFlowers(flowerbed, n);

        System.out.println("The flowers can" + (result ? " " : " not ") + "be placed.");
    }
}
