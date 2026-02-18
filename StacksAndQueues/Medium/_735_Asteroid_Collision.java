/*
LeetCode Problem: https://leetcode.com/problems/asteroid-collision/

Question: 735. Asteroid Collision

Problem Statement: We are given an array asteroids of integers representing asteroids in a row. The indices of the asteroid in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

Example 2:
Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.

Example 3:
Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.

Example 4:
Input: asteroids = [3,5,-6,2,-1,4]​​​​​​​
Output: [-6,2,4]
Explanation: The asteroid -6 makes the asteroid 3 and 5 explode, and then continues going left. On the other side, the asteroid 2 makes the asteroid -1 explode and then continues going right, without reaching asteroid 4.

Constraints:

2 <= asteroids.length <= 10^4
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */

/*
/*
Approach: Stack Simulation for Collision Resolution

Goal:
- Simulate asteroid collisions.
- Positive value → moving right.
- Negative value → moving left.
- Collision occurs only when:
      stack top > 0  AND  current < 0

Key Idea:
- Use a stack to keep surviving asteroids.
- For each asteroid:
    - Resolve all possible collisions with the stack top.
    - Push it only if it survives.

Collision Rules:

Case 1: |top| < |current|
    → top explodes (pop stack)
    → continue checking

Case 2: |top| == |current|
    → both explode
    → pop stack
    → current destroyed
    → stop

Case 3: |top| > |current|
    → current explodes
    → stop

Algorithm Steps:

1. Initialize stack array and stackPointer = -1.

2. For each asteroid:
   - While:
       stack not empty
       AND stack[top] > 0
       AND current < 0
       → resolve collision

   - If current survives
       → push to stack

3. Return stack[0...stackPointer].

Why This Works:
- Only opposite directions can collide.
- Each asteroid is pushed once and popped at most once.
- Correctly models chain collisions.

Example:
Input: [5, 10, -5]

Stack:
5 → [5]
10 → [5,10]
-5 collides with 10 → destroyed
Result: [5,10]

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns remaining asteroids after all collisions.
*/

package StacksAndQueues.Medium;

import java.util.Arrays;

public class _735_Asteroid_Collision {
    // Method to find the remaing asteroids in the space after collision
    public static int[] asteroidCollision(int[] asteroids) {
        // Initialize the stack array of size equal to asteroids array size
        int[] stack = new int[asteroids.length];

        // Initialize the stack pointer
        int stackPointer = -1;

        // Iterate over the asteroids array
        for (int index = 0; index < asteroids.length; index++) {
            // Store the current asteroid
            int currentAsteroid = asteroids[index];

            // Variable to check if current asteroid gets destroyed
            boolean isDestroyed = false;

            // Check for collision:
            // Collision happens only when top of stack is moving right (+)
            // and current asteroid is moving left (-)
            while (stackPointer >= 0 && currentAsteroid < 0 && stack[stackPointer] > 0) {
                // If top asteroid is smaller in magnitude, it gets destroyed
                if (Math.abs(stack[stackPointer]) < Math.abs(currentAsteroid)) {
                    // Remove the top asteroid from stack
                    stackPointer--;
                }
                // If both asteroids have equal magnitude, both get destroyed
                else if (Math.abs(stack[stackPointer]) == Math.abs(currentAsteroid)) {
                    // Remove top asteroid
                    stackPointer--;

                    // Mark current asteroid as destroyed
                    isDestroyed = true;

                    // Exit collision loop
                    break;
                }
                // If top asteroid is larger, current asteroid gets destroyed
                else {
                    // Mark current asteroid as destroyed
                    isDestroyed = true;

                    // Exit collision loop
                    break;
                }
            }

            // If current asteroid is not destroyed, push it onto stack
            if (!isDestroyed) {
                stack[++stackPointer] = currentAsteroid;
            }
        }

        // Return the copy of the stack from index 0 to stackPointer
        return Arrays.copyOf(stack, stackPointer + 1);
    }

    // Main method to test asteroidCollision
    public static void main(String[] args) {
        int[] asteroids = new int[] { 3, 5, -6, 2, -1, 4 };

        int[] result = asteroidCollision(asteroids);

        System.out.println("The remaing asteroids in the space after collision is : " + Arrays.toString(result));
    }
}
