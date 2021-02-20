import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String[] q1Words = q1.getInfo().split(" ");
        String q1LastWord = q1Words[q1Words.length-1];
        String[] q2Words = q2.getInfo().split(" ");
        String q2LastWord = q2Words[q2Words.length-1];
        
        int comparison = q1LastWord.compareTo(q2LastWord);
        if(comparison == 0){
            comparison = Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        
        return comparison;
    }
}
