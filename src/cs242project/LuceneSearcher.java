package cs242project;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;

import dataObjects.MapUtil;

public class LuceneSearcher {
	enum QueryType {UNIVERSITY, MAJOR};
	
	/* Note: usersInDescription does not need sorting.
	 * usersInTweets must be sorted by frequency.
	 * usersLeastRel must be sorted by Lucene default scoring.
	 */
	private HashMap<Document, Integer> usersInDescription, usersInTweets;
	private HashMap<Document, Float> usersLeastRel;

	public LuceneSearcher() {
		usersInDescription = new HashMap<>();
		usersInTweets = new HashMap<>();
		usersLeastRel = new HashMap<>();
	}
		
	public HashMap<Document, Integer> getUsersInDescription() {
		return usersInDescription;
	}

	public HashMap<Document, Integer> getUsersInTweets() {
		return usersInTweets;
	}

	public HashMap<Document, Float> getUsersLeastRel() {
		return usersLeastRel;
	}
	
	public static void main(String[] args) {
		searchLuceneIndex("western washington university");
	}

	public static ArrayList<String> searchLuceneIndex(String queryStr) {
		// SEARCH LUCENE
		ArrayList<String> studentProfiles = new ArrayList<String>();
		//String queryStr = "western washington university"; // ASSUME WE DON'T KNOW IF THIS IS COLLEGE OR MAJOR (or BOTH)
		queryStr = queryStr.trim().toLowerCase(); // Just always make queries like this. I need exact search...
		final int numResults = 100;
	
		LuceneSearcher searcher = new LuceneSearcher();
		searcher.searchIndex(queryStr, QueryType.MAJOR, numResults); 		// At most numResults. May be less.
		searcher.searchIndex(queryStr, QueryType.UNIVERSITY, numResults);	// Same. Results are in maps.
		
		// To Roya: All users in usersInDescription have -1 scores. Use these first.
		System.out.println(searcher.getUsersInDescription().size());
		for (Document d : searcher.getUsersInDescription().keySet()) {
			// Universities and majors
//			IndexableField[] majorFields = d.getFields("major");
//			IndexableField[] univFields = d.getFields("universities");
			
			// Example for loop for getting universities
//			for (IndexableField f : univFields) {
//				// Example on how to get find string.
//				if (f.stringValue().equals(queryStr)) {
//					// Example on how to get score.
//					int score = searcher.getUsersInDescription().get(d);
//					System.out.println(d.get("screenName") + " " + f.stringValue() + " " + score);
//					break;
//				}
//			}
			
			// For website: Top major and university, even if doesn't match query
			studentProfiles.add(d.getField("profileImageUrl").stringValue());
			studentProfiles.add(d.getField("description").stringValue());
			studentProfiles.add(d.getField("name").stringValue());
			studentProfiles.add(d.getField("screenName").stringValue());
			studentProfiles.add(d.getField("location").stringValue());
			if (d.getFields("universities").length != 0)
				studentProfiles.add(queryStr);
			else
				studentProfiles.add("no university");
			if (d.getFields("major").length != 0)
				studentProfiles.add(d.getFields("major")[0].stringValue());
			else
				studentProfiles.add("no major");
		}
		
		// Before using userInTweets and userLeastRel: sorting. 
		Map<Document, Integer> sortedTweetUsers = MapUtil.sortByValue(searcher.usersInTweets);
		for (Document d : sortedTweetUsers.keySet()) {
			// For website: Top major and university, even if doesn't match query
			studentProfiles.add(d.getField("profileImageUrl").stringValue());
			studentProfiles.add(d.getField("description").stringValue());
			studentProfiles.add(d.getField("name").stringValue());
			studentProfiles.add(d.getField("screenName").stringValue());
			studentProfiles.add(d.getField("location").stringValue());
			if (d.getFields("universities").length != 0)
				studentProfiles.add(d.getFields("universities")[0].stringValue());
			else
				studentProfiles.add("no university");
			if (d.getFields("major").length != 0)
				studentProfiles.add(d.getFields("major")[0].stringValue());
			else
				studentProfiles.add("no major");
		}
		
		Map<Document, Float> sortedLeastRelUsers = MapUtil.sortByValue(searcher.usersLeastRel);
		for (Document d : sortedLeastRelUsers.keySet()) {
			// For website: Top major and university, even if doesn't match query
			studentProfiles.add(d.getField("profileImageUrl").stringValue());
			studentProfiles.add(d.getField("description").stringValue());
			studentProfiles.add(d.getField("name").stringValue());
			studentProfiles.add(d.getField("screenName").stringValue());
			studentProfiles.add(d.getField("location").stringValue());
			if (d.getFields("universities").length != 0)
				studentProfiles.add(d.getFields("universities")[0].stringValue());
			else
				studentProfiles.add("no university");
			if (d.getFields("major").length != 0)
				studentProfiles.add(d.getFields("major")[0].stringValue());
			else
				studentProfiles.add("no major");
		}
		System.out.println("student profiles: " + studentProfiles);
		
		return studentProfiles;
	}
	
