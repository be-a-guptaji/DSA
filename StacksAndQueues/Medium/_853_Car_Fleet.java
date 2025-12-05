/*
LeetCode Problem: https://leetcode.com/problems/car-fleet/

Question: 853. Car Fleet

Problem Statement: There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.

You are given two integer arrays position and speed, both of length n, where position[i] is the starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.

A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.

A car fleet is a single car or a group of cars driving next to each other. The speed of the car fleet is the minimum speed of any car in the fleet.

If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.

Return the number of car fleets that will arrive at the destination.

Example 1:
Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms at target.
The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.

Example 2:
Input: target = 10, position = [3], speed = [3]
Output: 1
Explanation:
There is only one car, hence there is only one fleet.

Example 3:
Input: target = 100, position = [0,2,4], speed = [4,2,1]
Output: 1
Explanation:
The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The car starting at 4 (speed 1) travels to 5.
Then, the fleet at 4 (speed 2) and the car at position 5 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.

Constraints:

n == position.length == speed.length
1 <= n <= 10^5
0 < target <= 10^6
0 <= position[i] < target
All the values of position are unique.
0 < speed[i] <= 10^6
 */

/*
Approach: Sorting + Single Pass (Greedy)

1. Store each car’s position and speed together so they can be processed as pairs.

2. Sort all cars in descending order of position:
   - This ensures we always process the car closest to the target first.
   - Cars behind will either form new fleets or merge into an existing fleet.

3. For each car (in sorted order):
   - Compute the time it will take to reach the target.
   - Compare this time with the last recorded fleet time.

4. Fleet formation logic:
   - If the current car's time is greater than the last fleet time,
     it cannot catch up and forms a new fleet.
   - Otherwise, it joins the existing fleet because it will catch up.

5. Keep updating:
   - `fleets` to count how many separate fleets are formed.
   - `lastTime` to store the arrival time of the most recently formed fleet.

Time Complexity: O(n log n) due to sorting  
Space Complexity: O(n) for storing car data
*/

package StacksAndQueues.Medium;

import java.util.Arrays;

public class _853_Car_Fleet {
    // Method to find the number of car fleets that will arrive at the destination
    public static int carFleet(int target, int[] position, int[] speed) {
        // Get the number of cars
        int n = position.length;

        // 2D array to store each car’s position and speed
        double[][] cars = new double[n][2];

        // Fill the array with the given position and speed values
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i]; // Set the position
            cars[i][1] = speed[i]; // Set the speed
        }

        // Sort the cars in descending order of position (car closest to target first)
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        // Variable to count the number of fleets formed
        int fleets = 0;

        // Holds the time of the last fleet created (used for merging logic)
        double lastTime = 0;

        // Iterate through all the cars to determine fleet formation
        for (int i = 0; i < n; i++) {
            // Calculate the time taken by the current car to reach the target
            double time = (target - cars[i][0]) / cars[i][1];

            // If the current car takes more time than the previous fleet,
            // it forms a new fleet. Otherwise, it merges with the existing one.
            if (time > lastTime) {
                fleets++; // New fleet created
                lastTime = time; // Update the last fleet time
            }
        }

        // Return the total number of fleets
        return fleets;
    }

    // Main method to test carFleet
    public static void main(String[] args) {
        int target = 12;
        int[] position = { 10, 8, 0, 5, 3 };
        int[] speed = { 2, 4, 1, 1, 3 };

        int result = carFleet(target, position, speed);

        System.out.println("The number of car fleets that will arrive at the destination is : " + result);
    }
}
