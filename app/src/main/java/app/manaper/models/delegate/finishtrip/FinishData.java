package app.manaper.models.delegate.finishtrip;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FinishData implements Serializable {

    @SerializedName("trip_arr_deps_persons")
    private String tripArrDepsPersons;

    @SerializedName("trip_arr_deps_airlines")
    private String tripArrDepsAirlines;

    @SerializedName("trip_arr_deps_time")
    private String tripArrDepsTime;

    @SerializedName("trip_arr_deps_airport")
    private String tripArrDepsAirport;

    @SerializedName("trip_arr_deps_phonenumber")
    private Object tripArrDepsPhonenumber;

    @SerializedName("FK_trips_id")
    private int fKTripsId;

    @SerializedName("trip_arr_deps_id")
    private int tripArrDepsId;

    @SerializedName("FK_users_id")
    private int fKUsersId;

    @SerializedName("trip_arr_deps_updated_at")
    private String tripArrDepsUpdatedAt;

    @SerializedName("trip_arr_deps_status")
    private int tripArrDepsStatus;

    @SerializedName("trip_arr_deps_tripnumber")
    private String tripArrDepsTripnumber;

    @SerializedName("FK_cities_id")
    private int fKCitiesId;

    @SerializedName("trip_arr_deps_date")
    private String tripArrDepsDate;

    @SerializedName("trip_arr_deps_supervisor")
    private Object tripArrDepsSupervisor;

    public void setTripArrDepsPersons(String tripArrDepsPersons) {
        this.tripArrDepsPersons = tripArrDepsPersons;
    }

    public String getTripArrDepsPersons() {
        return tripArrDepsPersons;
    }

    public void setTripArrDepsAirlines(String tripArrDepsAirlines) {
        this.tripArrDepsAirlines = tripArrDepsAirlines;
    }

    public String getTripArrDepsAirlines() {
        return tripArrDepsAirlines;
    }

    public void setTripArrDepsTime(String tripArrDepsTime) {
        this.tripArrDepsTime = tripArrDepsTime;
    }

    public String getTripArrDepsTime() {
        return tripArrDepsTime;
    }

    public void setTripArrDepsAirport(String tripArrDepsAirport) {
        this.tripArrDepsAirport = tripArrDepsAirport;
    }

    public String getTripArrDepsAirport() {
        return tripArrDepsAirport;
    }

    public void setTripArrDepsPhonenumber(Object tripArrDepsPhonenumber) {
        this.tripArrDepsPhonenumber = tripArrDepsPhonenumber;
    }

    public Object getTripArrDepsPhonenumber() {
        return tripArrDepsPhonenumber;
    }

    public void setFKTripsId(int fKTripsId) {
        this.fKTripsId = fKTripsId;
    }

    public int getFKTripsId() {
        return fKTripsId;
    }

    public void setTripArrDepsId(int tripArrDepsId) {
        this.tripArrDepsId = tripArrDepsId;
    }

    public int getTripArrDepsId() {
        return tripArrDepsId;
    }

    public void setFKUsersId(int fKUsersId) {
        this.fKUsersId = fKUsersId;
    }

    public int getFKUsersId() {
        return fKUsersId;
    }

    public void setTripArrDepsUpdatedAt(String tripArrDepsUpdatedAt) {
        this.tripArrDepsUpdatedAt = tripArrDepsUpdatedAt;
    }

    public String getTripArrDepsUpdatedAt() {
        return tripArrDepsUpdatedAt;
    }

    public void setTripArrDepsStatus(int tripArrDepsStatus) {
        this.tripArrDepsStatus = tripArrDepsStatus;
    }

    public int getTripArrDepsStatus() {
        return tripArrDepsStatus;
    }

    public void setTripArrDepsTripnumber(String tripArrDepsTripnumber) {
        this.tripArrDepsTripnumber = tripArrDepsTripnumber;
    }

    public String getTripArrDepsTripnumber() {
        return tripArrDepsTripnumber;
    }

    public void setFKCitiesId(int fKCitiesId) {
        this.fKCitiesId = fKCitiesId;
    }

    public int getFKCitiesId() {
        return fKCitiesId;
    }

    public void setTripArrDepsDate(String tripArrDepsDate) {
        this.tripArrDepsDate = tripArrDepsDate;
    }

    public String getTripArrDepsDate() {
        return tripArrDepsDate;
    }

    public void setTripArrDepsSupervisor(Object tripArrDepsSupervisor) {
        this.tripArrDepsSupervisor = tripArrDepsSupervisor;
    }

    public Object getTripArrDepsSupervisor() {
        return tripArrDepsSupervisor;
    }

    @Override
    public String toString() {
        return
                "FinishData{" +
                        "trip_arr_deps_persons = '" + tripArrDepsPersons + '\'' +
                        ",trip_arr_deps_airlines = '" + tripArrDepsAirlines + '\'' +
                        ",trip_arr_deps_time = '" + tripArrDepsTime + '\'' +
                        ",trip_arr_deps_airport = '" + tripArrDepsAirport + '\'' +
                        ",trip_arr_deps_phonenumber = '" + tripArrDepsPhonenumber + '\'' +
                        ",fK_trips_id = '" + fKTripsId + '\'' +
                        ",trip_arr_deps_id = '" + tripArrDepsId + '\'' +
                        ",fK_users_id = '" + fKUsersId + '\'' +
                        ",trip_arr_deps_updated_at = '" + tripArrDepsUpdatedAt + '\'' +
                        ",trip_arr_deps_status = '" + tripArrDepsStatus + '\'' +
                        ",trip_arr_deps_tripnumber = '" + tripArrDepsTripnumber + '\'' +
                        ",fK_cities_id = '" + fKCitiesId + '\'' +
                        ",trip_arr_deps_date = '" + tripArrDepsDate + '\'' +
                        ",trip_arr_deps_supervisor = '" + tripArrDepsSupervisor + '\'' +
                        "}";
    }
}