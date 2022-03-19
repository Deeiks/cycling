package cycling;
import java.time.LocalDateTime;
public class Stage {
    StageState stageState;
    StageType stageType;
    String stageName;
    String stageDescription;
    double stageLength;
    LocalDateTime stageStartTime;
    public static int nextStageID = 0;
    int id;


    public Stage(String stageName, String stageDescription, double stageLength, LocalDateTime stageStartTime, StageType
            stageType) {
        this.stageState = StageState.UNDER_PREPARATION;
        this.stageType = stageType;
        this.stageName = stageName;
        this.stageDescription = stageDescription;
        this.stageLength = stageLength;
        this.stageStartTime = stageStartTime;
        this.id = nextStageID;
        nextStageID++;
    }

    public String getStageName() {return stageName;}

    public int getId() {
        return id;
    }

    public static int getCurId() {
        return nextStageID-1;
    }

}
