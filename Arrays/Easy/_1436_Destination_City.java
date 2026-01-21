/*
LeetCode Problem: https://leetcode.com/problems/destination-city/

Question: 1436. Destination City

Problem Statement: You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.

It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.

Example 1:
Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
Output: "Sao Paulo" 
Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".

Example 2:
Input: paths = [["B","C"],["D","B"],["C","A"]]
Output: "A"
Explanation: All possible trips are: 
"D" -> "B" -> "C" -> "A". 
"B" -> "C" -> "A". 
"C" -> "A". 
"A". 
Clearly the destination city is "A".

Example 3:
Input: paths = [["A","Z"]]
Output: "Z"

Constraints:

1 <= paths.length <= 100
paths[i].length == 2
1 <= cityAi.length, cityBi.length <= 10
cityAi != cityBi
All strings consist of lowercase and uppercase English letters and the space character.
 */

/*
Approach: Source Tracking with HashSet

Goal:
Find the destination city that has no outgoing paths.

Key Idea:
In the given paths, the destination city will never appear
as a source city.

Algorithm:
1. Create a HashSet to store all source cities (paths[i][0]).
2. Traverse the paths and add each source city to the set.
3. Traverse the paths again:
   - If a destination city (paths[i][1]) is not present in the set,
     it is the final destination → return it.
4. If no such city exists, return an empty string.

Why It Works:
- Every city except the final destination appears as a source.
- HashSet allows O(1) lookups.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _1436_Destination_City {
    // Method to find the city without any path outgoing to another city
    public static String destCity(List<List<String>> paths) {
        // Make a hash set to hold the value of the last index of the value
        HashSet<String> set = new HashSet<>();

        // Fill the last value of the list to the hash set
        for (List<String> path : paths) {
            set.add(path.get(0));
        }

        // Remove the first value from the set
        for (List<String> path : paths) {
            // If set does not contain the string then return the string
            if (!set.contains(path.get(1))) {
                return path.get(1);
            }
        }

        // Retrun the empty string in the end
        return "";
    }

    // Main method to test destCity
    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>(List.of(
                List.of("London", "New York"),
                List.of("New York", "Lima"),
                List.of("Lima", "Sao Paulo")));

        String result = destCity(paths);

        System.out.println("The city without any path outgoing to another city is : " + result);
    }
}
