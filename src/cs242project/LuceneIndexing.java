package cs242project;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class LuceneIndexing {
    // MySQL username and password 
	private static String sqlUserName  = "root";
	private static String sqlPassword  = "password";
	
	public static void main(String[] args) throws SQLException {
	
		sqlUserName = args[0];
		sqlPassword = args[1];
		readAllUsersFromTable();
	}
	
	
	private static void readAllUsersFromTable() throws SQLException {
		try {
			// Lucene Code
			// FIXME: Change directory to where you like
			Directory dir = FSDirectory.open(Paths.get("LuceneIndex"));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(dir, iwc);
			Document document;
			
			int counter = 0;
			
			iwc.setOpenMode(OpenMode.CREATE);
			
			// SQL Code
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter_students?autoReconnect=true&useSSL=false", sqlUserName, sqlPassword);
		     String query = "SELECT * FROM twitter_students.students";
		     
		     conn.setAutoCommit(false);
		     
		     // create the mysql insert preparedstatement
		     java.sql.Statement preparedStmt = conn.createStatement();
		     preparedStmt.setFetchSize(50);
		     ResultSet rs = preparedStmt.executeQuery(query);
		     
		
		    String userId,screenName,name,location,description,major;
		    ArrayList<String> university; 
		    long startTime = System.currentTimeMillis();
		    long timeuse = System.currentTimeMillis()-startTime;
	  	   	System.out.println("index: "+counter+" accounts - use:" +timeuse+"milisec");
		     
		     while(rs.next()) {
		    	 document = new Document();
		    	
		    	userId = rs.getString("userId");
		  	   	screenName =rs.getString("screenName");
		  	   	name = rs.getString("name");
		  	   	location = rs.getString("location");
		  	   	description = rs.getString("description");
		  	   	major = rs.getString("major");
		  	   	university = convertArrayList(rs.getString("universities"));
		  	   	
		  	   	if(counter>=100 && counter%500==0) {
		  	   		timeuse = System.currentTimeMillis()-startTime;
		  	   		System.out.println("index: "+counter+" accounts - use:" +timeuse+"milisec");
		  	   	}
		  	   	counter++;
		  	   	/* Note on StringField vs TextField and SortedDocValuesField:
		  	   	 * StringField: Indexes but does not tokenize
		  	   	 * TextField: Indexes and Tokenizes
		  	   	 * SortedDocValuesField: Allows results to be sorted easily		  	   	
		  	   	 */
		  	   	document.add(new StringField("userId", userId, Field.Store.YES));
		  	   	document.add(new StringField("screenName", screenName, Field.Store.YES));
		  	   	document.add(new SortedDocValuesField("screenName", new BytesRef(screenName)));
		  	   	document.add(new StringField("name", name, Field.Store.YES));
		  	   	document.add(new SortedDocValuesField("name", new BytesRef(name)));
		  	   	document.add(new StringField("location", location, Field.Store.YES));
		  	   	document.add(new SortedDocValuesField("location", new BytesRef(location)));
		  	   	document.add(new TextField("description", description, Field.Store.YES));
		  	   	document.add(new TextField("major", major, Field.Store.YES));
		  	   	
		  	   	for(int i =0;i<university.size();i++) {
		  	   		Field field = new StringField("universities", university.get(i), Field.Store.YES);
		  	   		document.add(field);
		  	   	}
		  	   	writer.addDocument(document);
		     
		     }
		     rs.close();
		     writer.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
		// End of writing index
		System.out.println("Finished with file");
	}
	
	private static void readUserByCol(String col, String cmpString) throws SQLException {
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter_students?autoReconnect=true&useSSL=false", "root", "password");
         String query = "SELECT * FROM twitter_students.students WHERE "+col+" = '"+ cmpString+"'" ;
         
         conn.setAutoCommit(false);
         
         // create the mysql insert preparedstatement
         java.sql.Statement preparedStmt = conn.createStatement();
         preparedStmt.setFetchSize(50);
         ResultSet rs = preparedStmt.executeQuery(query);
         

         String screenName,name,location,description,major;
         String university,userId; 
         
         while(rs.next()) {	
        	 	userId = rs.getString("userId");
      	   	screenName =rs.getString("screenName");
      	   	name = rs.getString("name");
      	   	location = rs.getString("location");
      	   	description = rs.getString("description");
      	   	major = rs.getString("major");
      	   	university = rs.getString("universities");
      	   	
      	   	//here's for creating document on lucene

      	   	System.out.println(convertArrayList(university));
      	   	
         }
         rs.close();
		
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
	public static ArrayList<String> convertArrayList(String text){
		ArrayList<String> result = new ArrayList<String>();
		String[] splitted = text.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		for(int i=0;i<splitted.length;i++) {
			result.add(splitted[i].trim());
		}
		
		return result;
	}

}
