package cs242project;

import java.io.IOException;
import java.nio.file.Paths;

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
	
	public static String searchIndexFile(String searchedTerm) {
		try {
			DirectoryReader ireader = DirectoryReader.open(FSDirectory.open(Paths.get("LuceneIndex")));
			IndexSearcher isearcher = new IndexSearcher(ireader);
		    // Parse a simple query that searches for "text":
		    QueryParser parser = new QueryParser("universities", new StandardAnalyzer());
		    Query query = parser.parse("university of portland");
		    ScoreDoc[] hits = isearcher.search(query, 100).scoreDocs;
		    System.out.println(hits.length);
		    // Iterate through the results:
		    for (int i = 0; i < hits.length; i++) {
		      Document hitDoc = isearcher.doc(hits[i].doc);
		      System.out.println(hitDoc.getFields("universities")[0]);
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
		return "Lucene";
	}

}
