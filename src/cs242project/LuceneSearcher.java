package cs242project;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;

public class LuceneSearcher {

	public static void main(String[] args) {
		String value = testFunction();
		searchIndexFile("university of portland");
	}
	
	public static String testFunction() {
		return "hi";
	}
	
	public static ArrayList searchIndexFile(String searchedTerm) {
		ArrayList<String> studentProfiles = new ArrayList<String>();
		try {
			DirectoryReader ireader = DirectoryReader.open(FSDirectory.open(Paths.get("/Users/roya/git/TwiterCrawler-WebApp/LuceneIndex")));
			IndexSearcher isearcher = new IndexSearcher(ireader);
		    // Parse a simple query that searches for "text":
		    QueryParser parser = new QueryParser("universities", new StandardAnalyzer());
		    Query query = parser.parse("university of portland");
		    ScoreDoc[] hits = isearcher.search(query, 100).scoreDocs;
		    System.out.println(hits.length);
		    // Iterate through the results:
		    for (int i = 0; i < hits.length; i++) {
		      Document hitDoc = isearcher.doc(hits[i].doc);
		      //System.out.println(hitDoc.getFields("universities")[0]);
		      //System.out.println(hitDoc.getField("name").stringValue());
		      String name = hitDoc.getField("name").stringValue();
		      //String iname = hitDoc.getFields("name")[0].stringValue();
		      System.out.println(name);
		      studentProfiles.add(name);
		      //String userName = hitDoc.getField("userName").stringValue();
		      String screenName = hitDoc.getFields("screenName")[0].stringValue();
		      System.out.println(screenName);
		      studentProfiles.add(screenName);
		      //String location = hitDoc.getField("location").stringValue();
		      String location = hitDoc.getFields("location")[0].stringValue();
		      System.out.println(location);
		      studentProfiles.add(location);
		      //String university = hitDoc.getField("university").stringValue();
		      String university = hitDoc.getFields("universities")[0].stringValue();
		      System.out.println(university);
		      studentProfiles.add(university);
		      //String major = hitDoc.getField("major").stringValue();
		      String major = hitDoc.getFields("major")[0].stringValue();
		      System.out.println(major);
		      studentProfiles.add(major);
		    }
		    ireader.close();
		    System.out.println("try clause");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentProfiles;
	}

}
