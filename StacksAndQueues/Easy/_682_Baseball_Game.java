/*
LeetCode Problem: https://leetcode.com/problems/baseball-game/

Question: 682. Baseball Game

Problem Statement: You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.

You are given a list of strings operations, where operations[i] is the ith operation you must apply to the record and is one of the following:

An integer x.
Record a new score of x.
'+'.
Record a new score that is the sum of the previous two scores.
'D'.
Record a new score that is the double of the previous score.
'C'.
Invalidate the previous score, removing it from the record.
Return the sum of all the scores on the record after applying all the operations.

The test cases are generated such that the answer and all intermediate calculations fit in a 32-bit integer and that all operations are valid.

Example 1:
Input: ops = ["5","2","C","D","+"]
Output: 30
Explanation:
"5" - Add 5 to the record, record is now [5].
"2" - Add 2 to the record, record is now [5, 2].
"C" - Invalidate and remove the previous score, record is now [5].
"D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.

Example 2:
Input: ops = ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation:
"5" - Add 5 to the record, record is now [5].
"-2" - Add -2 to the record, record is now [5, -2].
"4" - Add 4 to the record, record is now [5, -2, 4].
"C" - Invalidate and remove the previous score, record is now [5, -2].
"D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
"9" - Add 9 to the record, record is now [5, -2, -4, 9].
"+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
"+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.

Example 3:
Input: ops = ["1","C"]
Output: 0
Explanation:
"1" - Add 1 to the record, record is now [1].
"C" - Invalidate and remove the previous score, record is now [].
Since the record is empty, the total sum is 0.

Constraints:

1 <= operations.length <= 1000
operations[i] is "C", "D", "+", or a string representing an integer in the range [-3 * 10^4, 3 * 10^4].
For operation "+", there will always be at least two previous scores on the record.
For operations "C" and "D", there will always be at least one previous score on the record.
 */

/*
Approach: Stack Simulation

Goal:
- Process a list of operations representing baseball game scores.
- Return the total score after applying all operations.

Operations Meaning:
- Integer value → Add new score.
- "+" → Add sum of last two valid scores.
- "D" → Add double of last valid score.
- "C" → Remove last valid score.

Key Idea:
- Use a stack to keep track of valid scores.
- Each operation modifies the stack accordingly.
- Final answer is the sum of remaining stack values.

Algorithm Steps:

1. Initialize:
   - int[] stack = new int[operations.length]
   - stackPointer = 0

2. Iterate through operations:

   Case 1: "C"
       → Remove last score
       → stackPointer--

   Case 2: "D"
       → Double last score
       → stack[stackPointer] = stack[stackPointer - 1] * 2
       → stackPointer++

   Case 3: "+"
       → Sum last two scores
       → stack[stackPointer] = stack[stackPointer - 1] + stack[stackPointer - 2]
       → stackPointer++

   Case 4: Integer
       → Parse and push onto stack
       → stack[stackPointer++] = value

3. Sum all values from index 0 to stackPointer - 1.

Why This Works:
- Stack naturally models “last valid score”.
- All operations depend only on recent values.
- Efficient and direct simulation.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns total score after applying all operations.
*/

package StacksAndQueues.Easy;

public class _682_Baseball_Game {
    // Method to find the sum of all the scores on the record after applying all the
    // operations
    public static int calPoints(String[] operations) {
        // Initialize the new stack for the operations
        int[] stack = new int[operations.length];

        // Initialize the stack pointer
        int stackPointer = 0;

        // Iterate over the operations for preforming operations
        for (String operation : operations) {
            // Preform the operation
            switch (operation) {
                case "C" -> {
                    stackPointer--;
                }
                case "D" -> {
                    stack[stackPointer++] = stack[stackPointer - 2] << 1;
                }
                case "+" -> {
                    stack[stackPointer++] = stack[stackPointer - 2] + stack[stackPointer - 3];
                }
                default -> {
                    stack[stackPointer++] = Integer.parseInt(operation);
                }
            }
        }

        // Initailize the total sum of the variable for returning
        int totalSum = 0;

        // Accumulate the total sum
        for (int i = 0; i < stackPointer; i++) {
            totalSum += stack[i];
        }

        // Return the total sum
        return totalSum;
    }

    // Main method to test calPoints
    public static void main(String[] args) {
        String[] operations = new String[] { "5", "2", "C", "D", "+" };

        int result = calPoints(operations);

        System.out.println("The sum of all the scores on the record after applying all the operations is : " + result);
    }
}
