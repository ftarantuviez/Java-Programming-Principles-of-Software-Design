import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry quake : quakeData){
            if(quake.getMagnitude()>magMin){
                answer.add(quake);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            if(from.distanceTo(quake.getLocation())/1000<distMax){
                answer.add(quake);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> quakesByMagnitude = filterByMagnitude(list, 5.0);
        for(QuakeEntry q : quakesByMagnitude){
            System.out.println(q);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> quakesNearToMe = filterByDistanceFrom(list, 1000, city);
        System.out.println("Quakes near to Durham, NC: " + quakesNearToMe.size());
        for(QuakeEntry q : quakesNearToMe){
            Location cityQuake = q.getLocation();
            System.out.println(city.distanceTo(cityQuake)/1000 + " km: " + q.getInfo());
        }   
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            double depth = quake.getDepth();
            if(depth<maxDepth && depth>minDepth) answer.add(quake);
        }
        
        return answer;
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> quakesByDepth = filterByDepth(list, -4000.0, -2000.0);

        for(QuakeEntry q : quakesByDepth){
            System.out.println(q);
        }
        System.out.println("Number of quakes found: " + quakesByDepth.size());
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String searchBy, String phrase){
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            String title = quake.getInfo();
            boolean add = false;
            switch(searchBy){
            case "start":
                if(title.startsWith(phrase)) add = true;
            break;
            case "end":
                if(title.endsWith(phrase)) add = true;
            break;
            default:
                if(title.contains(phrase)) add = true;
            }
            
            if(add) ans.add(quake);
        }
        
        return ans;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        String position = "";
        String phrase = "Can";
        ArrayList<QuakeEntry> quakesFilterByPhrase = filterByPhrase(list, position, phrase);

        for(QuakeEntry q : quakesFilterByPhrase){
            System.out.println(q);
        }
        System.out.println("Number of quakes found: " + quakesFilterByPhrase.size());
    }
    
}
