/*
LeetCode Problem: https://leetcode.com/problems/design-circular-deque/

Question: 641. Design Circular Deque

Problem Statement: Design your implementation of the circular double-ended queue (deque).

Implement the MyCircularDeque class:

MyCircularDeque(int k) Initializes the deque with a maximum size of k.
boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
boolean isEmpty() Returns true if the deque is empty, or false otherwise.
boolean isFull() Returns true if the deque is full, or false otherwise.

Example 1:
Input
["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 2, true, true, true, 4]

Explanation
MyCircularDeque myCircularDeque = new MyCircularDeque(3);
myCircularDeque.insertLast(1);  // return True
myCircularDeque.insertLast(2);  // return True
myCircularDeque.insertFront(3); // return True
myCircularDeque.insertFront(4); // return False, the queue is full.
myCircularDeque.getRear();      // return 2
myCircularDeque.isFull();       // return True
myCircularDeque.deleteLast();   // return True
myCircularDeque.insertFront(4); // return True
myCircularDeque.getFront();     // return 4

Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */

/*
Approach:

1. We are implementing a fixed-size **circular deque**, where insertions and deletions
   can happen at both the front and the rear in O(1) time.

2. To achieve this in constant time, we maintain:
      • front  → index of the current front element  
      • rear   → index of the current rear element  
      • size   → current number of elements  
      • capacity → maximum allowed elements  
      • deque[] → fixed array to store elements  

3. Circular movement is handled using modular arithmetic:
      • Move front backward → (front − 1 + capacity) % capacity  
      • Move rear forward   → (rear + 1) % capacity  
      • Similarly for deleting:
            front = (front + 1) % capacity  
            rear  = (rear − 1 + capacity) % capacity  

4. Special handling is required when **deque is empty**:
      • front and rear should point to the same valid slot  
      • inserting first element should NOT move pointers  

5. Operations:
      • insertFront(value)
            - Check if full  
            - If empty: place at front directly  
            - Else move front backward and insert  
      • insertLast(value)
            - Check if full  
            - If empty: place at rear directly  
            - Else move rear forward and insert  
      • deleteFront()
            - Check if empty  
            - Move front forward  
            - If deque becomes empty → reset front and rear to 0  
      • deleteLast()
            - Check if empty  
            - Move rear backward  
            - If deque becomes empty → reset pointers  
      • getFront() / getRear()
            - Return corresponding element or -1 if empty  
      • isEmpty() / isFull()
            - Check size conditions  

6. This design ensures:
      • O(1) time for all operations  
      • Correct handling of wrap-around indexing  
      • Correct state restoration when deque becomes empty  

Time Complexity: O(1) for every operation  

Space Complexity: O(k) where k is the size of the deque  
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.List;

public class _641_Design_Circular_Deque {
    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */

    // Class to make the MyCircularDeque
    private static class MyCircularDeque {
        // Initialize the pointers for front and rear
        private int front;
        private int rear;

        // Initialize the capacity variable
        private final int capacity;

        // Initialize the size variable
        private int size;

        // Initialize the array for the queue
        private final int[] deque;

        public MyCircularDeque(int k) {
            // Set the front and rear variable
            this.front = 0;
            this.rear = 0;

            // Set the capacity of the queue
            this.capacity = k;

            // Set the size of the queue
            this.size = 0;

            // Set the deque array to size k
            this.deque = new int[k];
        }

        public boolean insertFront(int value) {
            // If deque is full then return false
            if (this.isFull()) {
                return false;
            }

            // If deque is empty then set both front and rear to same position
            if (this.size == 0) {
                this.deque[this.front] = value;

                // Increment the size of the deque
                this.size++;

                // Return true
                return true;
            }

            // If deque is not empty then move the front pointer backward
            this.front = (this.capacity + this.front - 1) % this.capacity;

            // Add value to the deque
            this.deque[this.front] = value;

            // Increment the size of the deque
            this.size++;

            // Return true in the end
            return true;
        }

        public boolean insertLast(int value) {
            // If deque is full then return false
            if (this.isFull()) {
                return false;
            }

            // If deque is empty then set both front and rear to same position
            if (this.size == 0) {
                this.deque[this.rear] = value;

                // Increment the size of the deque
                this.size++;

                // Return true
                return true;
            }

            // If deque is not empty then add the value to the deque in the rear
            this.rear = (this.capacity + this.rear + 1) % this.capacity;

            // Add value to the deque
            this.deque[this.rear] = value;

            // Increment the size of the deque
            this.size++;

            // Return true in the end
            return true;
        }

        public boolean deleteFront() {
            // If deque is empty then return false else return true after moving the pointer
            if (this.isEmpty()) {
                return false;
            }

            // Move the front pointer forward
            this.front = (this.capacity + this.front + 1) % this.capacity;

            // Decrement the size of deque
            this.size--;

            // Reset pointers if deque becomes empty
            if (this.size == 0) {
                this.front = 0;
                this.rear = 0;
            }

            // Return true
            return true;
        }

        public boolean deleteLast() {
            // If deque is empty then return false else return true after moving the pointer
            if (this.isEmpty()) {
                return false;
            }

            // Move the rear pointer backward
            this.rear = (this.capacity + this.rear - 1) % this.capacity;

            // Decrement the size of deque
            this.size--;

            // Reset pointers if deque becomes empty
            if (this.size == 0) {
                this.front = 0;
                this.rear = 0;
            }

            // Return true
            return true;
        }

        public int getFront() {
            // If deque is empty then return -1 else return the front deque position number
            if (this.isEmpty()) {
                return -1;
            }

            // Return front variable value in the deque
            return this.deque[this.front];
        }

        public int getRear() {
            // If deque is empty then return -1 else return the rear deque position number
            if (this.isEmpty()) {
                return -1;
            }

            // Return rear variable value in the deque
            return this.deque[this.rear];
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

    // Main method to test MyCircularDeque
    public static void main(String[] args) {

        String[] operations = {
                "MyCircularDeque", "insertLast", "insertLast", "insertFront",
                "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"
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

        // Create the CircularDeque object
        MyCircularDeque deque = new MyCircularDeque(values.get(0)[0]);

        // Loop through operations
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            switch (op) {
                case "MyCircularDeque" -> {
                    deque = new MyCircularDeque(values.get(i)[0]);
                    System.out.println("null");
                }
                case "insertFront" -> {
                    System.out.println(deque.insertFront(values.get(i)[0]));
                }
                case "insertLast" -> {
                    System.out.println(deque.insertLast(values.get(i)[0]));
                }
                case "deleteFront" -> {
                    System.out.println(deque.deleteFront());
                }
                case "deleteLast" -> {
                    System.out.println(deque.deleteLast());
                }
                case "getFront" -> {
                    System.out.println(deque.getFront());
                }
                case "getRear" -> {
                    System.out.println(deque.getRear());
                }
                case "isFull" -> {
                    System.out.println(deque.isFull());
                }
                case "isEmpty" -> {
                    System.out.println(deque.isEmpty());
                }
            }
        }
    }
}
