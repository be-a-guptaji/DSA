/*
LeetCode Problem: https://leetcode.com/problems/design-circular-queue/

Question: 622. Design Circular Queue

Problem Statement: Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

Implement the MyCircularQueue class:

MyCircularQueue(k) Initializes the object with the size of the queue to be k.
int Front() Gets the front item from the queue. If the queue is empty, return -1.
int Rear() Gets the last item from the queue. If the queue is empty, return -1.
boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
boolean isEmpty() Checks whether the circular queue is empty or not.
boolean isFull() Checks whether the circular queue is full or not.
You must solve the problem without using the built-in queue data structure in your programming language. 

Example 1:
Input
["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 3, true, true, true, 4]

Explanation
MyCircularQueue myCircularQueue = new MyCircularQueue(3);
myCircularQueue.enQueue(1); // return True
myCircularQueue.enQueue(2); // return True
myCircularQueue.enQueue(3); // return True
myCircularQueue.enQueue(4); // return False
myCircularQueue.Rear();     // return 3
myCircularQueue.isFull();   // return True
myCircularQueue.deQueue();  // return True
myCircularQueue.enQueue(4); // return True
myCircularQueue.Rear();     // return 4

Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 */
/*
Approach:
1. We need to implement a circular queue that supports the operations:
     • enQueue       → insert an element at the rear
     • deQueue       → delete the element at the front
     • Front         → return the front element
     • Rear          → return the rear element
     • isEmpty       → check if queue is empty
     • isFull        → check if queue is full

2. To achieve this, we use a fixed-size array of length k along with:
     • front → index of the current front element
     • rear  → index of the last inserted element
     • size  → number of elements currently stored
     • capacity → maximum size of the queue

3. Insert operation enQueue(value):
     • If size == capacity → queue is full, return false
     • Update rear = (rear + 1) % capacity to wrap around circularly
     • Store the value at queue[rear]
     • Increment size

4. Delete operation deQueue():
     • If size == 0 → queue is empty, return false
     • Move front to (front + 1) % capacity
     • Decrement size

5. Front and Rear operations:
     • If queue is empty → return -1
     • Front returns queue[front]
     • Rear returns queue[rear]

6. isEmpty and isFull:
     • isEmpty → size == 0
     • isFull  → size == capacity

7. Using modulo indexing ensures circular behavior so the queue reuses freed space.
   This avoids shifting elements, keeping operations efficient.

Time Complexity: All operations run in O(1) time.
Space Complexity: O(k) for storing the queue elements.
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.List;

public class _622_Design_Circular_Queue {
    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */

    // Class to make the MyCircularQueue
    private static class MyCircularQueue {
        // Initialize the pointers for front and rear
        private int front;
        private int rear;

        // Initialize the capacity variable
        private final int capacity;

        // Initialize the size variable
        private int size;

        // Initialize the array for the queue
        private final int[] queue;

        public MyCircularQueue(int k) {
            // Set the front and rear variable
            this.front = 0;
            this.rear = -1;

            // Set the capacity of the queue
            this.capacity = k;

            // Set the size of the queue
            this.size = 0;

            // Set the queue array to size k
            this.queue = new int[k];
        }

        public boolean enQueue(int value) {
            // If queue is full then return false
            if (this.isFull()) {
                return false;
            }

            // If queue is not full then add the value to the queue
            this.rear = (this.rear + 1) % this.capacity;
            this.queue[this.rear] = value;

            // Increment the size of the queue
            this.size++;

            // Return true in the end
            return true;
        }

        public boolean deQueue() {
            // If queue is empty then return false
            if (this.isEmpty()) {
                return false;
            }

            // If queue is not empty then move the front pointer forward
            this.front = (this.front + 1) % this.capacity;

            // Decrement the size of the queue
            this.size--;

            // Return true in the end
            return true;
        }

        public int Front() {
            // If queue is empty then return -1
            if (this.isEmpty()) {
                return -1;
            }

            // Return front variable value in the queue
            return this.queue[this.front];
        }

        public int Rear() {
            // If queue is empty then return -1
            if (this.isEmpty()) {
                return -1;
            }

            // Return rear variable value in the queue
            return this.queue[this.rear];

        }

        public boolean isEmpty() {
            // Return the size == 0
            return this.size == 0;
        }

        public boolean isFull() {
            // Return the capacity == size
            return this.capacity == this.size;
        }
    }

    // Main method to test MyCircularQueue
    public static void main(String[] args) {

        String[] operations = {
                "MyCircularQueue", "enQueue", "enQueue", "enQueue",
                "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"
        };

        List<int[]> values = new ArrayList<>();
        values.add(new int[] { 3 });
        values.add(new int[] { 1 });
        values.add(new int[] { 2 });
        values.add(new int[] { 3 });
        values.add(new int[] { 4 });
        values.add(new int[] {});
        values.add(new int[] {});
        values.add(new int[] {});
        values.add(new int[] { 4 });
        values.add(new int[] {});

        // Create the CircularQueue object
        MyCircularQueue circularQueue = new MyCircularQueue(values.get(0)[0]);

        // Loop through operations
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            switch (op) {
                case "MyCircularQueue" -> {
                    circularQueue = new MyCircularQueue(values.get(i)[0]);
                    System.out.println("null");
                }
                case "enQueue" -> {
                    System.out.println(circularQueue.enQueue(values.get(i)[0]));
                }
                case "deQueue" -> {
                    System.out.println(circularQueue.deQueue());
                }
                case "Rear" -> {
                    System.out.println(circularQueue.Rear());
                }
                case "Front" -> {
                    System.out.println(circularQueue.Front());
                }
                case "isFull" -> {
                    System.out.println(circularQueue.isFull());
                }
                case "isEmpty" -> {
                    System.out.println(circularQueue.isEmpty());
                }
            }
        }
    }
}
