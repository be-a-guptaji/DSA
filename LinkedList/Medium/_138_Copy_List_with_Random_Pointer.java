/*
LeetCode Problem: https://leetcode.com/problems/copy-list-with-random-pointer/

Question: 138. Copy List with Random Pointer

Problem Statement: A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

Constraints:

0 <= n <= 1000
-10^4 <= Node.val <= 10^4
Node.random is null or is pointing to some node in the linked list.
*/

/*
Approach:
1. First pass:
   - Traverse the original list.
   - For each node, create its copy and insert it right after the original node.
   - This way every original node is followed by its copy.
     (e.g., A → A' → B → B' → C → C')

2. Second pass:
   - Traverse the list again.
   - For each original node:
       - If it has a random pointer to some node R,
         then its copy node (original.next) should have random = R.next
         (which is the copy of R).

3. Third pass:
   - Separate the interleaved list into two lists:
       - Restore the original list by skipping the copies.
       - Extract the copy list by linking the copied nodes together.

Time Complexity: O(n), because we make three passes through the list.
Space Complexity: O(1), since no extra data structures are used (only pointers).
*/

package LinkedList.Medium;

public class _138_Copy_List_with_Random_Pointer {
    /*
     * // Definition for a Node.
     * class Node {
     * int val;
     * Node next;
     * Node random;
     * 
     * public Node(int val) {
     * this.val = val;
     * this.next = null;
     * this.random = null;
     * }
     * }
     */

    // Method to make a deep copy of the list with random nodes
    public static Node copyRandomList(Node head) {
        // Handle the case when the input list is empty
        if (head == null) {
            return null;
        }

        // Insert a new copied node right after each original node
        // This helps in linking random pointers without using extra space
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }

        // Copy the random pointers from original nodes to their corresponding copies
        current = head;

        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // Separate the copied list from the original list
        // and restore the original list back to its initial structure
        Node dummy = new Node(Integer.MIN_VALUE);
        Node copy = dummy;
        current = head;

        while (current != null) {
            copy.next = current.next;
            copy = copy.next;

            current.next = copy.next;
            current = current.next;
        }

        // Return the head of the deep copied list
        return dummy.next;
    }

    // Function to convert the array into a Node list with random pointers
    public static Node makelist(int[][] list) {
        if (list.length == 0) {
            return null; // Handle empty array
        }

        // Step 1: Create all nodes and store them in an array
        Node[] nodes = new Node[list.length];
        for (int i = 0; i < list.length; i++) {
            nodes[i] = new Node(list[i][0]); // create node with value
        }

        // Step 2: Link the "next" pointers
        for (int i = 0; i < list.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        // Step 3: Link the "random" pointers
        for (int i = 0; i < list.length; i++) {
            int randomIndex = list[i][1];
            if (randomIndex != -1) { // -1 means no random pointer
                nodes[i].random = nodes[randomIndex];
            }
        }

        // Step 4: Return the head
        return nodes[0];
    }

    // Mock class for making the Node class
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // Main method to test copyRandomList
    public static void main(String[] args) {
        int[][] head = { { 7, -1 }, { 13, 0 }, { 11, 4 }, { 10, 2 }, { 1, 0 } };

        Node result = copyRandomList(makelist(head));

        System.out.print("The partion linked list is : ");

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
