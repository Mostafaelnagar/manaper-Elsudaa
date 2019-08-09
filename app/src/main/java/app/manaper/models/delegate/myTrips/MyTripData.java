package app.manaper.models.delegate.myTrips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyTripData implements Serializable {

    @SerializedName("trip_fir_secs_hotel")
    private String tripFirSecsHotel;

    @SerializedName("trip_fir_secs_from")
    private String tripFirSecsFrom;

    @SerializedName("trip_fir_secs_id")
    private String tripFirSecsId;

    @SerializedName("city")
    private String city;

    @SerializedName("trip_fir_secs_type")
    private String tripFirSecsType;

    @SerializedName("FK_cities_id")
    private int fKCitiesId;
    @Expose
    @SerializedName("trip_arr_deps_type")
    private String tripArrDepsType;
    @SerializedName("trip_arr_deps_id")
    private int tripArrDepsId;

    public int getTripArrDepsId() {
        return tripArrDepsId;
    }

    public void setTripArrDepsId(int tripArrDepsId) {
        this.tripArrDepsId = tripArrDepsId;
    }

    public String getTripArrDepsType() {
        return tripArrDepsType;
    }

    public void setTripArrDepsType(String tripArrDepsType) {
        this.tripArrDepsType = tripArrDepsType;
    }

    public void setTripFirSecsHotel(String tripFirSecsHotel) {
        this.tripFirSecsHotel = tripFirSecsHotel;
    }

    public String getTripFirSecsHotel() {
        return tripFirSecsHotel;
    }

    public void setTripFirSecsFrom(String tripFirSecsFrom) {
        this.tripFirSecsFrom = tripFirSecsFrom;
    }

    public String getTripFirSecsFrom() {
        return tripFirSecsFrom;
    }

    public void setTripFirSecsId(String tripFirSecsId) {
        this.tripFirSecsId = tripFirSecsId;
    }

    public String getTripFirSecsId() {
        return tripFirSecsId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setTripFirSecsType(String tripFirSecsType) {
        this.tripFirSecsType = tripFirSecsType;
    }

    public String getTripFirSecsType() {
        return tripFirSecsType;
    }

    public void setFKCitiesId(int fKCitiesId) {
        this.fKCitiesId = fKCitiesId;
    }

    public int getFKCitiesId() {
        return fKCitiesId;
    }

    @Override
    public String toString() {
        return
                "MyTripData{" +
                        "trip_fir_secs_hotel = '" + tripFirSecsHotel + '\'' +
                        ",trip_fir_secs_from = '" + tripFirSecsFrom + '\'' +
                        ",trip_fir_secs_id = '" + tripFirSecsId + '\'' +
                        ",city = '" + city + '\'' +
                        ",trip_fir_secs_type = '" + tripFirSecsType + '\'' +
                        ",fK_cities_id = '" + fKCitiesId + '\'' +
                        "}";
    }
}