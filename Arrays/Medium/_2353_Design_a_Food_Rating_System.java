/*
LeetCode Problem: https://leetcode.com/problems/design-a-food-rating-system/

Question: 2353. Design a Food Rating System

Problem Statement: Design a food rating system that can do the following:

Modify the rating of a food item listed in the system.
Return the highest-rated food item for a type of cuisine in the system.
Implement the FoodRatings class:

FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
foods[i] is the name of the ith food,
cuisines[i] is the type of cuisine of the ith food, and
ratings[i] is the initial rating of the ith food.
void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.

Example 1:
Input
["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
[[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
Output
[null, "kimchi", "ramen", null, "sushi", null, "ramen"]
Explanation
FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
foodRatings.highestRated("korean"); // return "kimchi"
                                    // "kimchi" is the highest rated korean food with a rating of 9.
foodRatings.highestRated("japanese"); // return "ramen"
                                      // "ramen" is the highest rated japanese food with a rating of 14.
foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
foodRatings.highestRated("japanese"); // return "sushi"
                                      // "sushi" is the highest rated japanese food with a rating of 16.
foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
foodRatings.highestRated("japanese"); // return "ramen"
                                      // Both "sushi" and "ramen" have a rating of 16.
                                      // However, "ramen" is lexicographically smaller than "sushi".

Constraints:
1 <= n <= 2 * 10^4
n == foods.length == cuisines.length == ratings.length
1 <= foods[i].length, cuisines[i].length <= 10
foods[i], cuisines[i] consist of lowercase English letters.
1 <= ratings[i] <= 10^8
All the strings in foods are distinct.
food will be the name of a food item in the system across all calls to changeRating.
cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
At most 2 * 10^4 calls in total will be made to changeRating and highestRated.
*/

/*
Approach: HashMap + TreeSet (Ordered Rating Management)

Goal:
- Support:
   1. Updating food ratings efficiently.
   2. Retrieving highest-rated food for a cuisine.

Core Idea:
- Use HashMaps for direct lookups:
    food → rating
    food → cuisine
- Maintain a TreeSet per cuisine sorted by:
    1. Higher rating first
    2. Lexicographically smaller food name for ties
- TreeSet allows efficient insertion, deletion,
  and retrieval of highest-rated food.

Algorithm Steps:
1. Initialization:
   - Store:
       foodToRating
       foodToCuisine
   - Insert FoodPair into cuisine-specific TreeSet.
2. changeRating(food, newRating):
   - Find cuisine and old rating.
   - Remove old FoodPair from TreeSet.
   - Insert updated FoodPair.
   - Update rating map.
3. highestRated(cuisine):
   - Return first element of TreeSet.

Time Complexity:
- Constructor:
   O(n log n)
- changeRating:
   O(log n)
- highestRated:
   O(1)

Space Complexity:
- O(n)

Result:
- Efficiently supports dynamic rating updates
  and highest-rated cuisine queries.
*/

package Arrays.Medium;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */

// FoodRatings Class
class FoodRatings {
  // Initialize the maps for the operations
  private final HashMap<String, Integer> foodToRating;
  private final HashMap<String, String> foodToCuisine;
  private final HashMap<String, TreeSet<FoodPair>> cuisineToSortedSet;

  // Make the FoodPair class
  private final class FoodPair {
    // Initialize the rating and food variable
    final String food;
    final int rating;

    public FoodPair(String food, int rating) {
      this.food = food;
      this.rating = rating;
    }
  }

  public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
    // Initialize the maps for the operations
    this.foodToRating = new HashMap<>();
    this.foodToCuisine = new HashMap<>();
    this.cuisineToSortedSet = new HashMap<>();

    // Fill the maps
    for (int i = 0; i < foods.length; i++) {
      // Get the current food, rating and cuisine
      String food = foods[i];
      String cuisine = cuisines[i];
      int rating = ratings[i];

      // Update the maps
      this.foodToRating.put(food, rating);
      this.foodToCuisine.put(food, cuisine);
      this.cuisineToSortedSet.computeIfAbsent(cuisine, k -> new TreeSet<>((a, b) -> {
        if (a.rating != b.rating) {
          return Integer.compare(b.rating, a.rating);
        }
        return a.food.compareTo(b.food);
      })).add(new FoodPair(food, rating));
    }
  }

  public void changeRating(String food, int newRating) {
    // Get the cuisine from the food
    String cuisine = this.foodToCuisine.get(food);

    // Get the old rating
    int oldRating = this.foodToRating.get(food);

    // Get the TreeSet of the cuisine
    TreeSet<FoodPair> treeSet = this.cuisineToSortedSet.get(cuisine);

    // Remove the old rating and update the new rating
    treeSet.remove(new FoodPair(food, oldRating));
    treeSet.add(new FoodPair(food, newRating));

    // Update the foodToRating map
    this.foodToRating.put(food, newRating);
  }

  public String highestRated(String cuisine) {
    // Return the highest rated cuisine food
    return this.cuisineToSortedSet.get(cuisine).getFirst().food;
  }
}

public class _2353_Design_a_Food_Rating_System {
  // Main method to test FoodRatings
  public static void main(String[] args) {
    String[] operations = {
        "FoodRatings",
        "highestRated",
        "highestRated",
        "changeRating",
        "highestRated",
        "changeRating",
        "highestRated"
    };

    Object[][] values = {
        {
            new String[] { "kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi" },
            new String[] { "korean", "japanese", "japanese", "greek", "japanese", "korean" },
            new int[] { 9, 12, 8, 15, 14, 7 }
        },
        { "korean" },
        { "japanese" },
        { "sushi", 16 },
        { "japanese" },
        { "ramen", 16 },
        { "japanese" }
    };

    FoodRatings foodRatings = new FoodRatings(new String[0], new String[0], new int[0]);

    for (int i = 0; i < operations.length; i++) {

      String operation = operations[i];

      if (operation.equals("FoodRatings")) {

        String[] foods = (String[]) values[i][0];
        String[] cuisines = (String[]) values[i][1];
        int[] ratings = (int[]) values[i][2];

        foodRatings = new FoodRatings(foods, cuisines, ratings);

        System.out.println("null");
      }

      if (operation.equals("highestRated")) {

        String cuisine = (String) values[i][0];

        System.out.println(
            foodRatings.highestRated(cuisine));
      }

      if (operation.equals("changeRating")) {

        String food = (String) values[i][0];
        int newRating = (int) values[i][1];

        foodRatings.changeRating(food, newRating);

        System.out.println("null");
      }
    }
  }
}
