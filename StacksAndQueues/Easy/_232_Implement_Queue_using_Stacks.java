/*
LeetCode Problem: https://leetcode.com/problems/implement-queue-using-stacks/

Question: 232. Implement Queue using Stacks

Problem Statement: Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

Example 1:
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.

Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
 */

/*
Approach: We use two stacks (`input` and `output`) to simulate queue behavior:
- push(x): Always push to the `input` stack.
- pop(): If `output` is empty, transfer all elements from `input` to `output` to reverse their order, then pop from `output`.
- peek(): If `output` is empty, transfer all elements from `input` to `output`, then peek from `output`.
- empty(): Queue is empty if both `input` and `output` are empty.

By transferring elements only when `output` is empty, we avoid repeated movement of elements, ensuring each element is moved at most once between the two stacks.

Time Complexity:
- push(): O(1)
- pop(): Amortized O(1) — Each element is moved between stacks at most once.
- peek(): Amortized O(1)
- empty(): O(1)

Space Complexity:
- O(n) — where n is the total number of elements stored in the queue.
*/
package StacksAndQueues.Easy;

import java.util.Stack;

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

class MyQueue {
    private final Stack<Integer> input, output;

    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        moveIfNeeded();
        return output.pop();
    }

    public int peek() {
        moveIfNeeded();
        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    // Transfer only if output stack is empty
    private void moveIfNeeded() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }
}

// Driver Class for the testing the MyQueue Class
public class _232_Implement_Queue_using_Stacks {
    // Main method to test the custom queue
    public static void main(String[] args) {
        String[] operations = { "MyQueue", "push", "push", "peek", "pop", "empty" };

        int[][] value = { {}, { 1 }, { 2 }, {}, {}, {} };

        // Instantiate the MyQueue Class
        MyQueue obj = new MyQueue();

        for (int i = 1; i < operations.length; i++) {
            switch (operations[i]) {
                case "push" -> {
                    obj.push(value[i][0]);
                }
                case "pop" -> {
                    obj.pop();
                }
                case "peek" -> {
                    obj.peek();
                }
                case "empty" -> {
                    obj.empty();
                }
            }
        }

    }
}
