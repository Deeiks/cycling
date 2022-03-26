package cycling;
import java.time.LocalTime;
public class Segment {
    double segmentLocation;
    double segmentLength;
    SegmentType type;
    double averageGradient;
    int id;
    static int nextId = 0;



    public Segment(double loc, double segmentLength, SegmentType type, double avgGradient) {
        this.segmentLocation = loc;
        this.segmentLength = segmentLength;
        this.type = type;
        this.averageGradient = avgGradient;
        this.id  = nextId;
        nextId++;
    }

    public double getSegmentLength() {
        return segmentLength;
    }

    public int getId() {return id;}

    public static int getCurId() {
        return nextId-1;
    }
}
