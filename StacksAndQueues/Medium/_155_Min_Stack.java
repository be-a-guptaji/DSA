/*
LeetCode Problem: http://leetcode.com/problems/min-stack/

Question: 155. Min Stack

Problem Statement: Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

Example 1:
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

Constraints:

-2^31 <= val <= 2^31 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 */

/*
Approach: We solve this problem using a stack that stores both the value and the current minimum at each push.

- Use a LinkedList<Node> where each Node stores:
    - value → the element pushed
    - minValue → the smallest value in the stack up to this point
- When pushing:
    - If the stack is empty, minValue = val.
    - Otherwise, minValue = min(val, stack.peek().minValue).
    - Push a new Node with (value, minValue).
- When popping:
    - Remove the top Node from the stack.
- When calling top():
    - Return stack.peek().value.
- When calling getMin():
    - Return stack.peek().minValue.
- This way, getMin() is O(1) because each Node already stores the minimum up to that point.

Time Complexity: O(1) — All operations (push, pop, top, getMin) take constant time.
Space Complexity: O(n) — We store an extra minValue for each element in the stack.
*/

package StacksAndQueues.Medium;

import java.util.LinkedList;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

class MinStack {
    private final LinkedList<Node> stack;

    public MinStack() {
        stack = new LinkedList<>();
    }

    public void push(int val) {
        Node lastNode = stack.peek();
        int min = (lastNode == null) ? val : Math.min(val, lastNode.minValue);
        stack.push(new Node(val, min));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().minValue;
    }

    // Helper Class for tracking
    private class Node {
        final int value, minValue;

        public Node(int value, int minValue) {
            this.value = value;
            this.minValue = minValue;
        }
    }
}

// Driver Class for the testing the MinStack Class
public class _155_Min_Stack {
    // Main method to test the custom stack
    public static void main(String[] args) {
        String[] operations = { "MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin" };

        int[][] value = { {}, { -2 }, { 0 }, { -3 }, {}, {}, {}, {} };

        // Instantiate the Minstack Class
        MinStack obj = new MinStack();

        for (int i = 1; i < operations.length; i++) {
            switch (operations[i]) {
                case "push" -> {
                    obj.push(value[i][0]);
                }
                case "pop" -> {
                    obj.pop();
                }
                case "top" -> {
                    obj.top();
                }
                case "getMin" -> {
                    obj.getMin();
                }
            }
        }
    }
}