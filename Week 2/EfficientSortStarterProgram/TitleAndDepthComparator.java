import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        int comparison = q1.getInfo().compareTo(q2.getInfo());
        
        if(comparison == 0) comparison = Double.compare(q1.getDepth(), q2.getDepth());
        
        return comparison;
    }
}
