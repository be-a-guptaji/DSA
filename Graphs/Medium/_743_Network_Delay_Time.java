/*
LeetCode Problem: https://leetcode.com/problems/network-delay-time/

Question: 743. Network Delay Time

Problem Statement: You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Example 2:
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

Example 3:
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1

Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */

/*
Approach:
1. Model the network as a weighted directed graph using an adjacency list:
   - Each edge [u, v, w] represents a travel time w from node u to node v.
2. Initialize an array minimumTime[] to store the shortest known time to reach each node:
   - Set all values to Integer.MAX_VALUE initially.
   - Set the source node k time to 0.
3. Use a priority queue (min-heap) to implement Dijkstra's algorithm:
   - Each queue element is [node, currentTime].
   - Always process the node with the smallest currentTime first.
4. For each node polled from the queue:
   - Skip it if we already have a shorter path in minimumTime.
   - For each neighbor, calculate the new cumulative time.
   - If the new time is smaller than minimumTime[neighbor], update it and push into the queue.
5. After processing all reachable nodes:
   - Iterate through minimumTime[] to find the maximum time needed to reach any node.
   - If any node remains at Integer.MAX_VALUE, return -1 (unreachable node).
   - Otherwise, return the maximum cumulative time.

Time Complexity: O(E log V), where E = number of edges, V = number of nodes.  
Space Complexity: O(V + E) for adjacency list and priority queue.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _743_Network_Delay_Time {
    // Method to find the minimum time to reach all nodes in the graph
    public static int networkDelayTime(int[][] times, int n, int k) {
        // Initialize adjacency list for the graph
        List<List<int[]>> adjacencyList = new ArrayList<>();

        // Array to store shortest time to each node
        int[] minimumTime = new int[n + 1];

        // Add number of lists equal to the number of nodes
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
            minimumTime[i] = Integer.MAX_VALUE; // Initialize all times to infinity
        }

        // Build adjacency list according to [neighbor, weight]
        for (int[] edge : times) {
            adjacencyList.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }

        // Source node time = 0
        minimumTime[k] = 0;

        // Priority queue for Dijkstra: [node, currentTime]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.offer(new int[] { k, 0 });

        // Traverse the graph until the queue is empty
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int node = current[0];
            int time = current[1];

            // Skip if we already have a shorter path
            if (time > minimumTime[node]) {
                continue;
            }

            // Traverse neighbors
            for (int[] neighbor : adjacencyList.get(node)) {
                int nextNode = neighbor[0];
                int edgeWeight = neighbor[1];
                int newTime = time + edgeWeight;

                // Update if a shorter path is found
                if (newTime < minimumTime[nextNode]) {
                    minimumTime[nextNode] = newTime;
                    minHeap.offer(new int[] { nextNode, newTime });
                }
            }
        }

        // Find the maximum delay time
        int result = 0;

        for (int i = 1; i <= n; i++) {
            // If a node is unreachable, return -1
            if (minimumTime[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, minimumTime[i]);
        }

        // Return the result
        return result;
    }

    // Main method to test networkDelayTime
    public static void main(String[] args) {
        int[][] times = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
        int n = 4;
        int k = 2;

        int result = networkDelayTime(times, n, k);

        System.out.println("The minimum time needed to reach all the node from " + k + " node is : " + result);
    }
}