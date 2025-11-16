/*
LeetCode Problem: https://leetcode.com/problems/design-parking-system/

Question: 1603. Design Parking System

Problem Statement: Design a parking system for a parking lot. The parking lot has three kinds of parking spaces: big, medium, and small, with a fixed number of slots for each size.

Implement the ParkingSystem class:

ParkingSystem(int big, int medium, int small) Initializes object of the ParkingSystem class. The number of slots for each parking space are given as part of the constructor.
bool addCar(int carType) Checks whether there is a parking space of carType for the car that wants to get into the parking lot. carType can be of three kinds: big, medium, or small, which are represented by 1, 2, and 3 respectively. A car can only park in a parking space of its carType. If there is no space available, return false, else park the car in that size space and return true.

Example 1:
Input
["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
[[1, 1, 0], [1], [2], [3], [1]]
Output
[null, true, true, false, false]

Explanation
ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
parkingSystem.addCar(1); // return true because there is 1 available slot for a big car
parkingSystem.addCar(2); // return true because there is 1 available slot for a medium car
parkingSystem.addCar(3); // return false because there is no available slot for a small car
parkingSystem.addCar(1); // return false because there is no available slot for a big car. It is already occupied.

Constraints:

0 <= big, medium, small <= 1000
carType is 1, 2, or 3
At most 1000 calls will be made to addCar
 */

/*
Approach:
1. The ParkingSystem maintains three separate counters that track the available spaces
   for big, medium, and small cars. These counters are initialized using the values
   provided during the object creation.

2. To add a car, we check the type of the incoming car:
      • Type 1 → Big car
      • Type 2 → Medium car
      • Type 3 → Small car

3. For the given car type, we verify whether a parking space is available:
      - If the corresponding counter is zero, it means no space is left → return false.
      - Otherwise, decrement the appropriate counter by one and return true.

4. No complex data structures are required because the operations only involve
   checking and updating fixed counters.

Time Complexity: O(1), because each addCar call performs a constant number of operations.
Space Complexity: O(1), since only three integer variables are used.
*/

package SystemDesign.Easy;

import java.util.ArrayList;
import java.util.List;

public class _1603_Design_Parking_System {
    /**
     * Your ParkingSystem object will be instantiated and called as such:
     * ParkingSystem obj = new ParkingSystem(big, medium, small);
     * boolean param_1 = obj.addCar(carType);
     */

    // Class to make the ParkingSystem
    private static class ParkingSystem {
        // Variables to store available spaces for each car type
        private static int bigCarsSpaces;
        private static int mediumCarsSpaces;
        private static int smallCarsSpaces;

        public ParkingSystem(int big, int medium, int small) {
            // Set the initial number of spaces for big, medium, and small cars
            bigCarsSpaces = big;
            mediumCarsSpaces = medium;
            smallCarsSpaces = small;
        }

        public boolean addCar(int carType) {
            // Check the car type and park the car in the correct space
            switch (carType) {
                case 1 -> { // Big car type
                    // If no space is available, return false
                    if (bigCarsSpaces == 0) {
                        return false;
                    } else {
                        // Otherwise, reduce space count and return true
                        bigCarsSpaces--;
                        return true;
                    }
                }
                case 2 -> { // Medium car type
                    // If no space is available, return false
                    if (mediumCarsSpaces == 0) {
                        return false;
                    } else {
                        // Otherwise, reduce space count and return true
                        mediumCarsSpaces--;
                        return true;
                    }
                }
                case 3 -> { // Small car type
                    // If no space is available, return false
                    if (smallCarsSpaces == 0) {
                        return false;
                    } else {
                        // Otherwise, reduce space count and return true
                        smallCarsSpaces--;
                        return true;
                    }
                }
            }

            // If the carType is invalid, return false
            return false;
        }
    }

    // Main method to test ParkingSystem
    public static void main(String[] args) {
        String[] operations = { "ParkingSystem", "addCar", "addCar", "addCar", "addCar" };
        List<int[]> values = new ArrayList<>();

        values.add(new int[] { 1, 1, 0 });
        values.add(new int[] { 1 });
        values.add(new int[] { 2 });
        values.add(new int[] { 3 });
        values.add(new int[] { 1 });

        // Create an instance of ParkingSystem
        ParkingSystem parkingSystem = new ParkingSystem(values.get(0)[0], values.get(0)[1], values.get(0)[2]);

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("ParkingSystem")) {
                // Create the new ParkingSystem instance
                parkingSystem = new ParkingSystem(values.get(i)[0], values.get(i)[1], values.get(i)[2]);
                System.out.println("null");
            }
            if (operation.equals("addCar")) {
                // Call the addCar method with values[i][0]
                System.out.println(parkingSystem.addCar(values.get(i)[0]));
            }
        }
    }
}
