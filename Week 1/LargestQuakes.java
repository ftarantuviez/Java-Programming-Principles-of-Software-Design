import java.util.*;

public class LargestQuakes {
    public LargestQuakes(){}

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int maxIndex = 0;
        for(int i=0; i<quakeData.size(); i++){
            QuakeEntry quake = quakeData.get(i);
            if(quake.getMagnitude()>quakeData.get(maxIndex).getMagnitude()){
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        for(int k=howMany; k>=0; k--){
            int maxIndex = indexOfLargest(copy);
            ans.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        
        return ans;
    }
    
    public void getLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> quakes = getLargest(list, 49);
        for(QuakeEntry q : quakes){
            System.out.println(q);
        }
    }
}
