/*
LeetCode Problem: https://leetcode.com/problems/push-dominoes/

Question: 838. Push Dominoes

Problem Statement: There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.

Example 1:
Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.

Example 2:
Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."

Constraints:
n == dominoes.length
1 <= n <= 10^5
dominoes[i] is either 'L', 'R', or '.'.
*/

/*
Approach: Two-Pass Force Simulation

Goal:
- Simulate the final state of dominoes after applying pushes.

Core Idea:
- Each domino is affected by:
    1. Force from nearest 'R' on the left
    2. Force from nearest 'L' on the right
- Compute distances (forces) from both directions.
- Compare forces to determine final state.

Algorithm Steps:
1. Initialize two arrays:
   - right[i] → distance from nearest 'R' to the left
   - left[i]  → distance from nearest 'L' to the right
2. Left-to-right pass:
   - Track distance from last 'R'.
   - Reset on 'L'.
3. Right-to-left pass:
   - Track distance from last 'L'.
   - Reset on 'R'.
4. For each index:
   - If left[i] < right[i] → 'L'
   - If right[i] < left[i] → 'R'
   - If equal → remain '.'
5. Build and return final string.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns final configuration after all forces stabilize.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to suimulate the dominoes
  public String pushDominoes(String dominoes) {
    // Convert the dominoes string into the character array
    char[] dom = dominoes.toCharArray();

    // Initialize the length of the dom array
    int length = dom.length;

    // Initialize the left and right array for the forces
    int[] left = new int[length];
    int[] right = new int[length];

    // Initialize the force vairable
    int force = Integer.MAX_VALUE;

    // Simulate the right force
    for (int i = 0; i < length; i++) {
      // Update the force varible
      if (dom[i] == 'R') {
        force = 0;
      } else if (dom[i] == 'L') {
        force = Integer.MAX_VALUE;
      } else {
        force = force == Integer.MAX_VALUE ? Integer.MAX_VALUE : force + 1;
      }

      // Update the right[i] to the force
      right[i] = force;
    }

    // Reset the force variable
    force = Integer.MAX_VALUE;

    // Simulate the left force
    for (int i = length - 1; i != -1; i--) {
      // Update the force varible
      if (dom[i] == 'L') {
        force = 0;
      } else if (dom[i] == 'R') {
        force = Integer.MAX_VALUE;
      } else {
        force = force == Integer.MAX_VALUE ? Integer.MAX_VALUE : force + 1;
      }

      // Update the left[i] to the force
      left[i] = force;
    }

    // Update the dom character array
    for (int i = 0; i < length; i++) {
      if (left[i] < right[i]) {
        dom[i] = 'L';
      } else if (left[i] > right[i]) {
        dom[i] = 'R';
      }
    }

    // Return the dom array before converting it to string
    return new String(dom);
  }
}

public class _838_Push_Dominoes {
  // Main method to test pushDominoes
  public static void main(String[] args) {
    String dominoes = ".L.R...LR..L..";

    String result = new Solution().pushDominoes(dominoes);

    System.out.println("The dominoes after pushing is : " + result);
  }
}
