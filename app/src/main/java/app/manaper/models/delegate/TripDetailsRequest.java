package app.manaper.models.delegate;

public class TripDetailsRequest {
    private String id;
    private String trip_arr_deps_type;
    private String trip_fir_secs_type;

    public TripDetailsRequest(String id, String trip_arr_deps_type, String trip_fir_secs_type) {
        this.id = id;
        this.trip_arr_deps_type = trip_arr_deps_type;
        this.trip_fir_secs_type = trip_fir_secs_type;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrip_arr_deps_type() {
        return trip_arr_deps_type;
    }

    public void setTrip_arr_deps_type(String trip_arr_deps_type) {
        this.trip_arr_deps_type = trip_arr_deps_type;
    }

    public String getTrip_fir_secs_type() {
        return trip_fir_secs_type;
    }

    public void setTrip_fir_secs_type(String trip_fir_secs_type) {
        this.trip_fir_secs_type = trip_fir_secs_type;
    }

}
