/*
LeetCode Problem: https://leetcode.com/problems/sort-the-people/

Question: 2418. Sort the People

Problem Statement: You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.

For each index i, names[i] and heights[i] denote the name and height of the ith person.

Return names sorted in descending order by the people's heights.

Example 1:
Input: names = ["Mary","John","Emma"], heights = [180,165,170]
Output: ["Mary","Emma","John"]
Explanation: Mary is the tallest, followed by Emma and John.

Example 2:
Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
Output: ["Bob","Alice","Bob"]
Explanation: The first Bob is the tallest, followed by Alice and the second Bob.

Constraints:
n == names.length == heights.length
1 <= n <= 10^3
1 <= names[i].length <= 20
1 <= heights[i] <= 10^5
names[i] consists of lower and upper case English letters.
All the values of heights are distinct.
 */

/*
Approach: HashMap + Sorting (Reverse Order Mapping)

Goal:
- Sort people by their heights in descending order
  and return the corresponding names.

Core Idea:
- Map each height to corresponding names.
- Sort the heights array.
- Traverse heights in reverse order to reconstruct
  the names array in descending height order.

Algorithm Steps:
1. Create a HashMap:
   - key = height, value = list of names with that height.
2. Populate the map.
3. Sort the heights array in ascending order.
4. Traverse heights from end to start:
   - Fetch corresponding name from map.
   - Place into result (reuse names array).
5. Return the updated names array.

Time Complexity:
- O(n log n)
  due to sorting

Space Complexity:
- O(n)

Result:
- Returns names sorted in descending order of heights.
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// Solution Class
class Solution {
    // Method to find the sorted in descending order by the people's heights
    public String[] sortPeople(String[] names, int[] heights) {
        // Initialize the hash map for the heights and names pair
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();

        // Fill the hashmap
        for (int i = 0; i < heights.length; i++) {
            map.computeIfAbsent(heights[i], _ -> new ArrayList<>()).add(names[i]);
        }

        // Sort the array
        Arrays.sort(heights);

        // Fill the String names array
        for (int i = 0; i < heights.length; i++) {
            names[i] = map.get(heights[heights.length - 1 - i]).removeFirst();
        }

        // Return the names array
        return names;
    }
}

public class _2418_Sort_the_People {
    // Main method to test sortPeople
    public static void main(String[] args) {
        String[] names = new String[] { "Mary", "John", "Emma" };
        int[] heights = new int[] { 180, 165, 170 };

        String[] result = new Solution().sortPeople(names, heights);

        System.out.println("The sorted in descending order by the people's heights is : " + Arrays.toString(result));
    }
}
