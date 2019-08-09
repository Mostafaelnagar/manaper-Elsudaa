package app.manaper.models.delegate;

import com.google.gson.annotations.SerializedName;

public class AcceptTripRequest {
    @SerializedName("trip_arr_deps_id")
    private String trip_arr_deps_id;
    @SerializedName("trip_fir_secs_id")
    private String trip_fir_secs_id;

    public AcceptTripRequest(String trip_arr_deps_id, String trip_fir_secs_id) {
        this.trip_arr_deps_id = trip_arr_deps_id;
        this.trip_fir_secs_id = trip_fir_secs_id;
    }

    public String getTrip_arr_deps_id() {
        return trip_arr_deps_id;
    }

    public void setTrip_arr_deps_id(String trip_arr_deps_id) {
        this.trip_arr_deps_id = trip_arr_deps_id;
    }

    public String getTrip_fir_secs_id() {
        return trip_fir_secs_id;
    }

    public void setTrip_fir_secs_id(String trip_fir_secs_id) {
        this.trip_fir_secs_id = trip_fir_secs_id;
    }
}
