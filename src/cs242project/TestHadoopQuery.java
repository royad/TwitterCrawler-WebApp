package cs242project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import cs242project.HadoopSearcher.TermInfo;

public class TestHadoopQuery {

	public static void main(String[] args) throws IOException, SQLException{
		/*// TODO Auto-generated method stub
		HadoopSearcher hadoopQuery = new HadoopSearcher();
		ArrayList<TermInfo> uniList;
		ArrayList<TermInfo> majorList;
//		hadoopQuery.getSortedUniversity();
		
		
		//search by hadoopIndex
		//some University that contain comma inside please use "\"university of california, san diego\""
		//otherwise>> adelphi university with no \"
		long startTime = System.currentTimeMillis();
		ArrayList<TermInfo> q1 =hadoopQuery.getUniversityValueByName("\"university of california, san diego\"");
		long timeuse = System.currentTimeMillis()-startTime;
		
		System.out.println(" q1 is " + q1);
		//System.out.println("getUniversityValueByName timeuse: "+timeuse +"milisec");
		
		//from hadoopIndex get userID to search in mysql
		for (int i=0;i<10;i++) {
			String q1_userId = q1.get(i).termId;
			System.out.println("users " + q1_userId);
			StudentWithFreq q1_result = ReadMySQL.readUserByCol("userId",q1_userId);
			System.out.println(q1_result.userId+" "+q1_result.majorList+" "+q1_result.uniList);
		}*/
		
		getResult();
		
	}
	
	public static String getResult() throws IOException, SQLException {
		// TODO Auto-generated method stub
				HadoopSearcher hadoopQuery = new HadoopSearcher();
				ArrayList<TermInfo> uniList;
				ArrayList<TermInfo> majorList;
//				hadoopQuery.getSortedUniversity();
				
				
				//search by hadoopIndex
				//some University that contain comma inside please use "\"university of california, san diego\""
				//otherwise>> adelphi university with no \"
				long startTime = System.currentTimeMillis();
				ArrayList<TermInfo> q1 =hadoopQuery.getUniversityValueByName("\"university of california, san diego\"");
				long timeuse = System.currentTimeMillis()-startTime;
				
				System.out.println(" q1 is " + q1);
				//System.out.println("getUniversityValueByName timeuse: "+timeuse +"milisec");
				String q1_userId = "";
				//from hadoopIndex get userID to search in mysql
				for (int i=0;i<10;i++) {
					q1_userId = q1.get(i).termId;
					System.out.println("users " + q1_userId);
					StudentWithFreq q1_result = ReadMySQL.readUserByCol("userId",q1_userId);
					System.out.println(q1_result.userId+" "+q1_result.majorList+" "+q1_result.uniList);
				}
				
				return q1_userId;
	}

}