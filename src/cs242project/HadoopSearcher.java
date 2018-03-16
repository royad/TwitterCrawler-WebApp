package cs242project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HadoopSearcher {
    private static String HadoopIdxUniPath = "/Users/roya/git/TwitterCrawler-WebApp/hadoopIndex/";
    private static String sqlUserName  = "root";
    private static String sqlPassword  = "roya";
    
    public static HashMap<TermInfo,String> hIdxUniversity;
    public HashMap<TermInfo,String> hIdxMajor;
    public HashMap<TermInfo,String> hIdxLocation;
    public HashMap<TermInfo,String> hIdxDescription;
    
    public static HashMap<String,Integer> hIdxUniversityKey;
    public HashMap<String,Integer> hIdxMajorKey;
    public HashMap<String,Integer> hIdxLocationKey;
    public HashMap<String,Integer> hIdxDescriptionKey;
    
    
    public static class TermInfo implements Comparable<TermInfo> {
        public String termId;
        public int tf;

        public int compareTo(TermInfo o) {
        		int value1 = this.tf;
        		int value2 = o.tf;
        		
        		if(value1==-1) value1= Integer.MAX_VALUE;
        		if(value2==-1) value2= Integer.MAX_VALUE;
        	
            return value1-value2 ;
        }
        
       
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((termId == null) ? 0 : termId.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TermInfo other = (TermInfo) obj;
			if (termId == null) {
				if (other.termId != null)
					return false;
			} else if (!termId.equals(other.termId))
				return false;
			return true;
		}


		public String toString() {
            return "(term=" + this.termId + "; tf=" + this.tf + ")";
        }
    }
    
    public static TermInfo convertFront(String input){
    		TermInfo result = new TermInfo();

        String[] splitted = input.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        result.tf = Integer.parseInt(splitted[1].trim());
        result.termId = splitted[0].trim();
       
        return result;
    }
    public static ArrayList<TermInfo> convertLast(String input){
    		ArrayList<TermInfo> result = new ArrayList<TermInfo>();
	
	    String[] splitted = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	    for(int i=0;i<splitted.length;i++) {
	    		String str = splitted[i].substring(1, splitted[i].length()-1);
	    		String[] wholeTerm = str.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	    		
	    		String key = wholeTerm[0].split("=")[1].trim();
	    		int val = Integer.parseInt(wholeTerm[1].split("=")[1].trim());
	    		TermInfo eachTerm = new TermInfo();
	    		eachTerm.termId = key;
	    		eachTerm.tf = val;
	    		result.add(eachTerm);
	    }
	    
	    return result;
    }

	public HadoopSearcher() throws IOException {
		this.hIdxUniversity = new HashMap<TermInfo,String>();
		this.hIdxMajor = new HashMap<TermInfo,String>();
		this.hIdxLocation = new HashMap<TermInfo,String>();
		this.hIdxDescription = new HashMap<TermInfo,String>();
		
		this.hIdxUniversityKey = new HashMap<String,Integer>();
		this.hIdxDescriptionKey = new HashMap<String,Integer>();
		this.hIdxMajorKey = new HashMap<String,Integer>();
		this.hIdxLocationKey = new HashMap<String,Integer>();
		
		
        File universityF = new File(HadoopIdxUniPath+"hadoopIndexUniversity");
        File majorF = new File(HadoopIdxUniPath+"hadoopIndexMajor");
        File locationF = new File(HadoopIdxUniPath+"hadoopIndexLocation");
        File descriptionF = new File(HadoopIdxUniPath+"hadoopIndexDescription");

        	//locationF
        BufferedReader br = new BufferedReader(new FileReader(locationF));
        String line;
        while ((line = br.readLine()) != null) {
            String[] spritted = line.split("TermInfoArray");
            String front = spritted[0].trim();
            String last = spritted[1].trim();
            
            front = front.substring(1, front.length()-1).trim();
            last = last.substring(1, last.length()-1).trim();
            
            TermInfo frontTerm = convertFront(front);
            hIdxLocationKey.put(frontTerm.termId, frontTerm.tf);
            hIdxLocation.put(frontTerm, last);
        }
   
	    	//university
	    br = new BufferedReader(new FileReader(universityF));
	    while ((line = br.readLine()) != null) {
	        String[] spritted = line.split("TermInfoArray");
	        String front = spritted[0].trim();
	        String last = spritted[1].trim();
	        
	        front = front.substring(1, front.length()-1).trim();
	        last = last.substring(1, last.length()-1).trim();
	        
	        TermInfo frontTerm = convertFront(front);
	        hIdxUniversityKey.put(frontTerm.termId, frontTerm.tf);
	        hIdxUniversity.put(frontTerm, last);
	    }
	 
	    	//major
	    br = new BufferedReader(new FileReader(majorF));
	    while ((line = br.readLine()) != null) {
	        String[] spritted = line.split("TermInfoArray");
	        String front = spritted[0].trim();
	        String last = spritted[1].trim();
	        
	        front = front.substring(1, front.length()-1).trim();
	        last = last.substring(1, last.length()-1).trim();
	        
	        TermInfo frontTerm = convertFront(front);
	        hIdxMajorKey.put(frontTerm.termId, frontTerm.tf);
	        hIdxMajor.put(frontTerm, last);
	    }
	    
		//description
		br = new BufferedReader(new FileReader(descriptionF));
		while ((line = br.readLine()) != null) {
		    String[] spritted = line.split("TermInfoArray");
		    String front = spritted[0].trim();
		    String last = spritted[1].trim();
		    
		    front = front.substring(1, front.length()-1).trim().replaceAll(";;", ";");
		    last = last.substring(1, last.length()-1).trim();
		    TermInfo frontTerm = convertFront(front);
		    hIdxDescriptionKey.put(frontTerm.termId, frontTerm.tf);
		    hIdxDescription.put(frontTerm, last);
		}
		
		System.out.println("----Done Reading Idx_---");
		
		
	}
	
	public ArrayList<TermInfo> getSortedUniversity() {
		ArrayList<TermInfo> result = new ArrayList<TermInfo>(hIdxUniversity.keySet());
		Collections.sort(result,Collections.reverseOrder());
		return result;		
	}
	public ArrayList<TermInfo> getSortedLocation() {
		ArrayList<TermInfo> result = new ArrayList<TermInfo>(hIdxLocation.keySet());
		Collections.sort(result,Collections.reverseOrder());
		return result;		
	}
	public ArrayList<TermInfo> getSortedDescription() {
		ArrayList<TermInfo> result = new ArrayList<TermInfo>(hIdxDescription.keySet());
		Collections.sort(result,Collections.reverseOrder());
		return result;		
	}
	public ArrayList<TermInfo> getSortedMajor() {
		ArrayList<TermInfo> result = new ArrayList<TermInfo>(hIdxMajor.keySet());
		Collections.sort(result,Collections.reverseOrder());
		return result;		
	}
	
	public static ArrayList<TermInfo> getUniversityValueByName(String input) {	
		ArrayList<TermInfo> val = null;
		try {
		int freq = hIdxUniversityKey.get(input);
		TermInfo queryT = new TermInfo();
		queryT.termId = input;
		queryT.tf = freq;
		String stringVal = hIdxUniversity.get(queryT);
		val = convertLast(stringVal);
		Collections.sort(val,Collections.reverseOrder());	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return val;		
	}
	public ArrayList<TermInfo> getMajorValueByName(String input) {

		int freq = hIdxMajorKey.get(input);
		TermInfo queryT = new TermInfo();
		queryT.termId = input;
		queryT.tf = freq;
		String stringVal = hIdxMajor.get(queryT);
		ArrayList<TermInfo> val = convertLast(stringVal);
		Collections.sort(val,Collections.reverseOrder());	
		
		return val;		
	}
	public ArrayList<TermInfo> getDescriptionValueByName(String input) {		
		int freq = hIdxDescriptionKey.get(input);
		TermInfo queryT = new TermInfo();
		queryT.termId = input;
		queryT.tf = freq;
		String stringVal = hIdxDescription.get(queryT);
		ArrayList<TermInfo> val = convertLast(stringVal);
		Collections.sort(val,Collections.reverseOrder());	
		
		return val;		
	}
	public ArrayList<TermInfo> getLocationValueByName(String input) {
		int freq = hIdxLocationKey.get(input);
		TermInfo queryT = new TermInfo();
		queryT.termId = input;
		queryT.tf = freq;
		String stringVal = hIdxLocation.get(queryT);
		ArrayList<TermInfo> val = convertLast(stringVal);
		Collections.sort(val,Collections.reverseOrder());	
		
		return val;		
	}

    
    	

    
    

}