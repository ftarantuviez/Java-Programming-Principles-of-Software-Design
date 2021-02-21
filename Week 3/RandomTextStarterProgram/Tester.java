import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markov.setRandom(42);
        markov.setTraining(st);
        System.out.println("Follow of 't':: " + markov.getFollows("t"));
        System.out.println("Follows of '.':: " + markov.getFollows("."));
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("th");
        System.out.println("Number of characters that follows 'th':: " + follows.size());
    }
}
