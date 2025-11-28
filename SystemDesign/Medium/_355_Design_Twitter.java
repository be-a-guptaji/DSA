/*
LeetCode Problem: https://leetcode.com/problems/design-twitter/

Question: 355. Design Twitter

Problem Statement: Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.

Example 1:
Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.

Constraints:

1 <= userId, followerId, followeeId <= 500
0 <= tweetId <= 10^4
All the tweets have unique IDs.
At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
A user cannot follow himself.
 */

/*
Approach:
1. We are designing a Twitter system where users can:
      • Post tweets
      • Follow and unfollow other users
      • View the 10 most recent tweets in their news feed

2. To maintain tweet order, we use:
      • A global integer "time" that increases with every new tweet.
      • Each tweet is stored with its tweetId and time.

3. To store tweets:
      • We use a HashMap:
            userId → List of tweets posted by that user
      • Each tweet is stored as an object containing:
            tweetId and time

4. To manage following relationships:
      • We use another HashMap:
            followerId → Set of followeeIds
      • This allows quick lookup of who a user follows.

5. Posting a Tweet:
      • Add the tweet to the list of that user.
      • Assign the current time and increment the global time.

6. Getting the News Feed:
      • Use a Max Heap (PriorityQueue) based on time.
      • Add all tweets of:
            → The user himself
            → All the users he follows
      • Extract the top 10 latest tweets from the heap.

7. Follow Operation:
      • Add followeeId to followerId’s HashSet.

8. Unfollow Operation:
      • Remove followeeId from followerId’s HashSet.

9. This design ensures:
      • Fast insertion of tweets.
      • Efficient retrieval of latest 10 tweets.
      • Constant time follow and unfollow operations.

Time Complexity:
• postTweet → O(1)
• follow / unfollow → O(1)
• getNewsFeed → O(N log N) in worst case (N = total collected tweets)
Space Complexity:
• O(U + T)
  where U = number of users
        T = total number of tweets
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class _355_Design_Twitter {
    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */

    // Class to make the Twitter
    private static class Twitter {
        // Initialize a global variable to maintain the order of tweets by time
        private int time;

        // Initialize the HashMap to store userId -> List of tweets posted by that user
        private final HashMap<Integer, ArrayList<Storage>> userTweets;

        // Initialize the HashMap to store userId -> Set of followees
        private final HashMap<Integer, HashSet<Integer>> userGraph;

        public Twitter() {
            // Initialize the global time as zero
            this.time = 0;

            // Initialize the tweets storage HashMap
            this.userTweets = new HashMap<>();

            // Initialize the followers graph HashMap
            this.userGraph = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            // If the userId is not present in the userTweets then initialize the list
            this.userTweets.putIfAbsent(userId, new ArrayList<>());

            // Add the tweet with the current time to maintain tweet order
            this.userTweets.get(userId).add(new Storage(tweetId, time++));
        }

        public List<Integer> getNewsFeed(int userId) {
            // Initialize the priority queue (max heap) based on tweet time
            PriorityQueue<Storage> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);

            // If user has posted tweets then add them to the heap
            if (this.userTweets.containsKey(userId)) {
                maxHeap.addAll(userTweets.get(userId));
            }

            // Get the set of followees for the given userId
            HashSet<Integer> follows = this.userGraph.get(userId);

            // If the user follows someone then add their tweets also
            if (follows != null) {
                for (int followee : follows) {
                    if (this.userTweets.containsKey(followee)) {
                        maxHeap.addAll(this.userTweets.get(followee));
                    }
                }
            }

            // Initialize the list to store the recent 10 tweets
            List<Integer> feed = new ArrayList<>();

            // Pick only the top 10 most recent tweets
            while (!maxHeap.isEmpty() && feed.size() < 10) {
                feed.add(maxHeap.poll().tweetId);
            }

            // Return the news feed
            return feed;
        }

        public void follow(int followerId, int followeeId) {
            // If followerId is not present then initialize a new HashSet
            this.userGraph.putIfAbsent(followerId, new HashSet<>());

            // Add the followeeId to the follower's follow list
            this.userGraph.get(followerId).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            // If followerId is present in the HashMap then remove followeeId
            if (this.userGraph.containsKey(followerId)) {
                this.userGraph.get(followerId).remove(followeeId);
            }
        }

        // Helper class to store tweetId and time
        private class Storage {
            // Initialize the variable for tweetId
            private final int tweetId;

            // Initialize the variable for time of tweet
            private final int time;

            public Storage(int tID, int t) {
                // Set the tweetId
                this.tweetId = tID;

                // Set the time of the tweet
                this.time = t;
            }
        }
    }

    // Main method to test Twitter
    public static void main(String[] args) {

        String[] operations = {
                "Twitter", "postTweet", "getNewsFeed", "follow",
                "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"
        };

        List<int[]> values = new ArrayList<>();
        values.add(new int[] {}); // Twitter
        values.add(new int[] { 1, 5 }); // postTweet(1,5)
        values.add(new int[] { 1 }); // getNewsFeed(1)
        values.add(new int[] { 1, 2 }); // follow(1,2)
        values.add(new int[] { 2, 6 }); // postTweet(2,6)
        values.add(new int[] { 1 }); // getNewsFeed(1)
        values.add(new int[] { 1, 2 }); // unfollow(1,2)
        values.add(new int[] { 1 }); // getNewsFeed(1)

        // Create instance of Twitter
        Twitter twitter = new Twitter();

        // Loop through all operations
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            switch (op) {
                case "Twitter" -> {
                    twitter = new Twitter();
                    System.out.println("null");
                }
                case "postTweet" -> {
                    twitter.postTweet(values.get(i)[0], values.get(i)[1]);
                    System.out.println("null");
                }
                case "getNewsFeed" -> {
                    System.out.println(twitter.getNewsFeed(values.get(i)[0]));
                }
                case "follow" -> {
                    twitter.follow(values.get(i)[0], values.get(i)[1]);
                    System.out.println("null");
                }
                case "unfollow" -> {
                    twitter.unfollow(values.get(i)[0], values.get(i)[1]);
                    System.out.println("null");
                }
            }
        }
    }
}
