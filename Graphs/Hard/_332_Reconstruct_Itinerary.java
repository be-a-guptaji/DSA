/*
LeetCode Problem: https://leetcode.com/problems/reconstruct-itinerary/

Question: 332. Reconstruct Itinerary

Problem Statement: You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

Example 1:
Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]

Example 2:
Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.

Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
 */

/*
Approach: Hierholzer’s Algorithm (DFS + Lexicographical Ordering)

Goal:
Reconstruct the itinerary using all tickets exactly once, starting from "JFK".
If multiple valid itineraries exist, return the lexicographically smallest one.

Key Ideas:
- The problem reduces to finding an Eulerian path in a directed graph.
- Airports are nodes, tickets are directed edges.
- Lexicographical order is enforced using a min heap.

Steps:

1. Build the adjacency list:
   - Map each source airport to a min heap of destination airports.
   - This ensures the smallest lexicographical destination is chosen first.

2. Perform DFS (Hierholzer’s Algorithm):
   - Start DFS from "JFK".
   - While the current airport has outgoing edges:
       • Remove the smallest destination from its priority queue.
       • Recursively DFS on that destination.
   - After all outgoing edges are used, add the airport to the result list.

3. Reverse the result list:
   - Nodes are added in postorder, so reversing gives the correct itinerary.

Why It Works:
- Each ticket (edge) is used exactly once.
- DFS guarantees full traversal.
- Priority queue guarantees lexicographically smallest path.

Time Complexity:
- Building graph: O(n log n)
- DFS traversal: O(n log n)

Space Complexity: O(n)
*/

package Graphs.Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class _332_Reconstruct_Itinerary {
   // Method to reconstruct the itinerary in order
   public static List<String> findItinerary(List<List<String>> tickets) {
      // Initialize the hash map for storing the adjacency list
      HashMap<String, PriorityQueue<String>> adjacencyList = new HashMap<>();

      // Fill the hash map
      for (List<String> ticket : tickets) {
         // Get the source and destination of the ticket
         String src = ticket.get(0);
         String dst = ticket.get(1);

         // Add in the hash map if value is not in hash map then add then initialize the
         // pirority queue first
         adjacencyList.computeIfAbsent(src, k -> new PriorityQueue<>()).offer(dst);
      }

      // Initialize the result list of string to hold the path
      ArrayList<String> result = new ArrayList<>();

      // Make a dfs recursive call for finding the path
      dfs(adjacencyList, "JFK", result);

      // Reverse the result list
      Collections.reverse(result);

      // Return the result
      return result;
   }

   // Make a helper method to find the valid path
   private static void dfs(HashMap<String, PriorityQueue<String>> adjacencyList, String source,
         ArrayList<String> result) {
      // Get the queue for the source
      PriorityQueue<String> queue = adjacencyList.get(source);

      // Iterate over the queue untill it is empty or queue is not null
      while (queue != null && !queue.isEmpty()) {
         // Get the destination
         String destination = queue.poll();

         // Make a dfs call on the destination
         dfs(adjacencyList, destination, result);
      }

      // Add the source to the destination
      result.add(source);
   }

   // Main method to test findItinerary
   public static void main(String[] args) {
      List<List<String>> tickets = List.of(
            List.of("MUC", "LHR"),
            List.of("JFK", "MUC"),
            List.of("SFO", "SJC"),
            List.of("LHR", "SFO"));

      List<String> result = findItinerary(tickets);

      System.out.println("Reconstruct the itinerary in order is : " + result);
   }
}