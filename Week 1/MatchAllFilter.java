import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> allFilters;
    public MatchAllFilter(){
        allFilters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        allFilters.add(f);
    }
    
    public boolean satisfies(QuakeEntry quake){
        for(Filter filter : allFilters){
            if(!filter.satisfies(quake)) return false;
        }
        return true;
    }
}
