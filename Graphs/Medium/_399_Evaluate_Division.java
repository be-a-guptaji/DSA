/*
LeetCode Problem: https://leetcode.com/problems/evaluate-division/

Question: 399. Evaluate Division

Problem Statement: You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

Example 1:
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0

Example 2:
Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */

/*
Approach:
1. Represent the equations as a bidirectional weighted graph:
   - Each variable is a node.
   - An equation "a / b = k" is represented as:
        edge a → b with weight k,
        edge b → a with weight 1/k.
2. Store the graph in an adjacency list using a HashMap<String, List<Edge>>.
3. For each query [x, y]:
   - If x or y does not exist in the graph, return -1.
   - If x == y, return 1.
   - Otherwise, run BFS (or DFS) from x to y:
       • Start with weight = 1 at source.
       • Traverse neighbors, multiplying edge weights along the path.
       • If target is reached, return the accumulated weight.
   - If no path is found, return -1.
4. Collect the answers for all queries in the result array.

Time Complexity: O(Q * (V + E))  
- Q = number of queries, V = number of unique variables, E = number of equations.  
- Each BFS explores at most all edges once per query.
Space Complexity: O(V + E)  
- Adjacency list + visited set + BFS queue.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _399_Evaluate_Division {
    // Method to find the division result of the queries
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Create a Map for equation and the adjacency nodes
        Map<String, ArrayList<Edge>> map = new HashMap<>();

        // Initialize a variable for the size of the equation
        int equsize = equations.size();

        // Make the adjacency List according to the node value
        for (int i = 0; i < equsize; i++) {
            // Initialize variable for first and second variable of the equation
            String firstValue = equations.get(i).getFirst(); // Get the first value of the equation
            String secondValue = equations.get(i).getLast(); // Get the second value of the equation

            // Add the equation in forward and backward direction
            map.computeIfAbsent(firstValue, _ -> new ArrayList<>()).add(new Edge(secondValue, values[i]));
            map.computeIfAbsent(secondValue, _ -> new ArrayList<>()).add(new Edge(firstValue, 1 / values[i]));
        }

        // Initialize a variable for the size of the queries
        int qursize = queries.size();

        // Initialize the result array
        double[] result = new double[qursize];

        // Initialize a index for tracking the index variable for the result
        int index = 0;

        // Iterate over the queries to find the division result
        for (List<String> equ : queries) {
            // Initialize variable for first and second variable of the equation
            String firstValue = equ.getFirst(); // Get the first value of the equation
            String secondValue = equ.getLast(); // Get the second value of the equation

            // If any of the qureies parameter is not in the map key then fill the array
            // with the -1 else do the calculation
            if (!map.containsKey(firstValue) || !map.containsKey(secondValue)) {
                result[index++] = -1;
            } else if (firstValue.equals(secondValue)) {
                result[index++] = 1;
            } else {
                result[index++] = bfs(firstValue, secondValue, map);
            }
        }

        // Return the result
        return result;
    }

    // Helper function to find the result of the equation
    private static double bfs(String source, String target, Map<String, ArrayList<Edge>> map) {
        // Make a queue and set for traversing the graph
        Queue<Edge> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Add the source to the queue and to the set
        queue.add(new Edge(source, 1));
        visited.add(source);

        // Traverse until the queue is empty
        while (!queue.isEmpty()) {
            // Get the edge form the queue
            Edge edg = queue.poll();

            // If edg value's is equal to the target then return the weight
            if (edg.val.equals(target)) {
                return edg.weight;
            }

            // Get the adjacency List of the node
            ArrayList<Edge> list = map.get(edg.val);

            // Iterate over all the value of the connecting nodes
            for (Edge e : list) {
                // If the node is not visited then continue
                if (!visited.contains(e.val)) {
                    // Append to the queue
                    queue.add(new Edge(e.val, e.weight * edg.weight));

                    // Add the node to the visited set as well
                    visited.add(e.val);
                }
            }
        }

        // If no path is found return -1
        return -1;
    }

    // Helper function to store the edge weight and the node value
    private static class Edge {
        String val; // For storing the value
        double weight; // For storing the edge weight

        public Edge(String val, double weight) {
            this.val = val;
            this.weight = weight;
        }
    }

    // Main method to test calcEquation
    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"));

        double[] values = { 2.0, 3.0 };

        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x"));

        double[] result = calcEquation(equations, values, queries);

        System.out.print("The result of the following queries are : ");

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
