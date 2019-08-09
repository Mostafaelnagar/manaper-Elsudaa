package app.manaper.models.addTrip.addArrival;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddArrivalrequest {

    @SerializedName("trip_arr_deps_persons")
    private String tripArrDepsPersons;

    @SerializedName("trip_arr_deps_airlines")
    private String tripArrDepsAirlines;

    @SerializedName("trip_arr_deps_time")
    private String tripArrDepsTime;

    @SerializedName("trip_arr_deps_airport")
    private String tripArrDepsAirport;

    @SerializedName("trip_arr_deps_phonenumber")
    private String tripArrDepsPhonenumber;

    @SerializedName("FK_trips_id")
    private int fKTripsId;

    @SerializedName("trip_arr_deps_id")
    private int tripArrDepsId;

    @SerializedName("trip_arr_deps_type")
    private String tripArrDepsType;

    @SerializedName("trip_arr_deps_created_at")
    private String tripArrDepsCreatedAt;

    @SerializedName("check_date")
    private String checkDate;

    @SerializedName("trip_arr_deps_updated_at")
    private String tripArrDepsUpdatedAt;

    @SerializedName("step")
    private String step;

    @SerializedName("trip_arr_deps_tripnumber")
    private String tripArrDepsTripnumber;

    @SerializedName("FK_cities_id")
    private String fKCitiesId;

    @SerializedName("trip_arr_deps_date")
    private String tripArrDepsDate;

    @SerializedName("trip_arr_deps_supervisor")
    private String tripArrDepsSupervisor;

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

    public void setTripArrDepsPhonenumber(String tripArrDepsPhonenumber) {
        this.tripArrDepsPhonenumber = tripArrDepsPhonenumber;
    }

    public String getTripArrDepsPhonenumber() {
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

    public void setTripArrDepsType(String tripArrDepsType) {
        this.tripArrDepsType = tripArrDepsType;
    }

    public String getTripArrDepsType() {
        return tripArrDepsType;
    }

    public void setTripArrDepsCreatedAt(String tripArrDepsCreatedAt) {
        this.tripArrDepsCreatedAt = tripArrDepsCreatedAt;
    }

    public String getTripArrDepsCreatedAt() {
        return tripArrDepsCreatedAt;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setTripArrDepsUpdatedAt(String tripArrDepsUpdatedAt) {
        this.tripArrDepsUpdatedAt = tripArrDepsUpdatedAt;
    }

    public String getTripArrDepsUpdatedAt() {
        return tripArrDepsUpdatedAt;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    public void setTripArrDepsTripnumber(String tripArrDepsTripnumber) {
        this.tripArrDepsTripnumber = tripArrDepsTripnumber;
    }

    public String getTripArrDepsTripnumber() {
        return tripArrDepsTripnumber;
    }

    public void setFKCitiesId(String fKCitiesId) {
        this.fKCitiesId = fKCitiesId;
    }

    public String getFKCitiesId() {
        return fKCitiesId;
    }

    public void setTripArrDepsDate(String tripArrDepsDate) {
        this.tripArrDepsDate = tripArrDepsDate;
    }

    public String getTripArrDepsDate() {
        return tripArrDepsDate;
    }

    public void setTripArrDepsSupervisor(String tripArrDepsSupervisor) {
        this.tripArrDepsSupervisor = tripArrDepsSupervisor;
    }

    public String getTripArrDepsSupervisor() {
        return tripArrDepsSupervisor;
    }

    @Override
    public String toString() {
        return
                "AddArrivalrequest{" +
                        "trip_arr_deps_persons = '" + tripArrDepsPersons + '\'' +
                        ",trip_arr_deps_airlines = '" + tripArrDepsAirlines + '\'' +
                        ",trip_arr_deps_time = '" + tripArrDepsTime + '\'' +
                        ",trip_arr_deps_airport = '" + tripArrDepsAirport + '\'' +
                        ",trip_arr_deps_phonenumber = '" + tripArrDepsPhonenumber + '\'' +
                        ",fK_trips_id = '" + fKTripsId + '\'' +
                        ",trip_arr_deps_id = '" + tripArrDepsId + '\'' +
                        ",trip_arr_deps_type = '" + tripArrDepsType + '\'' +
                        ",trip_arr_deps_created_at = '" + tripArrDepsCreatedAt + '\'' +
                        ",check_date = '" + checkDate + '\'' +
                        ",trip_arr_deps_updated_at = '" + tripArrDepsUpdatedAt + '\'' +
                        ",step = '" + step + '\'' +
                        ",trip_arr_deps_tripnumber = '" + tripArrDepsTripnumber + '\'' +
                        ",fK_cities_id = '" + fKCitiesId + '\'' +
                        ",trip_arr_deps_date = '" + tripArrDepsDate + '\'' +
                        ",trip_arr_deps_supervisor = '" + tripArrDepsSupervisor + '\'' +
                        "}";
    }

    public boolean isValid() {
         return (tripArrDepsPersons != null && tripArrDepsAirlines != null
                && tripArrDepsTime != null && tripArrDepsAirport != null && tripArrDepsPhonenumber != null
                && tripArrDepsTripnumber != null && step != null
                && fKCitiesId != null && tripArrDepsDate != null && tripArrDepsSupervisor != null);
    }

    public boolean isLeavingValid() {
         return (tripArrDepsPersons != null && tripArrDepsAirlines != null
                && tripArrDepsTime != null && tripArrDepsAirport != null
                && tripArrDepsTripnumber != null && step != null
                && fKCitiesId != null && tripArrDepsDate != null);
    }
}