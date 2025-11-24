/*
LeetCode Problem: https://leetcode.com/problems/design-underground-system/

Question: 1396. Design Underground System

Problem Statement: An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.

Implement the UndergroundSystem class:

void checkIn(int id, string stationName, int t)
A customer with a card ID equal to id, checks in at the station stationName at time t.
A customer can only be checked into one place at a time.
void checkOut(int id, string stationName, int t)
A customer with a card ID equal to id, checks out from the station stationName at time t.
double getAverageTime(string startStation, string endStation)
Returns the average time it takes to travel from startStation to endStation.
The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.

Example 1:
Input
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

Output
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, "Leyton", 3);
undergroundSystem.checkIn(32, "Paradise", 8);
undergroundSystem.checkIn(27, "Leyton", 10);
undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
undergroundSystem.checkIn(10, "Leyton", 24);
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000
undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12

Example 2:
Input
["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
[[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]

Output
[null,null,null,5.00000,null,null,5.50000,null,null,6.66667]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(10, "Leyton", 3);
undergroundSystem.checkOut(10, "Paradise", 8); // Customer 10 "Leyton" -> "Paradise" in 8-3 = 5
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.00000, (5) / 1 = 5
undergroundSystem.checkIn(5, "Leyton", 10);
undergroundSystem.checkOut(5, "Paradise", 16); // Customer 5 "Leyton" -> "Paradise" in 16-10 = 6
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.50000, (5 + 6) / 2 = 5.5
undergroundSystem.checkIn(2, "Leyton", 21);
undergroundSystem.checkOut(2, "Paradise", 30); // Customer 2 "Leyton" -> "Paradise" in 30-21 = 9
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 6.66667, (5 + 6 + 9) / 3 = 6.66667

Constraints:

1 <= id, t <= 10^6
1 <= stationName.length, startStation.length, endStation.length <= 10
All strings consist of uppercase and lowercase English letters and digits.
There will be at most 2 * 10^4 calls in total to checkIn, checkOut, and getAverageTime.
Answers within 10-5 of the actual value will be accepted.
 */

/*
Approach:

1. We are asked to build a system that tracks:
      • When a passenger checks in (station, time)
      • When the same passenger checks out (station, time)
      • The average travel time between two stations

2. To solve this efficiently, we maintain two HashMaps:

   (A) checkInCheckOutMap → stores active check-ins  
         key   → passenger id  
         value → Info(stationName, time)  

       This allows O(1) access to retrieve the check-in data when a passenger checks out.

   (B) tripsHashMap → stores cumulative trip statistics for each route  
         key   → "start#end" (unique route identifier)  
         value → int[] { totalTime, totalTrips }

       This allows O(1) updates and O(1) average-time queries.

3. When checkIn(id, station, time) is called:
      • We store the (station, time) for this passenger in checkInCheckOutMap.

4. When checkOut(id, station, time) is called:
      • We retrieve the passenger’s check-in information.
      • We remove the passenger entry from checkInCheckOutMap.
      • We compute the total travel time for this trip.
      • We create a key "start#end" for the route.
      • We update tripsHashMap by adding to totalTime and totalTrips.

5. When getAverageTime(start, end) is called:
      • We fetch the { totalTime, totalTrips } entry from tripsHashMap.
      • We return totalTime / totalTrips.

6. Important design details:
      • Using a custom Info class avoids using Object[] and improves readability.
      • Removing passengers on checkout ensures no stale entries exist.
      • Using "start#end" as the route key avoids collisions and simplifies lookups.

Time Complexity:
      • checkIn  → O(1)
      • checkOut → O(1)
      • getAverageTime → O(1)

Space Complexity:
      • O(n) where n is the total number of active check-ins + unique routes.
*/

package SystemDesign.Medium;

import java.util.HashMap;

public class _1396_Design_Underground_System {
    /**
     * Your UndergroundSystem object will be instantiated and called as such:
     * UndergroundSystem obj = new UndergroundSystem();
     * obj.checkIn(id,stationName,t);
     * obj.checkOut(id,stationName,t);
     * double param_3 = obj.getAverageTime(startStation,endStation);
     */

    // Class to make the UndergroundSystem
    private static class UndergroundSystem {
        // Initialize the hashMap to handle check in (id -> {station, time})
        private final HashMap<Integer, Info> checkInCheckOutMap;

        // Initialize the hashMap to handle trips ("start#end" -> {totalTime,
        // totalTrips})
        private final HashMap<String, int[]> tripsHashMap;

