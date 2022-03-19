package cycling;
import java.time.LocalTime;
public class Segment {
    double segmentLocation;
    double segmentLength;
    SegmentType type;
    double averageGradient;
    int id;


    public Segment(double loc, double segmentLength, SegmentType type, double avgGradient, int ID) {
        this.segmentLocation = loc;
        this.segmentLength = segmentLength;
        this.type = type;
        this.averageGradient = avgGradient;
        this.id  = ID;
    }

}
