/*
LeetCode Problem: https://leetcode.com/problems/open-the-lock/

Question: 752. Open the Lock

Problem Statement: You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation: 
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".

Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.

Constraints:
1 <= deadends.length <= 500
deadends[i].length == 4
target.length == 4
target will not be in the list deadends.
target and deadends[i] consist of digits only.
*/

/*
Approach: BFS on 4-digit Lock State with Deadend Pruning
Goal:
- Find the minimum number of turns to reach the
  target combination from "0000", where each turn
  rotates one wheel by one position (up or down),
  without passing through any deadend combination.
Core Idea:
- Each lock state is a 4-digit combination; BFS
  explores states level by level (one turn per
  level), guaranteeing the first time the target
  is reached it is via the minimum turns.
- Encode each combination as a 4-digit integer
  (0-9999) for O(1) visited/deadend lookup in a
  HashSet.
- Each state has exactly 8 neighbors (4 wheels x
  2 directions); generate them via modular
  arithmetic to handle wrap-around (9->0, 0->9).
Algorithm Steps:
1. Convert all deadends to integers and store in
   deadendSet.
2. If 0 (initial state "0000") is a deadend,
   return -1 immediately.
3. Enqueue "0000" and mark 0 as visited by adding
   it to deadendSet.
4. BFS level by level (each level = one turn):
   a. For each state in the current level, if it
      equals targetLock return turn.
   b. Generate 8 neighbors via nextCombination
      (each wheel +1 and -1 mod 10).
   c. Enqueue and mark unvisited, non-deadend
      neighbors.
   d. Increment turn after processing the level.
5. Return -1 if target is unreachable.
Why It Works:
- BFS level-by-level processing ensures the first
  time the target is dequeued, the turn count is
  minimal.
- Reusing deadendSet as the visited set avoids a
  separate boolean array and correctly prunes both
  deadends and already-visited states in O(1).
- Integer encoding of states reduces memory
  overhead and speeds up set operations compared
  to string hashing.
Time Complexity:
- O(10^4)
since there are at most 10,000 distinct lock
states, each processed once with O(8) neighbor
generation per state.
Space Complexity:
- O(10^4)
for the BFS queue and deadendSet storing at most
10,000 states.
Result:
- Returns the minimum number of turns to reach
  the target, or -1 if unreachable.
*/

package Graphs.Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the minimum total number of turns required to open the lock
  public int openLock(String[] deadends, String target) {
    // Initialize the hashset for the deadends
    HashSet<Integer> deadendSet = new HashSet<>();

    // Fill the deadendSet
    for (int i = 0; i < deadends.length; i++) {
      deadendSet.add(this.stringToInteger(deadends[i]));
    }

    // Initialize the target variable
    int targetLock = this.stringToInteger(target);

    // If 0 is in the deadendSet then return -1
    if (deadendSet.contains(0)) {
      return -1;
    }

    // Initialize the turns variable
    int turn = 0;

    // Initialize the array for the integer
    char[] number = new char[] { '0', '0', '0', '0' };

    // Initialize the queue for the bfs
    Queue<char[]> queue = new ArrayDeque<>();

    // Add the default string to the queue
    queue.offer(number);

    // Add the 0 to the deadendSet
    deadendSet.add(0);

    // Iterate over the queue
    while (!queue.isEmpty()) {
      // Initialize the size of the queue
      int size = queue.size();

      // Iterate for the size of the queue
      for (int i = 0; i < size; i++) {
        // Get the queue value
        char[] temp = queue.poll();

        // Get the value from the
        int value = this.characterToInteger(temp);

        // Return if value is equal to the lock
        if (value == targetLock) {
          return turn;
        }

        // Initialize the next combination
        ArrayList<char[]> combination = this.nextCombination(temp);

        // Iterate over the next combination
        for (int j = 0; j < combination.size(); j++) {
          // Initialize the temp array
          char[] t = combination.get(j);

          // Initialize the nextValue
          int nextValue = this.characterToInteger(t);

          // If we have already deadendSet the value then skip the iteration
          if (!deadendSet.contains(nextValue)) {
            // Add the value to the queue
            queue.offer(t);

            // Add the value to the deadendSet set
            deadendSet.add(nextValue);
          }
        }
      }

      // Increment the turn variable
      turn++;
    }

    // Return -1 in the end
    return -1;
  }

  // Helper method to covert the string to the number
  private int stringToInteger(String deadend) {
    return ((deadend.charAt(0) - '0') * 1000) + ((deadend.charAt(1) - '0') * 100) + ((deadend.charAt(2) - '0') * 10)
        + ((deadend.charAt(3) - '0'));
  }

  // Helper method to covert the character array to the number
  private int characterToInteger(char[] number) {
    return ((number[0] - '0') * 1000) + ((number[1] - '0') * 100) + ((number[2] - '0') * 10) + (number[3] - '0');
  }

  // Helper method to generate the next combination
  private ArrayList<char[]> nextCombination(char[] value) {
    // Initialize the combination list
    ArrayList<char[]> combination = new ArrayList<>();

    // Iterate over the value
    for (int i = 0; i < 4; i++) {
      // Initialize the temp character array
      char[] temp = new char[] { value[0], value[1], value[2], value[3] };

      // Make the next combination
      temp[i] = (char) ((((temp[i] - '0') + 1) % 10) + '0');

      // Add the temp to the combination list
      combination.add(new char[] { temp[0], temp[1], temp[2], temp[3] });

      // Reset the temp character array
      temp = new char[] { value[0], value[1], value[2], value[3] };

      // Make the next combination
      temp[i] = (char) (((((temp[i] - '0') + 10) - 1) % 10) + '0');

      // Add the temp to the combination list
      combination.add(new char[] { temp[0], temp[1], temp[2], temp[3] });
    }

    // Return the combination list
    return combination;
  }
}

public class _752_Open_the_Lock {
  // Main method to test openLock
  public static void main(String[] args) {
    String[] deadends = new String[] {};
    String target = "";

    int result = new Solution().openLock(deadends, target);

    System.out.println("The minimum total number of turns required to open the lock is : " + result);
  }
}
