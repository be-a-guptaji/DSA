/*
LeetCode Problem: https://leetcode.com/problems/car-pooling/

Question: 1094. Car Pooling

Problem Statement: There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

Example 1:
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true

Constraints:
    1 <= trips.length <= 1000
    trips[i].length == 3
    1 <= numPassengersi <= 100
    0 <= fromi < toi <= 1000
    1 <= capacity <= 10^5
*/

/*
Approach: Coordinate-compressed Difference Array
Goal:
- Determine if a car with a given capacity can
  complete all trips without exceeding capacity at
  any point along the route.
Core Idea:
- Each trip adds passengers at its start location
  and removes them at its end location.
- A difference array encodes these boarding and
  alighting events as point updates; a prefix sum
  sweep then reconstructs the passenger count at
  every location.
- Compressing coordinates to the range
  [min_start, max_end] avoids allocating a
  full-range array when trips span a small portion
  of the location space.
Algorithm Steps:
1. Find the minimum start location (left) and
   maximum end location (right) across all trips.
2. Initialize a difference array of size
   right - left + 1.
3. For each trip (numPassengers, from, to):
   - Add numPassengers at index from - left
     (passengers board here).
   - Subtract numPassengers at index to - left
     (passengers alight here).
4. Sweep the difference array with a running
   prefix sum (currentPassenger):
   - If currentPassenger exceeds capacity at any
     index, return false.
5. Return true if no location exceeds capacity.
Why It Works:
- The difference array encodes O(1) per trip
  instead of updating every location in the trip's
  range, reducing trip processing from O(range) to
  O(1).
- The prefix sum reconstructs exact passenger
  counts at each location in a single O(range)
  pass.
- Passengers alight at exactly the end location,
  so subtracting at to - left (not to - left - 1)
  correctly reflects that they are gone from that
  point onward.
Time Complexity:
- O(n + r)
where n is the number of trips and r is the
compressed location range (right - left + 1).
Space Complexity:
- O(r)
for the difference array.
Result:
- Returns true if all trips can be completed
  without exceeding capacity, false otherwise.
*/

package StacksQueuesAndHeaps.Medium;

// Solution Class
class Solution {
  // Method to determine if it is possible to pick up and drop off all passengers
  // for all the given trips, or false otherwise
  public boolean carPooling(int[][] trips, int capacity) {
    // Initialize the left and right value
    int left = Integer.MAX_VALUE;
    int right = Integer.MIN_VALUE;

    // Iterate over the trips array
    for (int i = 0; i < trips.length; i++) {
      left = Math.min(left, trips[i][1]);
      right = Math.max(right, trips[i][2]);
    }

    // Initialize the passenger array
    int[] passenger = new int[right - left + 1];

    // Iterate over the trips array
    for (int i = 0; i < trips.length; i++) {
      passenger[trips[i][1] - left] += trips[i][0];
      passenger[trips[i][2] - left] -= trips[i][0];
    }

    // Initialize the current passenger
    int currentPassenger = 0;

    // Check if all the passenger can fit in the capacity
    for (int i = 0; i < passenger.length; i++) {
      // Update the currentPassenger
      currentPassenger += passenger[i];

      // If currentPassenger is more than the capacity then return false
      if (currentPassenger > capacity) {
        return false;
      }
    }

    // Return true
    return true;
  }
}

// Main Class
public class _1094_Car_Pooling {
  // Main method to test carPooling
  public static void main(String[] args) {
    int[][] trips = { { 2, 1, 5 }, { 3, 3, 7 } };
    int capacity = 4;

    boolean result = new Solution().carPooling(trips, capacity);

    System.out.println(
        "True if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise is : "
            + result);
  }
}
