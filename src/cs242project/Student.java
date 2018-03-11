package cs242project;

import java.util.ArrayList;
import java.util.LinkedList;

public class Student {
	long userId;
	String screenName;
	String name;
	String description;
	String location;
	String profileImageURL;
	ArrayList<Tweet> tweets;
	
	public Student() {
		tweets = new ArrayList<>();
	}
	
	public Student(long userId, String screenName, String name, String description, String location, String profileImageURL) {
		this.userId = userId;
		this.screenName = screenName;
		this.name = name;
		this.description = description;
		this.location = location;
		this.profileImageURL = profileImageURL;
		tweets = new ArrayList<>();
	}

	public long getUserId() {
		return userId;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getProfileImageURL() {
		return profileImageURL;
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}
	
	public void debugPrintString() {
		System.out.format("%d;%s;%s;%s;%s;%s%n", userId, screenName, name, location, profileImageURL, description);
	}
}