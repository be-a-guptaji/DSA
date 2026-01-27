/*
LeetCode Problem: https://leetcode.com/problems/average-waiting-time/

Question: 1701. Average Waiting Time

Problem Statement: There is a restaurant with a single chef. You are given an array customers, where customers[i] = [arrivali, timei]:

arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
timei is the time needed to prepare the order of the ith customer.
When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. The customer waits till the chef finishes preparing his order. The chef does not prepare food for more than one customer at a time. The chef prepares food for customers in the order they were given in the input.

Return the average waiting time of all customers. Solutions within 10-5 from the actual answer are considered accepted.

Example 1:
Input: customers = [[1,2],[2,5],[4,3]]
Output: 5.00000
Explanation:
1) The first customer arrives at time 1, the chef takes his order and starts preparing it immediately at time 1, and finishes at time 3, so the waiting time of the first customer is 3 - 1 = 2.
2) The second customer arrives at time 2, the chef takes his order and starts preparing it at time 3, and finishes at time 8, so the waiting time of the second customer is 8 - 2 = 6.
3) The third customer arrives at time 4, the chef takes his order and starts preparing it at time 8, and finishes at time 11, so the waiting time of the third customer is 11 - 4 = 7.
So the average waiting time = (2 + 6 + 7) / 3 = 5.

Example 2:
Input: customers = [[5,2],[5,4],[10,3],[20,1]]
Output: 3.25000
Explanation:
1) The first customer arrives at time 5, the chef takes his order and starts preparing it immediately at time 5, and finishes at time 7, so the waiting time of the first customer is 7 - 5 = 2.
2) The second customer arrives at time 5, the chef takes his order and starts preparing it at time 7, and finishes at time 11, so the waiting time of the second customer is 11 - 5 = 6.
3) The third customer arrives at time 10, the chef takes his order and starts preparing it at time 11, and finishes at time 14, so the waiting time of the third customer is 14 - 10 = 4.
4) The fourth customer arrives at time 20, the chef takes his order and starts preparing it immediately at time 20, and finishes at time 21, so the waiting time of the fourth customer is 21 - 20 = 1.
So the average waiting time = (2 + 6 + 4 + 1) / 4 = 3.25.

Constraints:

1 <= customers.length <= 10^5
1 <= arrivali, timei <= 10^4
arrivali <= arrivali+1
*/

/*
Approach: Simulation (Greedy Time Tracking)

Goal:
Compute the average waiting time of all customers, where:
waiting time = (finish time of order) − (arrival time).

Key Ideas:
1. Process customers in given order (they arrive in non-decreasing arrival time).
2. Maintain a `currentTime` representing when the chef is free.
3. For each customer:
   - The chef starts at max(currentTime, arrivalTime).
   - Order finishes at: startTime + cookingTime.
   - Waiting time = finishTime − arrivalTime.
4. Accumulate total waiting time and divide by number of customers.

Algorithm:
1. Initialize currentTime = 0, totalWaitingTime = 0.
2. For each customer:
   - currentTime = max(currentTime, arrival) + cookTime
   - totalWaitingTime += currentTime − arrival
3. Return totalWaitingTime / number of customers.

Time Complexity:
- O(n), where n is the number of customers.

Space Complexity:
- O(1), constant extra space.
*/

package Arrays.Medium;

public class _1701_Average_Waiting_Time {
    // Method to find the average waiting time of all customers
    public static double averageWaitingTime(int[][] customers) {
        // Initialize a variable for the total waiting time
        long totalWaitingTime = 0;

        // Initialzie the current time for tracking
        int currentTime = 0;

        // Iterate over the customers to find the total waiting time
        for (int[] customer : customers) {
            currentTime = Math.max(currentTime, customer[0]) + customer[1];
            totalWaitingTime += currentTime - customer[0];
        }

        // Return average waiting time
        return (double) totalWaitingTime / customers.length;
    }

    // Main method to test averageWaitingTime
    public static void main(String[] args) {
        int[][] customers = new int[][] { { 1, 2 }, { 2, 5 }, { 4, 3 } };

        double result = averageWaitingTime(customers);

        System.out.println("The average waiting time of all customers is : " + result);
    }
}