	static void quicksortArrays(IndexableField[] values, IndexableField[] scores, int start, int end) {
		if (end - start <= 1)
			return;
		
		IndexableField tmp;
		int pivot = end - 1;
		int currScore = -1, pivotScore = -1;
		for (int i = start; i < pivot;) {
			// Note: always consider -1 as largest value because of format
			currScore = scores[i].numericValue().intValue();
			pivotScore = scores[pivot].numericValue().intValue();
			
			if (currScore == -1) currScore = Integer.MAX_VALUE;
			if (pivotScore == -1) pivotScore = Integer.MAX_VALUE;
	
			// greatest to least
			if (currScore < pivotScore) {
				// Move scores
				tmp = scores[i];
				scores[i] = scores[pivot - 1];		// Move pivot - 1 left to where i is
				scores[pivot - 1] = scores[pivot];	// Move pivot left 1 space
				scores[pivot] = tmp;				// Move value bigger than pivot right to orig place of pivot
				
				// Repeat for values array
				tmp = values[i];
				values[i] = values[pivot - 1];		// Move pivot - 1 left to where i is
				values[pivot - 1] = values[pivot];	// Move pivot left 1 space
				values[pivot] = tmp;				// Move value bigger than pivot right to orig place of pivot
				
				pivot--;
			} else {
				i++;
			}
		}
		
		quicksortArrays(values, scores, start, pivot);		// left
		quicksortArrays(values, scores, pivot + 1, end);	// right
		
		return;
	}
	
	// General one that sorts by integer array 
	static void quicksortArrays(Object[] docs, int[] scores, int start, int end) {
		if (end - start <= 1)
			return;
		
		int tmp;
		Object docTmp;
		int pivot = end - 1;
		int currScore = -1, pivotScore = -1;
		for (int i = start; i < pivot;) {
			// Note: always consider -1 as largest value because of format
			currScore = scores[i];
			pivotScore = scores[pivot];
			
			if (currScore == -1) currScore = Integer.MAX_VALUE;
			if (pivotScore == -1) pivotScore = Integer.MAX_VALUE;
			
			if (currScore < pivotScore) {
				// Move scores
				tmp = scores[i];
				scores[i] = scores[pivot - 1];		// Move pivot - 1 left to where i is
				scores[pivot - 1] = scores[pivot];	// Move pivot left 1 space
				scores[pivot] = tmp;				// Move value bigger than pivot right to orig place of pivot
				
				// Repeat for values array
				docTmp = docs[i];
				docs[i] = docs[pivot - 1];		// Move pivot - 1 left to where i is
				docs[pivot - 1] = docs[pivot];	// Move pivot left 1 space
				docs[pivot] = docTmp;			// Move value bigger than pivot right to orig place of pivot
				
				pivot--;
			} else {
				i++;
			}
		}
		
		quicksortArrays(docs, scores, start, pivot);
		quicksortArrays(docs, scores, pivot + 1, end);
		
		return;
	}
	
