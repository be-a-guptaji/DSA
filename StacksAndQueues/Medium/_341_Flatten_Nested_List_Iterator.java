/*
LeetCode Problem: https://leetcode.com/problems/flatten-nested-list-iterator/

Question: 341. Flatten Nested List Iterator

Problem Statement: You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.

Implement the NestedIterator class:

NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
int next() Returns the next integer in the nested list.
boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
Your code will be tested with the following pseudocode:

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
If res matches the expected flattened list, then your code will be judged as correct.

Example 1:
Input: nestedList = [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Input: nestedList = [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

Constraints:

1 <= nestedList.length <= 500
The values of the integers in the nested list is in the range [-10^6, 10^6].
 */

/*
Approach: Depth-First Search (Eager Flattening)

Goal:
- Implement an iterator that flattens a nested list of integers
  and returns elements in sequential order.

Core Idea:
- Preprocess the entire nested structure using DFS.
- Store all integers in a single flattened list.
- Use an index pointer to simulate iterator behavior.

Algorithm Steps:
1. Initialize an empty ArrayList to store flattened integers.
2. Perform DFS on nestedList:
   - If element is integer → add to flattenList.
   - Else → recursively call DFS on sublist.
3. Store total size of flattenList.
4. next() → return flattenList[index++].
5. hasNext() → return index < size.

Time Complexity:
- O(n) preprocessing, where n is total number of integers.

Space Complexity:
- O(n) for storing flattened elements + recursion stack.

Result:
- Iterator returns integers in correct flattened order.
*/

package StacksAndQueues.Medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _341_Flatten_Nested_List_Iterator {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     * // @return true if this NestedInteger holds a single integer, rather than a
     * nested list.
     * public boolean isInteger();
     *
     * // @return the single integer that this NestedInteger holds, if it holds a
     * single integer
     * // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     *
     * // @return the nested list that this NestedInteger holds, if it holds a
     * nested list
     * // Return empty list if this NestedInteger holds a single integer
     * public List<NestedInteger> getList();
     * }
     */

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */

    // Class to make the NestedIterator
    public static class NestedIterator implements Iterator<Integer> {
        // Initialize the array list for the flatten list
        private final ArrayList<Integer> flattenList;

        // Initialize the index variable for the flatten list
        private int index;

        // Initialize the size of the flattenList
        private final int size;

        public NestedIterator(List<NestedInteger> nestedList) {
            // Initialize the flatten list
            this.flattenList = new ArrayList<>();

            // Intialize the index
            this.index = 0;

            // Flatten the array list
            this.dfs(nestedList);

            // Update the size variable
            this.size = this.flattenList.size();
        }

        @Override
        public Integer next() {
            return this.flattenList.get(this.index++);
        }

        @Override
        public boolean hasNext() {
            return this.index < this.size;
        }

        // Helper method to flatten the list
        private void dfs(List<NestedInteger> nestedList) {
            // Iterate over the nestedList
            for (NestedInteger nl : nestedList) {
                if (nl.isInteger()) {
                    this.flattenList.add(nl.getInteger());
                } else {
                    this.dfs(nl.getList());
                }
            }
        }
    }

    // Class to make the NestedInteger
    public static class NestedInteger {
        private Integer value;
        private List<NestedInteger> list;

        // Constructor initializes an empty nested list
        public NestedInteger() {
            this.list = new ArrayList<>();
        }

        // Constructor initializes a single integer
        public NestedInteger(int value) {
            this.value = value;
        }

        // @return true if this holds a single integer
        public boolean isInteger() {
            return value != null;
        }

        // @return the single integer, or null if it holds a list
        public Integer getInteger() {
            return value;
        }

        // @return the nested list, or null if it holds a single integer
        public List<NestedInteger> getList() {
            return list;
        }

        // Helper method to add elements (useful for testing)
        public void add(NestedInteger ni) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(ni);
        }
    }

    // Main method to test NestedIterator
    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();

        nestedList.add(new NestedInteger(1));

        NestedInteger secondList = new NestedInteger();
        secondList.add(new NestedInteger(4));

        NestedInteger innerList = new NestedInteger();
        innerList.add(new NestedInteger(6));

        secondList.add(innerList);
        nestedList.add(secondList);

        NestedIterator i = new NestedIterator(nestedList);

        ArrayList<Integer> result = new ArrayList<>();

        while (i.hasNext()) {
            result.add(i.next());
        }

        System.out.println("The flatten list is : " + result);
    }
}