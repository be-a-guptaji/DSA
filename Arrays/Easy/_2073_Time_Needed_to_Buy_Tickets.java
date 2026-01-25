/*
LeetCode Problem: https://leetcode.com/problems/time-needed-to-buy-tickets/

Question: 2073. Time Needed to Buy Tickets

Problem Statement: There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.

You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].

Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.

Return the time taken for the person initially at position k (0-indexed) to finish buying tickets.

Example 1:
Input: tickets = [2,3,2], k = 2
Output: 6
Explanation:
The queue starts as [2,3,2], where the kth person is underlined.
After the person at the front has bought a ticket, the queue becomes [3,2,1] at 1 second.
Continuing this process, the queue becomes [2,1,2] at 2 seconds.
Continuing this process, the queue becomes [1,2,1] at 3 seconds.
Continuing this process, the queue becomes [2,1] at 4 seconds. Note: the person at the front left the queue.
Continuing this process, the queue becomes [1,1] at 5 seconds.
Continuing this process, the queue becomes [1] at 6 seconds. The kth person has bought all their tickets, so return 6.

Example 2:
Input: tickets = [5,1,1,1], k = 0
Output: 8
Explanation:
The queue starts as [5,1,1,1], where the kth person is underlined.
After the person at the front has bought a ticket, the queue becomes [1,1,1,4] at 1 second.
Continuing this process for 3 seconds, the queue becomes [4] at 4 seconds.
Continuing this process for 4 seconds, the queue becomes [] at 8 seconds. The kth person has bought all their tickets, so return 8.

Constraints:

n == tickets.length
1 <= n <= 100
1 <= tickets[i] <= 100
0 <= k < n
 */

/*
Approach: Direct Counting per Person

Goal:
Find the total time required for the person initially at index k
to finish buying all their tickets.

Key Observation:
- Each ticket purchase takes 1 unit of time.
- The person at index k needs tickets[k] turns.
- People before or at k can buy at most tickets[k] tickets.
- People after k can buy at most tickets[k] − 1 tickets,
  because once person k finishes, the process stops.

Algorithm:
1. Initialize time = 0.
2. Traverse the tickets array:
   - If i ≤ k:
       • Add min(tickets[i], tickets[k]) to time.
   - Else:
       • Add min(tickets[i], tickets[k] − 1) to time.
3. Return time.

Why It Works:
- Simulates the exact number of turns each person contributes
  before person k finishes.
- Avoids explicit queue simulation.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _2073_Time_Needed_to_Buy_Tickets {
    // Method to find the time taken for the person initially at position k
    // (0-indexed) to finish buying tickets.
    public static int timeRequiredToBuy(int[] tickets, int k) {
        // Initialize the time vairable
        int time = 0;

        // Iterate over the tickets array for finding the minimum time
        for (int i = 0; i < tickets.length; i++) {
            // Update the time according to i and k
            if (i <= k) {
                time += Math.min(tickets[i], tickets[k]);
            } else {
                time += Math.min(tickets[i], tickets[k] - 1);
            }
        }

        // Return time
        return time;
    }

    // Main method to test timeRequiredToBuy
    public static void main(String[] args) {
        int[] tickets = new int[] { 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        int k = 4;

        int result = timeRequiredToBuy(tickets, k);

        System.out.println("The time taken for the person initially at position " + k
                + " (0-indexed) to finish buying tickets is : " + result);
    }
}
