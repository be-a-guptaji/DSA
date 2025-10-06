/*
LeetCode Problem: https://leetcode.com/problems/gas-station/

Question: 134. Gas Station

Problem Statement: There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.

Example 1:
Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.

Example 2:
Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.

Constraints:

n == gas.length == cost.length
1 <= n <= 10^5
0 <= gas[i], cost[i] <= 10^4
The input is generated such that the answer is unique.
*/

/*
Approach: This solution uses a greedy approach to determine the valid starting gas station.
- We iterate through each gas station and compute the difference between gas[i] and cost[i].
- 'tank' keeps track of the current gas in the car as we simulate the journey.
- 'total' tracks the overall net gas. If it's negative at the end, completing the circuit is impossible.
- If 'tank' drops below 0 at any point, it means we cannot reach the current station from the previous start.
  So we reset the start index to the next station (i + 1) and reset tank to 0.
- At the end, if total gas is >= total cost, we return the valid start index. Otherwise, return -1.

Time Complexity: O(n), where n = number of gas stations. Each station is visited once.
Space Complexity: O(1), as only a few integer variables are used.
*/

package Greedy.Medium;

public class _134_Gas_Station {
    // Method to find the starting station to start to cover all the sation in
    // clockwise direction
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        // Initialize variable for the tracking the tank gas, total gas and starting
        // index
        int tank = 0, start = 0, total = 0;

        for (int i = 0; i < gas.length; i++) {
            // Get difference between the gas and the cost
            int difference = gas[i] - cost[i];

            // Add difference to total and the tank
            total += difference;
            tank += difference;

            // If tank goes below the 0 then reinitialize the tank with 0 and update the
            // starting position to the next index
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        // Return the starting position if the total gas is more than or equal to 0
        return total >= 0 ? start : -1;
    }

    // Main method to test canCompleteCircuit
    public static void main(String[] args) {
        int[] gas = { 1, 2, 3, 4, 5 };
        int[] cost = { 3, 4, 5, 1, 2 };

        int result = canCompleteCircuit(gas, cost);

        System.out
                .println("The starting station to start to cover all the sation in clockwise direction is : " + result);
    }
}
