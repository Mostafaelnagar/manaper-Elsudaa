package app.manaper.models.company;


import com.google.gson.annotations.SerializedName;


public class Arrival   {

    @SerializedName("trip_arr_deps_persons")
    private String tripArrDepsPersons;

    @SerializedName("laravel_through_key")
    private int laravelThroughKey;

    @SerializedName("trip_arr_deps_airlines")
    private String tripArrDepsAirlines;

    @SerializedName("trip_arr_deps_time")
    private String tripArrDepsTime;

    @SerializedName("city")
    private String city;

    @SerializedName("trip_arr_deps_airport")
    private String tripArrDepsAirport;

    @SerializedName("trip_arr_deps_phonenumber")
    private String tripArrDepsPhonenumber;

    @SerializedName("FK_trips_id")
    private int fKTripsId;

    @SerializedName("trip_arr_deps_id")
    private int tripArrDepsId;

    @SerializedName("trip_arr_deps_type")
    private int tripArrDepsType;

    @SerializedName("trip_arr_deps_tripnumber")
    private String tripArrDepsTripnumber;

    @SerializedName("FK_cities_id")
    private int fKCitiesId;

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

    public void setLaravelThroughKey(int laravelThroughKey) {
        this.laravelThroughKey = laravelThroughKey;
    }

    public int getLaravelThroughKey() {
        return laravelThroughKey;
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

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
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

    public void setTripArrDepsType(int tripArrDepsType) {
        this.tripArrDepsType = tripArrDepsType;
    }

    public int getTripArrDepsType() {
        return tripArrDepsType;
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

    public void setTripArrDepsSupervisor(String tripArrDepsSupervisor) {
        this.tripArrDepsSupervisor = tripArrDepsSupervisor;
    }

    public String getTripArrDepsSupervisor() {
        return tripArrDepsSupervisor;
    }

    @Override
    public String toString() {
        return
                "Arrival{" +
                        "trip_arr_deps_persons = '" + tripArrDepsPersons + '\'' +
                        ",laravel_through_key = '" + laravelThroughKey + '\'' +
                        ",trip_arr_deps_airlines = '" + tripArrDepsAirlines + '\'' +
                        ",trip_arr_deps_time = '" + tripArrDepsTime + '\'' +
                        ",city = '" + city + '\'' +
                        ",trip_arr_deps_airport = '" + tripArrDepsAirport + '\'' +
                        ",trip_arr_deps_phonenumber = '" + tripArrDepsPhonenumber + '\'' +
                        ",fK_trips_id = '" + fKTripsId + '\'' +
                        ",trip_arr_deps_id = '" + tripArrDepsId + '\'' +
                        ",trip_arr_deps_type = '" + tripArrDepsType + '\'' +
                        ",trip_arr_deps_tripnumber = '" + tripArrDepsTripnumber + '\'' +
                        ",fK_cities_id = '" + fKCitiesId + '\'' +
                        ",trip_arr_deps_date = '" + tripArrDepsDate + '\'' +
                        ",trip_arr_deps_supervisor = '" + tripArrDepsSupervisor + '\'' +
                        "}";
    }
}