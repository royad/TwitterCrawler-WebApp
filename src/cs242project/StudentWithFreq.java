package cs242project;

import java.util.ArrayList;

import cs242project.HadoopSearcher.TermInfo;

public class StudentWithFreq extends Student {
	ArrayList<TermInfo> uniList;
	ArrayList<TermInfo> majorList;
	public StudentWithFreq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentWithFreq(long userId, String screenName, String name, String description, String location,
			String profileImageURL) {
		super(userId, screenName, name, description, location, profileImageURL);
		// TODO Auto-generated constructor stub
	}
	
	
}