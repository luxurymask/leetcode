package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Twitter {
	
	private HashMap<Integer, LinkedList<Tweet>> feeds= new HashMap<>();
	
	private static class Tweet implements Comparable<Tweet>{
		
		private static int TIME_INDEX = 0;
		private final int id;
		private final int time;
		
		Tweet(int id){
			this.id = id;
			this.time = TIME_INDEX++;
			
		}
		
		public int compareTo(Tweet t) {
			return t.time - time;
		}
		
	}
	
    /** Initialize your data structure here. */
    public Twitter() {
        
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
    	LinkedList<Tweet> feed = feeds.get(userId);
        if(feed == null){
        	
        }
    }
    
//    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
//    public List<Integer> getNewsFeed(int userId) {
//        
//    }
//    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
