/*
LeetCode Problem: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

Question: 987. Vertical Order Traversal of a Binary Tree

Problem Statement: Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.

Example 2:
Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.

Example 3:
Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
*/

/*
Approach:
1. Edge case check
   - If the root is null then return an empty list.
2. BFS traversal with coordinates
   - Use a queue to perform BFS.
   - Each node is paired with (col, row).
   - Root starts at (0, 0).
   - For left child → (col - 1, row + 1).
   - For right child → (col + 1, row + 1).
3. Data structure for storage
   - Use a TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>.
     - Outer TreeMap → keys are columns (sorted automatically).
     - Inner TreeMap → keys are rows (sorted automatically).
     - PriorityQueue → stores multiple nodes at same (col, row) in ascending order.
4. Fill the map during BFS
   - For each node, insert node.val into map[col][row].
   - Add its children with updated coordinates into the queue.
5. Construct the result
   - Traverse the TreeMap column by column.
   - Within each column, traverse rows in ascending order.
   - Extract values from the PriorityQueue and add to a column list.
   - Append each column list into the final result list.
6. Final output
   - Return the list of lists containing vertical order traversal.

Time Complexity: O(N log N), Insertion and sorting by TreeMap and PriorityQueue.  
Space Complexity: O(N), BFS queue + map storage.  
*/

package Trees.Hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class _987_Vertical_Order_Traversal_of_a_Binary_Tree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    // Method to get the vertical order traversal of the binary tree
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        // Edge case check
        if (root == null) {
            return new ArrayList<>();
        }

        // TreeMap → col → (row → minHeap of node values)
        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        // BFS queue storing (node, col, row)
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));

        // BFS traversal
        while (!queue.isEmpty()) {
            // Get the tuple from queue
            Tuple t = queue.poll();
            TreeNode node = t.node;
            int col = t.col;
            int row = t.row;

            // Put node value inside map[col][row]
            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            map.get(col).get(row).offer(node.val);

            // Add left child with col-1 and row+1
            if (node.left != null) {
                queue.offer(new Tuple(node.left, col - 1, row + 1));
            }

            // Add right child with col+1 and row+1
            if (node.right != null) {
                queue.offer(new Tuple(node.right, col + 1, row + 1));
            }
        }

        // Build final result list
        List<List<Integer>> result = new ArrayList<>();

        // Traverse columns in sorted order
        for (Map<Integer, PriorityQueue<Integer>> rows : map.values()) {
            List<Integer> colList = new ArrayList<>();

            // Traverse rows in sorted order
            for (PriorityQueue<Integer> pq : rows.values()) {
                while (!pq.isEmpty()) {
                    colList.add(pq.poll());
                }
            }
            result.add(colList);
        }

        // Retrun the result
        return result;
    }

    // Helper Tuple class for BFS
    private static class Tuple {
        TreeNode node;
        int col;
        int row;

        public Tuple(TreeNode node, int col, int row) {
            this.node = node;
            this.col = col;
            this.row = row;
        }
    }

    // Build tree from int[] (no nulls, complete binary tree)
    public static TreeNode makeTree(int[] val) {
        if (val == null || val.length == 0) {
            return null;
        }

        // Convert int[] → Integer[] (not strictly needed here, but keeps consistent
        // with logic)
        Integer[] arr = new Integer[val.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = val[i];
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (i < arr.length) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < arr.length) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // Mock class for makeing the TreeNode Class
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Main method to test verticalTraversal
    public static void main(String[] args) {
        int[] root = { 1, 2, 3, 4, 5, 6, 7 };

        List<List<Integer>> result = verticalTraversal(makeTree(root));

        System.out.println("The vertical order traversal of a binary tree is: " + result);
    }
}