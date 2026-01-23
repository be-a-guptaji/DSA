/*
LeetCode Problem: https://leetcode.com/problems/path-crossing/

Question: 1496. Path Crossing

Problem Statement: Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.

Example 1:
Input: path = "NES"
Output: false 
Explanation: Notice that the path doesn't cross any point more than once.

Example 2:
Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.

Constraints:

1 <= path.length <= 104
path[i] is either 'N', 'S', 'E', or 'W'.
*/

/*
Approach: Coordinate Tracking with HashSet

Goal:
Determine whether a given path crosses itself.
The path starts at (0, 0) and moves one unit in the direction:
'N', 'S', 'E', or 'W'.

Key Idea:
Track every visited coordinate. If the same coordinate is visited again,
the path crosses itself.

Algorithm:
1. Initialize coordinates:
   - horizontal = 0, vertical = 0 (starting at origin).
2. Use a HashSet to store visited positions.
   - Encode (x, y) into a single integer key: x * 20000 + y.
3. Add the origin (0, 0) to the set.
4. Traverse the path character by character:
   - Update (horizontal, vertical) based on direction.
   - Encode the new position.
   - If the position already exists in the set, return true.
   - Otherwise, add it to the set.
5. If traversal completes without repeats, return false.

Why It Works:
- Every move changes the position by exactly one unit.
- Revisiting a coordinate implies the path intersects itself.
- HashSet enables O(1) lookup for visited positions.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package Arrays.Easy;

import java.util.HashSet;

public class _1496_Path_Crossing {
    // Method to determine if the path crosses itself at any point or not
    public static boolean isPathCrossing(String path) {
        // Initialize the horizontal and vertical variable
        int horizontal = 0, vertical = 0;

        // Initialize the hash set for points
        HashSet<Integer> visit = new HashSet<>();

        // Add the origin to the set
        visit.add(0);

        // Iterate over the path
        for (char direction : path.toCharArray()) {
            // Update the horizontal and vertical variable according to the direction
            switch (direction) {
                case 'N' -> {
                    vertical++;
                }
                case 'E' -> {
                    horizontal++;
                }
                case 'W' -> {
                    horizontal--;
                }
                case 'S' -> {
                    vertical--;
                }
            }

            // Get the hash of the points
            int hashCode = horizontal * 20000 + vertical;

            // If we already hash visited the point return true
            if (!visit.add(hashCode)) {
                return true;
            }
        }

        // Retrun false in the end
        return false;
    }

    // Main method to test isPathCrossing
    public static void main(String[] args) {
        String path = "NESWW";

        boolean result = isPathCrossing(path);

        System.out.println("The path crosses" + (result ? " " : " does not ") + "itself at any poin.");
    }
}
