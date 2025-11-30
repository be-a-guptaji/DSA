/*
LeetCode Problem: https://leetcode.com/problems/maximum-frequency-stack/

Question: 895. Maximum Frequency Stack

Problem Statement: Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.

Implement the FreqStack class:

FreqStack() constructs an empty frequency stack.
void push(int val) pushes an integer val onto the top of the stack.
int pop() removes and returns the most frequent element in the stack.
If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.

Example 1:
Input
["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
Output
[null, null, null, null, null, null, null, 5, 7, 5, 4]

Explanation
FreqStack freqStack = new FreqStack();
freqStack.push(5); // The stack is [5]
freqStack.push(7); // The stack is [5,7]
freqStack.push(5); // The stack is [5,7,5]
freqStack.push(7); // The stack is [5,7,5,7]
freqStack.push(4); // The stack is [5,7,5,7,4]
freqStack.push(5); // The stack is [5,7,5,7,4,5]
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].

Constraints:

0 <= val <= 10^9
At most 2 * 10^4 calls will be made to push and pop.
It is guaranteed that there will be at least one element in the stack before calling pop.
 */

/*
Approach:
1. The goal is to design a stack-like data structure that supports:
      • push(val) → push an element into the stack
      • pop()     → remove and return the most frequent element
   If multiple elements have the same frequency, the most recently added
   element among them must be removed first.

2. To achieve this, we use two HashMaps:
      • frequency:
           Tracks how many times each value appears.
           Format → value → frequency count
      • frequencyStack:
           Maps each frequency to a stack that stores all values
           having that exact frequency.
           Format → frequency → stack of values

3. We also maintain:
      • maxFrequency → keeps track of the maximum frequency present at any time.
        This allows us to pop in O(1) time.

4. push(val) operation:
      • Get the current frequency of val from the frequency map.
      • Increment the frequency by 1.
      • Update the frequency map with the new value.
      • Push the value into the stack corresponding to its new frequency.
      • Update maxFrequency if the new frequency is greater than the current max.

5. pop() operation:
      • Always pop from the stack corresponding to maxFrequency.
      • This ensures:
           a) Highest frequency element is removed.
           b) If multiple elements share that frequency, the most recent one
              is removed first due to stack behavior.
      • After popping:
           • Decrease the frequency of that value in the frequency map.
           • If the stack at maxFrequency becomes empty, decrease maxFrequency.

6. This design ensures:
      • push() works in O(1)
      • pop() works in O(1)
      • Correct handling of both frequency priority and recency.

Time Complexity:
      • push → O(1)
      • pop  → O(1)
Space Complexity:
      • O(n), for storing frequencies and multiple stacks.
*/

package SystemDesign.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class _895_Maximum_Frequency_Stack {
    /**
     * Your FreqStack object will be instantiated and called as such:
     * FreqStack obj = new FreqStack();
     * obj.push(val);
     * int param_2 = obj.pop();
     */

    // Class to make the FreqStack
    private static class FreqStack {
        // Initialize the HashMap for tracking the frequency of the numbers
        private final HashMap<Integer, Integer> frequency;

        // Initialize the HashMap for the tracking of the stack value
        private final HashMap<Integer, Stack<Integer>> frequencyStack;

        // Initialize the variable for the tracking of the highest frequency
        private int maxFrequency;

        public FreqStack() {
            // Initialize the HashMap for tracking the frequency of the number
            this.frequency = new HashMap<>();

            // Initialize the HashMap for tracking the stack value
            this.frequencyStack = new HashMap<>();

            // Initialize the variable for the tracking of the highest frequency
            this.maxFrequency = 0;
        }

        public void push(int val) {
            // Initialize the variable for the val frequency
            int valFrequency = 0;

            // Check the frequency of the val in the frequency HashMap
            if (this.frequency.containsKey(val)) {
                // Get the frequency of the val
                valFrequency = this.frequency.get(val);
            } else {
                // Add the frequency to the frequency HashMap
                this.frequency.put(val, 0);
            }

            // Incerement the value of the valFrequencyby one
            valFrequency++;

            // Update the frequency HashMap
            this.frequency.put(val, valFrequency);

            // Check in frequency stack if the value exist or not if it exist then add the
            // value to the frequency else make new stack and add value to that
            if (this.frequencyStack.containsKey(valFrequency)) {
                this.frequencyStack.get(valFrequency).push(val);
            } else {
                this.frequencyStack.put(valFrequency, new Stack<>());
                this.frequencyStack.get(valFrequency).push(val);
            }

            // Update the maxFrequency
            this.maxFrequency = Math.max(this.maxFrequency, valFrequency);
        }

        public int pop() {
            // Get the stack which has the maximum frequency
            Stack<Integer> stack = this.frequencyStack.get(this.maxFrequency);

            // Pop the most recent element from the stack
            int val = stack.pop();

            // Decrease the frequency of the popped value
            this.frequency.put(val, this.frequency.get(val) - 1);

            // If the current max frequency stack becomes empty then reduce maxFrequency
            if (stack.isEmpty()) {
                this.maxFrequency--;
            }

            // Return the popped value
            return val;
        }
    }

    // Main method to test FreqStack
    public static void main(String[] args) {

        String[] operations = {
                "FreqStack", "push", "push", "push", "push", "push", "push",
                "pop", "pop", "pop", "pop"
        };

        List<int[]> values = new ArrayList<>();

        values.add(new int[] {}); // FreqStack()
        values.add(new int[] { 5 }); // push(5)
        values.add(new int[] { 7 }); // push(7)
        values.add(new int[] { 5 }); // push(5)
        values.add(new int[] { 7 }); // push(7)
        values.add(new int[] { 4 }); // push(4)
        values.add(new int[] { 5 }); // push(5)
        values.add(new int[] {}); // pop()
        values.add(new int[] {}); // pop()
        values.add(new int[] {}); // pop()
        values.add(new int[] {}); // pop()

        // Create an instance of FreqStack
        FreqStack freqStack = new FreqStack();

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("FreqStack")) {
                // Create the new FreqStack instance
                freqStack = new FreqStack();
                System.out.println("null");
            }

            if (operation.equals("push")) {
                // Call the push method with values[i][0]
                freqStack.push(values.get(i)[0]);
                System.out.println("null");
            }

            if (operation.equals("pop")) {
                // Call the pop method and print the result
                System.out.println(freqStack.pop());
            }
        }
    }
}
