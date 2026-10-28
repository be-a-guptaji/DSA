/*
LeetCode Problem: https://leetcode.com/problems/find-all-people-with-secret/

Question: 2092. Find All People With Secret

Problem Statement: You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.

Example 1:
Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.​​​​
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.

Example 2:
Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
Output: [0,1,3]
Explanation:
At time 0, person 0 shares the secret with person 3.
At time 2, neither person 1 nor person 2 know the secret.
At time 3, person 3 shares the secret with person 0 and person 1.
Thus, people 0, 1, and 3 know the secret after all the meetings.

Example 3:
Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
Output: [0,1,2,3,4]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
Note that person 2 can share the secret at the same time as receiving it.
At time 2, person 3 shares the secret with person 4.
Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.

Constraints:
2 <= n <= 10^5
1 <= meetings.length <= 10^5
meetings[i].length == 3
0 <= xi, yi <= n - 1
xi != yi
1 <= timei <= 10^5
1 <= firstPerson <= n - 1
*/

/*
Approach: Time-grouped Graph DFS with Secret Propagation
Goal:
- Find all people who know the secret after all
  meetings, where the secret spreads between any
  two people who meet while at least one knows it.
Core Idea:
- Group meetings by timestamp; at each timestamp,
  build a temporary graph of only the people
  meeting at that time.
- DFS from every secret-holder present in that
  timestamp's graph, propagating the secret to all
  connected people in the same time-group.
- Process timestamps in sorted order so earlier
  meetings propagate before later ones.
Algorithm Steps:
1. Initialize secrets = {0, firstPerson}.
2. Sort meetings by time and build timingMap:
   timestamp -> undirected adjacency list of
   people meeting at that time.
3. For each timestamp in sorted order:
   a. Reset visited set for this timestamp.
   b. For each person in the timestamp's graph:
      - If they know the secret, run DFS to spread
        it to all connected people at this time.
4. Return secrets as a list.
Why It Works:
- Grouping by timestamp ensures people who meet
  simultaneously share the secret instantaneously
  within the same connected component, regardless
  of which person in the component already knew it.
- A per-timestamp visited set prevents redundant
  DFS calls within the same time group while
  allowing the same person to appear in multiple
  timestamp graphs.
- Sorting timestamps ensures causal ordering:
  secrets learned at time t are available for
  propagation at time t' > t.
Time Complexity:
- O(m log m + m * alpha) where m is the number of
  meetings; sorting is O(m log m) and DFS across
  all timestamps processes each meeting edge at
  most twice total.
Space Complexity:
- O(n + m) for the secrets set, visited set, and
  timingMap adjacency lists.
Result:
- Returns the list of all people who know the
  secret after all meetings conclude.
*/

package Graphs.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

// Solution Class
class Solution {
  // Initialize the hashset for the secrets and visited
  HashSet<Integer> secrets;
  HashSet<Integer> visited;

  // Method to find a list of all the people that have the secret after all the
  // meetings have taken place
  public ArrayList<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
    // Initialize the hashset for the secrets
    this.secrets = new HashSet<>(Arrays.asList(0, firstPerson));

    // Sort the meetings by time
    Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));

    // Initialize the hashmap of timing map to graph
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> timingMap = new HashMap<>();

    // Iterate over the meetings and make a graph
    for (int[] meeting : meetings) {
      // Get the source, destination and timing
      int source = meeting[0];
      int destination = meeting[1];
      int timing = meeting[2];

      // Add the timing to the hashmap
      timingMap.putIfAbsent(timing, new HashMap<>());

      // Get the map of timing
      HashMap<Integer, ArrayList<Integer>> map = timingMap.get(timing);

      // Add the value to the map to source and destination
      map.putIfAbsent(source, new ArrayList<>());
      map.putIfAbsent(destination, new ArrayList<>());

      // Make the graph
      map.get(source).add(destination);
      map.get(destination).add(source);
    }

    // Get all the timings
    ArrayList<Integer> timings = new ArrayList<>(timingMap.keySet());

    // Sort the timings
    Collections.sort(timings);

    // Iterate over the timings
    for (int time : timings) {
      // Initialize the hashset for the visited
      this.visited = new HashSet<>();

      // Iterate over all the sources
      for (int source : timingMap.get(time).keySet()) {
        // If source is in secrets then do the dfs
        if (this.secrets.contains(source)) {
          this.dfs(source, timingMap.get(time));
        }
      }
    }

    // Return the secrets by converting it to arraylist
    return new ArrayList<>(this.secrets);
  }

  // Helper method for the dfs
  private void dfs(int source, HashMap<Integer, ArrayList<Integer>> adjList) {
    // If the source is seen then return
    if (!this.visited.add(source)) {
      return;
    }

    // Add the source to the secrets array
    this.secrets.add(source);

    // Iterate over all the neighoburs
    for (int neighoburs : adjList.getOrDefault(source, new ArrayList<>())) {
      this.dfs(neighoburs, adjList);
    }
  }
}

public class _2092_Find_All_People_With_Secret {
  // Main method to test findAllPeople
  public static void main(String[] args) {
    int n = 10;
    int[][] meetings = new int[][] { { 1, 2, 5 }, { 2, 3, 8 }, { 1, 5, 10 } };
    int firstPerson = 1;

    ArrayList<Integer> result = new Solution().findAllPeople(n, meetings, firstPerson);

    System.out.println(
        "A list of all the people that have the secret after all the meetings have taken place is : " + result);
  }
}