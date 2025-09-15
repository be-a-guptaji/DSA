/*
LeetCode Problem: https://leetcode.com/problems/clone-graph/

Question: 133. Clone Graph

Problem Statement: Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

Constraints:

The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 */

/*
Approach:
1. Use a HashMap<Node, Node> to keep track of visited nodes and their clones.
2. Perform DFS (or BFS) starting from the given node.
   - If a node has already been cloned, return it from the map.
   - Otherwise, create a new clone, store it in the map, and recursively clone all its neighbors.
3. This ensures each node is cloned exactly once, and all neighbor relationships are preserved.
4. Return the cloned graph starting from the given node.

Time Complexity: O(V + E), where V is the number of nodes and E is the number of edges  
Space Complexity: O(V), for the HashMap and recursion stack
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _133_Clone_Graph {
    /*
     * // Definition for a Node.
     * class Node {
     * public int val;
     * public List<Node> neighbors;
     * public Node() {
     * val = 0;
     * neighbors = new ArrayList<Node>();
     * }
     * public Node(int _val) {
     * val = _val;
     * neighbors = new ArrayList<Node>();
     * }
     * public Node(int _val, ArrayList<Node> _neighbors) {
     * val = _val;
     * neighbors = _neighbors;
     * }
     * }
     */

    // Method to clone a graph
    public static Node cloneGraph(Node node) {
        // If the Node is null, return null
        if (node == null) {
            return null;
        }

        // HashMap to store the mapping from original node -> cloned node
        Map<Node, Node> map = new HashMap<>();

        // Start DFS and return the cloned node
        return dfs(node, map);
    }

    // Helper DFS method to clone nodes
    private static Node dfs(Node node, Map<Node, Node> map) {
        // If node is already cloned, return it
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create a new clone node
        Node clone = new Node(node.val);

        // Store the mapping of original -> clone
        map.put(node, clone);

        // Clone all neighbors recursively
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }

        // Return the cloned node
        return clone;
    }

    // Build the graph from the array of arrays
    private static Node makeGraph(int[][] graph) {
        // Make the Array of nodes for holding the value of the nodes
        Node[] nodes = new Node[graph.length + 1];

        // Make the nodes with the value eqaul to their index
        for (int i = 0; i <= graph.length; i++) {
            nodes[i] = new Node(i);
        }

        // Make connection from the graph nodes
        for (int i = 0; i < graph.length; i++) {
            for (int link : graph[i]) {
                nodes[i + 1].neighbors.add(nodes[link]);
            }
        }

        // Return the first index
        return nodes[1];
    }

    // Mock Class for the node of the graph
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // Main method to test cloneGraph
    public static void main(String[] args) {
        int[][] graph = { { 2, 4 }, { 1, 3 }, { 2, 4 }, { 1, 3 } };

        Node result = cloneGraph(makeGraph(graph));

        System.out.print("The new node of the deep copy is :" + result);
    }
}