        public UndergroundSystem() {
            // Initialize the hashMap for check-in info
            this.checkInCheckOutMap = new HashMap<>();

            // Initialize the hashMap for trip statistics
            this.tripsHashMap = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            // Store check-in station and time in the hashMap
            this.checkInCheckOutMap.put(id, new Info(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            // Get the check-in information from the hashMap
            Info info = this.checkInCheckOutMap.get(id);

            // Remove this id entry from the check-in map (important)
            this.checkInCheckOutMap.remove(id);

            // Initialize the StringBuilder object
            StringBuilder sb = new StringBuilder();
            
            // Create a unique key for the route
            sb.append(info.staion).append('#').append(stationName);

            // Convert StringBuilder to string key
            String key = sb.toString();

            // Calculate the total time of the trip
            int totalTime = t - info.time;

            // Update the tripsHashMap with total time and trips count
            if (this.tripsHashMap.containsKey(key)) {
                int[] arr = this.tripsHashMap.get(key);

                // Update the cumulative trip data
                arr[0] += totalTime; // total time
                arr[1] += 1; // number of trips
            } else {
                // Add a new route entry
                this.tripsHashMap.put(key, new int[] { totalTime, 1 });
            }
        }

        public double getAverageTime(String startStation, String endStation) {
            // Initialize the StringBuilder object
            StringBuilder sb = new StringBuilder();

            // Create a unique key for the route
            sb.append(startStation).append('#').append(endStation);

            // Convert StringBuilder to string key
            String key = sb.toString();

            // Retrieve total time and total trips
            int[] values = this.tripsHashMap.get(key);

            // Return the average time of all trips for this route
            return (double) values[0] / values[1];
        }

        // Helper class to store the value of Station and time
        private class Info {
            // Initialize the variable for station name and time
            private final String staion;
            private final int time;

            public Info(String s, int t) {
                // Set the variable for station name and time
                this.staion = s;
                this.time = t;
            }
        }
    }

    // Main method to test UndergroundSystem
    public static void main(String[] args) {

        String[] operations = {
                "UndergroundSystem", "checkIn", "checkIn", "checkIn", "checkOut", "checkOut",
                "checkOut", "getAverageTime", "getAverageTime", "checkIn", "getAverageTime",
                "checkOut", "getAverageTime"
        };

        Object[][] values = {
                {}, // UndergroundSystem()
                { 45, "Leyton", 3 }, // checkIn(45, Leyton, 3)
                { 32, "Paradise", 8 }, // checkIn(32, Paradise, 8)
                { 27, "Leyton", 10 }, // checkIn(27, Leyton, 10)
                { 45, "Waterloo", 15 }, // checkOut(45, Waterloo, 15)
                { 27, "Waterloo", 20 }, // checkOut(27, Waterloo, 20)
                { 32, "Cambridge", 22 }, // checkOut(32, Cambridge, 22)
                { "Paradise", "Cambridge" }, // getAverageTime(Paradise, Cambridge)
                { "Leyton", "Waterloo" }, // getAverageTime(Leyton, Waterloo)
                { 10, "Leyton", 24 }, // checkIn(10, Leyton, 24)
                { "Leyton", "Waterloo" }, // getAverageTime(Leyton, Waterloo)
                { 10, "Waterloo", 38 }, // checkOut(10, Waterloo, 38)
                { "Leyton", "Waterloo" } // getAverageTime(Leyton, Waterloo)
        };

        UndergroundSystem undergroundSystem = new UndergroundSystem();

        // Iterate through the operations
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            switch (op) {
                case "UndergroundSystem" -> {
                    undergroundSystem = new UndergroundSystem();
                    System.out.println("null");
                }

                case "checkIn" -> {
                    int id = (int) values[i][0];
                    String station = (String) values[i][1];
                    int time = (int) values[i][2];
                    undergroundSystem.checkIn(id, station, time);
                    System.out.println("null");
                }

                case "checkOut" -> {
                    int id = (int) values[i][0];
                    String station = (String) values[i][1];
                    int time = (int) values[i][2];
                    undergroundSystem.checkOut(id, station, time);
                    System.out.println("null");
                }

                case "getAverageTime" -> {
                    String start = (String) values[i][0];
                    String end = (String) values[i][1];
                    double avg = undergroundSystem.getAverageTime(start, end);
                    System.out.println(avg);
                }
            }
        }
    }

}