	// Returns an int array, the same size as hits, that contains all hard-coded scores we stored
	static int[] getCollegeScores(ScoreDoc[] hits, IndexSearcher isearcher, String college) throws IOException {
		int[] scores = new int[hits.length];
	    
	    for (int i = 0; i < hits.length; i++) {
	    	Document hitDoc = isearcher.doc(hits[i].doc);
	    	int collegeInd = -1;
	    	IndexableField[] univFields = hitDoc.getFields("universities");
	    	IndexableField[] univScores = hitDoc.getFields("univScore");
	    	
	    	for (int j = 0; j < univFields.length; j++) {
	    		if (univFields[j].stringValue().equals(college)) {
	    			collegeInd = j;
	    			break;
	    		}
	    	}
	    	
	    	if (collegeInd != -1) {
	    		scores[i] = univScores[collegeInd].numericValue().intValue(); 
	    	} else {
	    		scores[i] = 0;
	    	}
	    }
	    
	    return scores;
	}
	
	void searchIndex(String queryStr, QueryType type, int resultCount) {
		if (queryStr == null || queryStr.isEmpty())
			return;
		
		try {
			String fieldName = null;
			String fieldScore = null;
			if (type == QueryType.UNIVERSITY) {
				fieldName = "universities";
				fieldScore = "univScore";
			} else if (type == QueryType.MAJOR) {
				fieldName = "major";
				fieldScore = "majorScore";
			} else {
				System.err.println("Error: invalid queryType");
				System.exit(-1);
			}
			
			// Set up Lucene
			DirectoryReader ireader = DirectoryReader.open(FSDirectory.open(Paths.get("/Users/roya/git/TwitterCrawler-WebApp/LuceneIndex")));
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			// Parse a simple query that searches for text
			QueryParser parser;
			parser = new QueryParser(fieldName, new StandardAnalyzer());
			Query query = parser.parse(queryStr);
			
			// Results (sorted by relevant score)
			ScoreDoc[] hits = isearcher.search(query, resultCount).scoreDocs;
			if (hits.length == 0) {
				ireader.close();
				return;
			}
			int[] scores = getCollegeScores(hits, isearcher, queryStr);
			quicksortArrays(hits, scores, 0, hits.length); 		// Order hits by score
			
			// Iterate through the results to sort universities
			Document[] docResults = new Document[hits.length];	// Ordered by score implicitly 
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				docResults[i] = hitDoc;
				  
				IndexableField[] univFields = hitDoc.getFields(fieldName);
				IndexableField[] univScores = hitDoc.getFields(fieldScore);
				  
				quicksortArrays(univFields, univScores, 0, univScores.length);
			}
			
			// Put results in maps for later sorting
			for (int i = 0; i < scores.length; i++) {
				if (scores[i] == -1) {
					// Give priority to description
					if (usersInTweets.containsKey(docResults[i]))
						usersInTweets.remove(docResults[i]);
					if (usersLeastRel.containsKey(docResults[i]))
						usersLeastRel.remove(docResults[i]);
					
					usersInDescription.put(docResults[i], scores[i]);
				} else if (scores[i] > 0) {
					// Don't put in if in description
					if (!usersInDescription.containsKey(docResults[i])) {
						// Priority to better ranks
						if (usersLeastRel.containsKey(docResults[i]))
							usersLeastRel.remove(docResults[i]);
						
						usersInTweets.put(docResults[i], scores[i]);
					}
				} else {
					// Don't put in if in description or tweets
					if (!usersInDescription.containsKey(docResults[i]) 
							&& !usersInTweets.containsKey(docResults[i])) {
						usersLeastRel.put(docResults[i], hits[i].score);
					}
				}
			}
			
			ireader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return;
	}
}