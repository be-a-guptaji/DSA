/*
LeetCode Problem: https://leetcode.com/problems/validate-stack-sequences/description/

Question: 2390. Removing Stars From a String

Problem Statement: Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.

Example 1:
Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4),
pop() -> 4,
push(5),
pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

Example 2:
Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.

Constraints:

1 <= pushed.length <= 1000
0 <= pushed[i] <= 1000
All the elements of pushed are unique.
popped.length == pushed.length
popped is a permutation of pushed.
 */

/*
Approach: Stack Simulation

Goal:
- Determine whether the `popped` sequence can result from
  push/pop operations applied to `pushed`.

Key Idea:
- Simulate the push process.
- Whenever the top of the stack matches the next element
  in `popped`, perform a pop immediately.
- If at the end all elements are popped correctly,
  the sequence is valid.

Algorithm:

1. Initialize:
   - stack[] of size pushed.length
   - stackPointer = -1
   - poppedPointer = 0

2. Iterate over pushed:
   - Push current element onto stack.
   - After each push:
       While:
         stack not empty AND
         top == popped[poppedPointer]
       → pop from stack
       → increment poppedPointer

3. After processing all pushed elements:
   - If poppedPointer == popped.length → valid
   - Otherwise → invalid

Why This Works:
- We mimic the exact behavior of a real stack.
- Immediate popping ensures correct order checking.
- Each element is pushed once and popped once.

Example:
pushed = [1,2,3,4,5]
popped = [4,5,3,2,1]

Simulation:
push 1
push 2
push 3
push 4 → pop 4
push 5 → pop 5
pop 3
pop 2
pop 1 → valid

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns true if `popped` is a valid stack pop sequence.
*/

package StacksAndQueues.Medium;

public class _946_Validate_Stack_Sequences {
    // Method to determine if this could have been the result of a sequence of push
    // and pop operations on an initially empty stack
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        // Initialzie the stack for the pushed array
        int[] stack = new int[pushed.length];

        // Initialize the stack pointer
        int stackPointer = -1;

        // Intiailize the pointer for the popped array
        int poppedPointer = 0;

        // Iterate over the pushed array
        for (int i = 0; i < pushed.length; i++) {
            // Push the value in the stack
            stack[++stackPointer] = pushed[i];

            // If stack pointer is in bound and top value of the stack is equal to the
            // popped value then decrement the stack pointer and increment the pooped
            // pointer
            while (stackPointer > -1 && poppedPointer < popped.length && stack[stackPointer] == popped[poppedPointer]) {
                // Decrement the stack pointer
                stackPointer--;

                // Increment the popped pointer
                poppedPointer++;
            }
        }

        // Return condition in the end
        return poppedPointer == popped.length;
    }

    // Main method to test validateStackSequences
    public static void main(String[] args) {
        int[] pushed = new int[] { 1, 2, 3, 4, 5 };
        int[] popped = new int[] { 4, 5, 3, 2, 1 };

        boolean result = validateStackSequences(pushed, popped);

        System.out.println("This could" + (result ? " " : " not ")
                + "have been the result of a sequence of push and pop operations on an initially empty stack.");
    }
}
