package cat.arithmetic.leetcode.expiredLeetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author nanyin
 * @class Twitter.java
 * @description 355. 设计推特
 * @create 19:39 2020-04-13
 *
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 *
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-twitter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Twitter {

    class Tweet{
        private Integer id;
        private Integer user;

        public Tweet(Integer id, Integer user) {
            this.id = id;
            this.user = user;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUser() {
            return user;
        }

        public void setUser(Integer user) {
            this.user = user;
        }
    }

    class Follow{

        private Integer user;

        private List<Integer> followed;

        public Integer getUser() {
            return user;
        }

        public void setUser(Integer user) {
            this.user = user;
        }

        public List<Integer> getFollowed() {
            return followed;
        }

        public void setFollowed(List<Integer> followed) {
            this.followed = followed;
        }
    }

    LinkedList<Tweet> tweets;
    LinkedList<Follow> follows;

    /** Initialize your data structure here. */
    public Twitter() {
        tweets = new LinkedList<>();
        follows = new LinkedList<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId,userId);
        tweets.addFirst(tweet);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Follow follow = getFollow(userId);
        List<Integer> tw = new LinkedList<>();
        for (Tweet tweet : tweets) {
            if (follow.getFollowed() != null && (follow.getFollowed().contains(tweet.getUser()))) {
                tw.add(tweet.getId());
            }
            if(tweet.getUser() == userId){
                tw.add(tweet.id);
            }
            if (tw.size() > 10) {
                break;
            }
        }
        return tw;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Follow follow = getFollow(followerId);
        List<Integer> followed = follow.getFollowed() == null ? new LinkedList<>() : follow.getFollowed();
        followed.add(followeeId);
        follow.setFollowed(followed);
        follows.add(follow);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Follow follow = getFollow(followerId);
        List<Integer> followed = follow.getFollowed();
        if(followed !=null && followed.contains(followeeId)){
            int i = followed.indexOf(followeeId);
            followed.remove(i);
        }
        follow.setFollowed(followed);
        follows.add(follow);
    }

    private Follow getFollow(int followerId){
        Follow follow = containFollow(followerId);
        if(follow==null){
            follow = new Follow();
            follow.setUser(followerId);
            follows.add(follow);
        }
        return follow;
    }

    private Follow containFollow(int followerId){
        for (Follow follow : follows) {
            if(follow.getUser() == followerId){
                return follow;
            }
        }
        return null;
    };


    public static void main(String[] args) {
        Twitter twitter = new Twitter();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

// 用户1关注了用户2.
        twitter.follow(1, 1);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        List<Integer> newsFeed = twitter.getNewsFeed(1);

        for (Integer integer : newsFeed) {
            System.out.println(integer);
        }

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
