import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private String myText;
    private Random myRandom;
    private int n;
    
    public MarkovModel(int N) {
    	myRandom = new Random();
    	n = N;
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> answer = new ArrayList<String>();
        int pos = 0;
        while(pos<myText.length()){
            int firstIdx = myText.indexOf(key, pos);
            int firstPos = firstIdx+key.length();
            if(firstIdx == -1) break;
            if(firstPos > myText.length()-1) break;
            
            String nextIdx = myText.substring(firstPos, firstPos+1);
            answer.add(nextIdx);
            pos = firstPos;
        }
        
        return answer;
    }
    
    public String getRandomText(int numChars){
    	if (myText == null){
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length()-n);
    	String key = myText.substring(index, index+n);
    	sb.append(key);
    	
    	for(int k=0; k < numChars-n; k++){
    	    ArrayList<String> follows = getFollows(key);
    	    if(follows.size() == 0) break;
    	    index = myRandom.nextInt(follows.size());
    	    String next = follows.get(index);
    	    sb.append(next);
    	    key = key.substring(1) + next;
    	}
    	
    	return sb.toString();
    }
}
