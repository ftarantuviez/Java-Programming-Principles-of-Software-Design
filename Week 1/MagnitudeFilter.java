import java.util.*;
import edu.duke.*;

public class MagnitudeFilter implements Filter {
    private double maxMag;
    private double minMag;
    public MagnitudeFilter(double min, double max){
        maxMag = max;
        minMag = min;
    }
    
    public boolean satisfies(QuakeEntry quake){ 
        double mag = quake.getMagnitude();
        return mag >= minMag && mag <= maxMag;
    }
}
