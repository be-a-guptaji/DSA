/*
LeetCode Problem: https://leetcode.com/problems/implement-stack-using-queues/

Question: 225. Implement Stack using Queues

Problem Statement: Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.

Example 1:
Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]
Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.

Follow-up: Can you implement the stack using only one queue?
 */

/*
Approach: Implement Stack Using a Single Queue

Goal:
- Implement stack (LIFO) behavior using only a queue (FIFO).

Key Idea:
- When pushing a new element:
    1) Insert it into the queue.
    2) Rotate the existing elements behind it.
- This makes the most recently pushed element always stay at the front,
  allowing pop() to behave like a stack.

How Each Operation Works:

1) push(x):
   - Add x to the queue.
   - Rotate the previous elements:
       for (size - 1) times:
           queue.offer(queue.poll());
   - After rotation, x becomes the front element.

2) pop():
   - Remove and return the front element.
   - Since push ensures newest element is at front,
     this correctly simulates stack pop.

3) top():
   - Return front element (peek).
   - Represents top of stack.

4) empty():
   - Return whether queue is empty.

Why This Works:
- Queue normally removes oldest element first.
- By rotating after each push, we reposition the new element
  to behave as the stack top.

Time Complexity:
- push(): O(n)
- pop(): O(1)
- top(): O(1)
- empty(): O(1)

Space Complexity:
- O(n)

Result:
- Fully simulates stack behavior using a single queue.
*/

package StacksAndQueues.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _225_Implement_Stack_using_Queues {
    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */

    // Class to make the MyStack
    private static class MyStack {
        // Initialize the private variable for the queue
        private final Queue<Integer> queue;

        public MyStack() {
            // Initailize the queue
            this.queue = new LinkedList<>();
        }

        public void push(int x) {
            // Add the value to the queue
            this.queue.offer(x);

            // Reverse the queue
            for (int i = 0; i < this.queue.size() - 1; i++) {
                this.queue.offer(this.queue.poll());
            }
        }

        public int pop() {
            // Retrun the first element
            return this.queue.poll();
        }

        public int top() {
            // Retrun the first element
            return this.queue.peek();
        }

        public boolean empty() {
            // Return the queue empty condition
            return this.queue.isEmpty();
        }
    }

    // Main method to test MyStack
    public static void main(String[] args) {
        String[] operations = { "MyStack", "push", "push", "top", "pop", "empty" };

        List<int[]> values = new ArrayList<>();
        values.add(new int[] {});
        values.add(new int[] { 1 });
        values.add(new int[] { 2 });
        values.add(new int[] {});
        values.add(new int[] {});
        values.add(new int[] {});

        MyStack myStack = new MyStack();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("MyStack")) {
                myStack = new MyStack();
                System.out.println("null");
            }

            if (operation.equals("push")) {
                myStack.push(values.get(i)[0]);
                System.out.println("null");
            }

            if (operation.equals("pop")) {
                System.out.println(myStack.pop());
            }

            if (operation.equals("top")) {
                System.out.println(myStack.top());
            }

            if (operation.equals("empty")) {
                System.out.println(myStack.empty());
            }
        }
    }
}
