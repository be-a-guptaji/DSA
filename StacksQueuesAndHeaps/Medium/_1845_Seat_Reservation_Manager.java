/*
LeetCode Problem: https://leetcode.com/problems/seat-reservation-manager/

Question: 1845. Seat Reservation Manager

Problem Statement: Design a system that manages the reservation state of n seats that are numbered from 1 to n.

Implement the SeatManager class:

    SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n. All seats are initially available.
    int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
    void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.

Example 1:
Input
["SeatManager", "reserve", "reserve", "unreserve", "reserve", "reserve", "reserve", "reserve", "unreserve"]
[[5], [], [], [2], [], [], [], [], [5]]
Output
[null, 1, 2, null, 2, 3, 4, 5, null]
Explanation
SeatManager seatManager = new SeatManager(5); // Initializes a SeatManager with 5 seats.
seatManager.reserve();    // All seats are available, so return the lowest numbered seat, which is 1.
seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
seatManager.unreserve(2); // Unreserve seat 2, so now the available seats are [2,3,4,5].
seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
seatManager.reserve();    // The available seats are [3,4,5], so return the lowest of them, which is 3.
seatManager.reserve();    // The available seats are [4,5], so return the lowest of them, which is 4.
seatManager.reserve();    // The only available seat is seat 5, so return 5.
seatManager.unreserve(5); // Unreserve seat 5, so now the available seats are [5].

Constraints:
    1 <= n <= 10^5
    1 <= seatNumber <= n
    For each call to reserve, it is guaranteed that there will be at least one unreserved seat.
    For each call to unreserve, it is guaranteed that seatNumber will be reserved.
    At most 10^5 calls in total will be made to reserve and unreserve.
*/

/*
Approach: Min-Heap for Smallest Available Seat Tracking
Goal:
- Support reserve (return and remove the smallest
  available seat number) and unreserve (return a
  seat number back to the available pool) in
  efficient time.
Core Idea:
- A min-heap always surfaces the smallest available
  seat number at its root, making reserve an O(log
  n) poll and unreserve an O(log n) offer.
Algorithm Steps:
1. In the constructor, insert all seat numbers 1
   to n into the min-heap.
2. reserve(): poll and return the heap's minimum
   (smallest available seat).
3. unreserve(seatNumber): push seatNumber back into
   the heap, restoring it to the available pool.
Time Complexity:
- Constructor: O(n log n) for n insertions.
- reserve(): O(log n) per call.
- unreserve(): O(log n) per call.
Space Complexity:
- O(n) for the min-heap storing up to n seat
  numbers.
Result:
- Always reserves the lowest-numbered available
  seat and correctly restores unreserved seats for
  future reservations.
*/

package StacksQueuesAndHeaps.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */

// SeatManager Class
class SeatManager {
  // Initialize the minHeap
  private final PriorityQueue<Integer> minHeap;

  public SeatManager(int n) {
    // Initialize the minHeap
    this.minHeap = new PriorityQueue<>();

    // Add all the value to the minHeap
    for (int i = 1; i <= n; i++) {
      this.minHeap.offer(i);
    }
  }

  public int reserve() {
    return this.minHeap.poll();
  }

  public void unreserve(int seatNumber) {
    this.minHeap.offer(seatNumber);
  }
}

// Main Class
public class _1845_Seat_Reservation_Manager {
  // Main method to test SeatManager
  // Main method to test SeatManager
  public static void main(String[] args) {

    String[] operations = {
        "SeatManager", "reserve", "reserve", "unreserve",
        "reserve", "reserve", "reserve", "reserve", "unreserve"
    };

    List<int[]> values = new ArrayList<>();
    values.add(new int[] { 5 }); // SeatManager(5)
    values.add(new int[] {}); // reserve()
    values.add(new int[] {}); // reserve()
    values.add(new int[] { 2 }); // unreserve(2)
    values.add(new int[] {}); // reserve()
    values.add(new int[] {}); // reserve()
    values.add(new int[] {}); // reserve()
    values.add(new int[] {}); // reserve()
    values.add(new int[] { 5 }); // unreserve(5)

    // Create an instance of SeatManager
    SeatManager seatManager = new SeatManager(0);

    // Loop through the operations and values arrays
    for (int i = 0; i < operations.length; i++) {
      String operation = operations[i];

      if (operation.equals("SeatManager")) {
        // Create the new SeatManager instance
        seatManager = new SeatManager(values.get(i)[0]);
        System.out.println("null");
      }

      if (operation.equals("reserve")) {
        // Call reserve() and print the reserved seat number
        System.out.println(seatManager.reserve());
      }

      if (operation.equals("unreserve")) {
        // Call unreserve() with the seat number
        seatManager.unreserve(values.get(i)[0]);
        System.out.println("null");
      }
    }
  }

}
