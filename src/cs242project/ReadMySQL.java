package cs242project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import cs242project.HadoopSearcher.TermInfo;

public class ReadMySQL {
	private static String sqlUserName  = "root";
	private static String sqlPassword  = "roya";
	

	
	
	public static StudentWithFreq readUserByCol(String col, String cmpString) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter_students?autoReconnect=true&useSSL=false", sqlUserName, sqlPassword);
        String query = "SELECT * FROM twitter_students.students WHERE "+col+" = '"+ cmpString+"'" ;
        
        conn.setAutoCommit(false);
        //ArrayList<String> result = new ArrayList<String>();
        StudentWithFreq result = new StudentWithFreq();
        
        // create the mysql insert preparedstatement
        java.sql.Statement preparedStmt = conn.createStatement();
        preparedStmt.setFetchSize(50);
        ResultSet rs = preparedStmt.executeQuery(query);
        
        
        String screenName = null,name = null,location = null,description = null,major= null;
        String university = null,userId = null; 
        
        while(rs.next()) {	
       	 	userId = rs.getString("userId");
     	   	screenName =rs.getString("screenName");
     	   	name = rs.getString("name");
     	   	location = rs.getString("location");
     	   	description = rs.getString("description");
     	   	major = rs.getString("major");
     	   	university = rs.getString("universities");
        }
        
        rs.close();
        
        result.userId = Long.parseLong(userId);
        result.screenName = screenName;
        result.name = name;
        result.screenName = screenName;
        result.location = location;
        result.description = description;
        result.majorList = convertToTermInfo(major);
        result.uniList = convertToTermInfo(university);
//        result.add(userId);
//        result.add(screenName);
//        result.add(name);
//        result.add(screenName);
//        result.add(location);
//        result.add(description);
//        result.add(major);
//        result.add(university);
//        
        return result;
        
		
	}
	private static void getAllTweetsByUserId(String userId) throws SQLException {
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter_students?autoReconnect=true&useSSL=false", "root", "password");
        String query = "SELECT * FROM twitter_students.tweets where userId ='"+ userId+"'" ;
       
        conn.setAutoCommit(false);
        
        // create the mysql insert preparedstatement
        java.sql.Statement preparedStmt = conn.createStatement();
        preparedStmt.setFetchSize(50);
        ResultSet rs = preparedStmt.executeQuery(query);

        String tweet,tweetId; 
        
        while(rs.next()) {	
     	   	
     	   	tweet = rs.getString("tweet");
     	   	tweetId = rs.getString("tweetId");
     	   	
     	   	//here's for creating document on lucene
     	   	System.out.println(tweet);
     	   	
        }
        rs.close();
		
	}
	public static ArrayList<TermInfo> convertToTermInfo(String text){
		ArrayList<TermInfo> result  = new ArrayList<TermInfo> ();
        
		// fix UMC - University of Missouri, Columbia" mistake;
		text =text.replaceAll(", columbia\"","");
		text =text.substring(1, text.length()-1);
		
		//for empty col
		if (text.length()<1) {return result;}
		
		String[] splitted =  text.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		for(int i=0;i<splitted.length;i++) {
			TermInfo tmp = new TermInfo();
			String[] eachTerm = splitted[i].split("=(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			tmp.termId = eachTerm[0].trim();
			//tmp.tf = Integer.parseInt(eachTerm[1]);
			result.add(tmp);
					
		}
		
		Collections.sort(result,Collections.reverseOrder());
		
		return result;
	}
	public static ArrayList<String> convertArrayList(String text){
		ArrayList<String> result = new ArrayList<String>();
		
		// fix UMC - University of Missouri, Columbia" mistake;
		text =text.replaceAll(", columbia\"","");
        
		
		String[] splitted = text.replaceAll("\\[", "").replaceAll("\\]", "").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		for(int i=0;i<splitted.length;i++) {
			result.add(splitted[i].trim());
		}
		
		return result;
	}
}