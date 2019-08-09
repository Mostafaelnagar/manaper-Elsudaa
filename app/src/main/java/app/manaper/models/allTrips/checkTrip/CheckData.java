package app.manaper.models.allTrips.checkTrip;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckData implements Serializable {

    @SerializedName("step")
    private int step;

    @SerializedName("Fk_trips_id")
    private String fkTripsId;

    public void setStep(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public void setFkTripsId(String fkTripsId) {
        this.fkTripsId = fkTripsId;
    }

    public String getFkTripsId() {
        return fkTripsId;
    }

    @Override
    public String toString() {
        return
                "CheckData{" +
                        "step = '" + step + '\'' +
                        ",fk_trips_id = '" + fkTripsId + '\'' +
                        "}";
    }
}