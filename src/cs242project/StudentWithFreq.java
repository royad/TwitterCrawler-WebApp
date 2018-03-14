package cs242project;

import java.util.ArrayList;

import cs242project.HadoopSearcher.TermInfo;

public class StudentWithFreq extends Student {
	ArrayList<TermInfo> uniList;
	ArrayList<TermInfo> majorList;
	
	 public ArrayList<String> getUniListNoFreq (){
	     ArrayList<String> result = new ArrayList<String>();
		 for(int i =0;i<uniList.size();i++) {
			 result.add(uniList.get(i).termId);
	     	
	     }
		 return result;
	 }
	 public ArrayList<String> getMajorListNoFreq (){
	     ArrayList<String> result = new ArrayList<String>();
		 for(int i =0;i<majorList.size();i++) {
			 result.add(majorList.get(i).termId);
	     	
	     }
		 return result;
	 }

	public StudentWithFreq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentWithFreq(long userId, String screenName, String name, String description, String location,
			String profileImageURL) {
		super(userId, screenName, name, description, location, profileImageURL);
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<TermInfo> getMajors() {
		return majorList;
	}
	public ArrayList<TermInfo> getUniList() {
		return uniList;
	}
	

	
	
}