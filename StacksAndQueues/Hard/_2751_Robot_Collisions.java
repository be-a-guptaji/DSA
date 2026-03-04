/*
LeetCode Problem: https://leetcode.com/problems/robot-collisions/

Question: 2751. Robot Collisions

Problem Statement: There are n 1-indexed robots, each having a position on a line, health, and movement direction.

You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L' for left or 'R' for right). All integers in positions are unique.

All robots start moving on the line simultaneously at the same speed in their given directions. If two robots ever share the same position while moving, they will collide.

If two robots collide, the robot with lower health is removed from the line, and the health of the other robot decreases by one. The surviving robot continues in the same direction it was going. If both robots have the same health, they are both removed from the line.

Your task is to determine the health of the robots that survive the collisions, in the same order that the robots were given, i.e. final health of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.

Return an array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur.

Note: The positions may be unsorted.

Example 1:
Input: positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
Output: [2,17,9,15,10]
Explanation: No collision occurs in this example, since all robots are moving in the same direction. So, the health of the robots in order from the first robot is returned, [2, 17, 9, 15, 10].

Example 2:
Input: positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
Output: [14]
Explanation: There are 2 collisions in this example. Firstly, robot 1 and robot 2 will collide, and since both have the same health, they will be removed from the line. Next, robot 3 and robot 4 will collide and since robot 4's health is smaller, it gets removed, and robot 3's health becomes 15 - 1 = 14. Only robot 3 remains, so we return [14].

Example 3:
Input: positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
Output: []
Explanation: Robot 1 and robot 2 will collide and since both have the same health, they are both removed. Robot 3 and 4 will collide and since both have the same health, they are both removed. So, we return an empty array, [].

Constraints:

1 <= positions.length == healths.length == directions.length == n <= 10^5
1 <= positions[i], healths[i] <= 10^9
directions[i] == 'L' or directions[i] == 'R'
All values in positions are distinct
 */

/*
Approach: Sorting + Stack Simulation (Collision Resolution)

Goal:
- Determine the health of robots that survive after all collisions,
  preserving their original input order.

Core Idea:
- Sort robots by position so collisions can be processed in spatial order.
- Use a stack to keep robots moving to the right ('R').
- When a left-moving robot ('L') appears, simulate collisions with
  stack robots until one survives or both are destroyed.

Algorithm Steps:
1. Store robot information as {index, position, health, direction}.
2. Sort robots by position.
3. Traverse robots:
   - If direction is 'R', push to stack.
   - If direction is 'L', resolve collisions with stack top ('R'):
       a. Higher health robot survives and loses 1 health.
       b. Lower health robot is destroyed.
       c. Equal health → both destroyed.
4. Push surviving robots into stack.
5. After processing, collect survivors.
6. Sort survivors by original index.
7. Extract remaining health values.

Time Complexity:
- O(n log n) due to sorting.

Space Complexity:
- O(n)

Result:
- Returns the health of robots that remain after all collisions.
*/

package StacksAndQueues.Hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class _2751_Robot_Collisions {
    // Method to find an array containing the health of the remaining robots (in the
    // order they were given in the input), after no further collisions can occur
    public static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        // Get the length of the arrays
        int length = positions.length;

        // Convert the directions string to the direction array
        char[] direction = directions.toCharArray();

        // Initialize the list of array integer
        ArrayList<int[]> list = new ArrayList<>(); // int[] = {index, position, health, direction}

        // Fill the list of arrays
        for (int index = 0; index < length; index++) {
            // Add to the list
            list.add(new int[] { index, positions[index], healths[index], direction[index] });
        }

        // Sort the list by their position
        list.sort(Comparator.comparingInt(key -> key[1]));

        // Initialize the stack of array integer
        Stack<int[]> stack = new Stack<>();

        // Iterate over the list
        for (int[] current : list) {
            // Variable to check if the current robot is destroyed
            boolean destroyed = false;

            // Check collision only if:
            // 1. Stack is not empty
            // 2. Top robot direction is 'R'
            // 3. Current robot direction is 'L'
            while (!stack.isEmpty() && stack.peek()[3] == 'R' && current[3] == 'L') {
                // Get the top value of the stack
                int[] top = stack.peek();

                // If top health is greater than current health
                if (top[2] > current[2]) {
                    // Decrease top health by 1
                    top[2]--;

                    // Current robot is destroyed
                    destroyed = true;

                    // Exit the loop
                    break;

                    // If top health is less than current health
                } else if (top[2] < current[2]) {
                    // Decrease current health by 1
                    current[2]--;

                    // Remove the top robot (it is destroyed)
                    stack.pop();

                    // If both have equal health
                } else {
                    // Remove the top robot
                    stack.pop();

                    // Current robot is destroyed
                    destroyed = true;

                    // Exit the loop
                    break;
                }
            }

            // If current robot is not destroyed, push it to the stack
            if (!destroyed) {
                stack.push(current);
            }
        }

        // Clear the list
        list.clear();

        // Add all the value from the stack to the list
        list.addAll(stack);

        // Sort the list by their index (original input order)
        list.sort(Comparator.comparingInt(key -> key[0]));

        // Initialize the list of integer for the result
        ArrayList<Integer> result = new ArrayList<>();

        // Add the health value from the list
        for (int[] l : list) {
            result.add(l[2]);
        }

        // Return the result list
        return result;
    }

    // Main method to test survivedRobotsHealths
    public static void main(String[] args) {
        int[] positions = new int[] { 3, 5, 2, 6 };
        int[] healths = new int[] { 10, 10, 15, 12 };
        String directions = "RLRL";

        List<Integer> result = survivedRobotsHealths(positions, healths, directions);

        System.out.println(
                "An array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur is : "
                        + result);
    }
}
