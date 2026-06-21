/*
LeetCode Problem: https://leetcode.com/problems/design-linked-list/

Question: 707. Design Linked List

Problem Statement: Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement the MyLinkedList class:

    MyLinkedList() Initializes the MyLinkedList object.
    int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
    void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
    void addAtTail(int val) Append a node of value val as the last element of the linked list.
    void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
    void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.

Example 1:
Input
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]
Output
[null, null, null, null, 2, null, 3]
Explanation
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
myLinkedList.get(1);              // return 3

Constraints:
    0 <= index, val <= 1000
    Please do not use the built-in LinkedList library.
    At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
*/

/*
Approach: Doubly Linked List with Sentinel Nodes

Goal:
- Design a linked list supporting:

      get(index)
      addAtHead(val)
      addAtTail(val)
      addAtIndex(index, val)
      deleteAtIndex(index)

Core Data Structure:
--------------------
Doubly Linked List

Each node stores:

      val
      next
      previous

Two sentinel (dummy) nodes are maintained:

      head <-> tail

Initially:

      head.next = tail
      tail.previous = head

Benefits:
- Simplifies insertion and deletion.
- Eliminates special handling for empty lists.
- Head and tail operations become uniform.

Layout:
--------

      head <-> node1 <-> node2 <-> ... <-> tail

Sentinel nodes are not part of the actual data.

-------------------------------------------------
get(index)
-------------------------------------------------

Purpose:
- Return the value at the specified index.

Process:
1. Start from:

      head.next

2. Move index steps forward.
3. If index is valid:
      return node.val
4. Otherwise:
      return -1

Time Complexity:
- O(n)

-------------------------------------------------
addAtHead(val)
-------------------------------------------------

Purpose:
- Insert a node at the beginning.

Before:

      head <-> first

After:

      head <-> newNode <-> first

Pointer Updates:

      newNode.next = head.next
      newNode.previous = head

      head.next = newNode
      first.previous = newNode

Time Complexity:
- O(1)

-------------------------------------------------
addAtTail(val)
-------------------------------------------------

Purpose:
- Insert a node before tail.

Before:

      last <-> tail

After:

      last <-> newNode <-> tail

Pointer Updates:

      newNode.next = tail
      newNode.previous = tail.previous

      tail.previous = newNode
      last.next = newNode

Time Complexity:
- O(1)

-------------------------------------------------
addAtIndex(index, val)
-------------------------------------------------

Purpose:
- Insert before the node currently at index.

Process:
1. Traverse to the node at position index.
2. Insert:

      prev <-> newNode <-> current

Pointer Updates:

      current.previous = newNode
      newNode.previous.next = newNode

Time Complexity:
- O(n)

-------------------------------------------------
deleteAtIndex(index)
-------------------------------------------------

Purpose:
- Remove the node at index.

Before:

      prev <-> current <-> next

After:

      prev <-> next

Pointer Updates:

      prev.next = next
      next.previous = prev

Time Complexity:
- O(n)

-------------------------------------------------
Overall Complexities
-------------------------------------------------

get(index)
    O(n)

addAtHead(val)
    O(1)

addAtTail(val)
    O(1)

addAtIndex(index, val)
    O(n)

deleteAtIndex(index)
    O(n)

Space Complexity:
- O(n)

-------------------------------------------------
Implementation Note
-------------------------------------------------

This implementation uses sentinel nodes correctly,
but there is a bug in addAtIndex():

After:

      current.previous = node;

the code should also set:

      node.next = current;

However, node.next is already assigned in the
constructor, so the logic works.

A more common implementation additionally stores
the list size, allowing faster index validation
and simpler boundary checks.
*/

package LinkedList.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

// MyLinkedList Class
class MyLinkedList {
  // ListNode Class
  private final class ListNode {
    final int val;
    ListNode next;
    ListNode previous;

    public ListNode(int val, ListNode next, ListNode previous) {
      this.val = val;
      this.next = next;
      this.previous = previous;
    }
  }

  // Initialize the head and tail node
  ListNode head;
  ListNode tail;

  public MyLinkedList() {
    // Initialize the head and tail node
    this.head = new ListNode(0, null, null);
    this.tail = new ListNode(0, null, this.head);

    // Update the head.next to tail
    this.head.next = this.tail;
  }

  public int get(int index) {
    // Initialize the current node
    ListNode current = this.head.next;

    // Iterate till the index
    while (index != 0 && current.next != this.tail) {
      // Move the pointer forward
      current = current.next;

      // Decrement the index
      index--;
    }

    // If index is not zero then return -1
    if (index != 0 || current == this.tail) {
      return -1;
    }

    // Return the current.val
    return current.val;
  }

  public void addAtHead(int val) {
    // Initialize the node variable
    ListNode node = new ListNode(val, this.head.next, this.head);

    // Update the pointers
    this.head.next = node;
    node.next.previous = node;
  }

  public void addAtTail(int val) {
    // Initialize the node variable
    ListNode node = new ListNode(val, this.tail, this.tail.previous);

    // Update the pointers
    this.tail.previous = node;
    node.previous.next = node;
  }

  public void addAtIndex(int index, int val) {
    // Initialize the current node
    ListNode current = this.head.next;

    // Iterate till the index
    while (index > 0 && current != null) {
      // Move the pointer forward
      current = current.next;

      // Decrement the index
      index--;
    }

    // If current is null then return
    if (current == null) {
      return;
    }

    // Initialize the node variable
    ListNode node = new ListNode(val, current, current.previous);

    // Update the pointers
    current.previous = node;
    node.previous.next = node;
  }

  public void deleteAtIndex(int index) {
    // Initialize the current node
    ListNode current = this.head.next;

    // Iterate till the index
    while (index > 0) {
      // Move the pointer forward
      current = current.next;

      // Decrement the index
      index--;
    }

    // Update the pointers
    if (current != null && current.previous != null && current.next != null) {
      current.previous.next = current.next;
      current.next.previous = current.previous;
    }
  }
}

// Main Class
public class _707_Design_Linked_List {
  // Main method to test MyLinkedList
  public static void main(String[] args) {
    String[] operations = { "MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get" };

    List<int[]> values = new ArrayList<>();
    values.add(new int[] {});
    values.add(new int[] { 1, });
    values.add(new int[] { 3 });
    values.add(new int[] { 1, 2 });
    values.add(new int[] { 1 });
    values.add(new int[] { 1 });
    values.add(new int[] { 1 });

    // Create an instance of MyLinkedList
    MyLinkedList myLinkedList = new MyLinkedList();

    // Loop through the operations and values arrays
    for (int i = 0; i < operations.length; i++) {
      String operation = operations[i];

      if (operation.equals("MyLinkedList")) {
        myLinkedList = new MyLinkedList();
      }
      if (operation.equals("get")) {
        myLinkedList.get(values.get(i)[0]);
      }
      if (operation.equals("addAtHead")) {
        myLinkedList.addAtHead(values.get(i)[0]);
      }
      if (operation.equals("addAtTail")) {
        myLinkedList.addAtTail(values.get(i)[0]);
      }
      if (operation.equals("addAtIndex")) {
        myLinkedList.addAtIndex(values.get(i)[0], values.get(i)[1]);
      }
      if (operation.equals("deleteAtIndex")) {
        myLinkedList.deleteAtIndex(values.get(i)[0]);
      }

    }
  }
}
