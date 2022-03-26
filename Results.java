package cycling;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.MILLIS;

public class Results {
    int riderID;
    LocalTime checkPoints[];
    LocalTime raceResults;

    public Results(int riderID, LocalTime[] checkPoints) {
        this.riderID = riderID;
        this.checkPoints = checkPoints;
        this.raceResults = LocalTime.ofSecondOfDay(MILLIS.between(checkPoints[0],checkPoints[checkPoints.length]));
    }
}
