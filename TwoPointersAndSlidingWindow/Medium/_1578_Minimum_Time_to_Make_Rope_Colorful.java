/*
LeetCode Problem: https://leetcode.com/problems/minimum-time-to-make-rope-colorful/

Question: 1578. Minimum Time to Make Rope Colorful

Problem Statement: Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.

Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.

Return the minimum time Bob needs to make the rope colorful.

Example 1:
Input: colors = "abaac", neededTime = [1,2,3,4,5]
Output: 3
Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
Bob can remove the blue balloon at index 2. This takes 3 seconds.
There are no longer two consecutive balloons of the same color. Total time = 3.

Example 2:
Input: colors = "abc", neededTime = [1,2,3]
Output: 0
Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.

Example 3:
Input: colors = "aabaa", neededTime = [1,2,3,4,1]
Output: 2
Explanation: Bob will remove the balloons at indices 0 and 4. Each balloons takes 1 second to remove.
There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.

Constraints:

n == colors.length == neededTime.length
1 <= n <= 10^5
1 <= neededTime[i] <= 10^4
colors contains only lowercase English letters.
 */

/*
Approach: Greedy – Keep the Maximum, Remove the Rest

Goal:
- Make the rope colorful so that no two adjacent balloons have the same color.
- Removing a balloon costs `neededTime[i]`.
- Minimize total removal time.

Key Observation:
- For any group of consecutive balloons with the same color,
  we must remove all except ONE.
- To minimize cost, we keep the balloon with the maximum removal time
  and remove the others.

Strategy (Two-Pointer Greedy):

1. Traverse the string from left to right.
2. Maintain `previous`:
   - Index of the balloon currently being kept in a group.
3. For each `next` balloon:
   - If colors are different:
       → start a new group (`previous = next`)
   - If colors are the same:
       → remove the one with smaller `neededTime`
       → add its cost to `minimumTime`
       → keep the balloon with larger `neededTime` as `previous`

Why This Works:
- Exactly one balloon per color group must remain.
- Keeping the most expensive one minimizes total removal cost.
- Greedy choice is locally optimal and globally optimal.

Example:
colors = "aabaa"
neededTime = [1,2,3,4,1]

Group "aa":
- remove 1, keep 2
Group "aa":
- remove 3, keep 4
Total cost = 1 + 3 = 4

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the minimum time required to make the rope colorful.
*/

package TwoPointersAndSlidingWindow.Medium;

public class _1578_Minimum_Time_to_Make_Rope_Colorful {
    // Method to find the minimum time Bob needs to make the rope colorful
    public static int minCost(String colors, int[] neededTime) {
        // Convert the string into the character array
        char[] color = colors.toCharArray();

        // Initialize two pointers for the slow and fast pointer
        int previous = 0;

        // Initialize the minimum time to make rope colorful
        int minimumTime = 0;

        // Iterater over the colors array
        for (int next = 1; next < color.length; next++) {
            // If color matches then update the minimum time accordingly
            if (color[previous] == color[next]) {
                if (neededTime[previous] < neededTime[next]) {
                    minimumTime += neededTime[previous];
                    previous = next;
                } else {
                    minimumTime += neededTime[next];
                }
            } else {
                // If color does not match then move the pointer to the next value
                previous = next;
            }
        }

        // Return minimum time
        return minimumTime;
    }

    // Main method to test minCost
    public static void main(String[] args) {
        String colors = "aabaa";
        int[] neededTime = new int[] { 1, 2, 3, 4, 1 };

        int result = minCost(colors, neededTime);

        System.out.println("The minimum time Bob needs to make the rope colorful is : " + result);
    }
}
