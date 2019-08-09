package app.manaper.models.delegate;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.manaper.R;
import app.manaper.base.volleyutils.MyApplication;

public class DelTripData {
    @Expose
    @SerializedName("trip_arr_deps_airlines")
    private String tripArrDepsAirlines;
    @Expose

    @SerializedName("city")
    private String city;
    @Expose

    @SerializedName("trip_arr_deps_airport")
    private String tripArrDepsAirport;
    @Expose

    @SerializedName("trip_arr_deps_id")
    private int tripArrDepsId;
    @Expose

    @SerializedName("trip_arr_deps_tripnumber")
    private String tripArrDepsTripnumber;
    @Expose

    @SerializedName("FK_cities_id")
    private int fKCitiesId;
    @Expose

    @SerializedName("trip_arr_deps_date")
    private String tripArrDepsDate;
    @Expose
    @SerializedName("trip_arr_deps_type")
    private String tripArrDepsType;
    @SerializedName("trip_fir_secs_id")
    @Expose
    private String tripFirSecsId;
    @SerializedName("trip_fir_secs_hotel")
    @Expose
    private String tripFirSecsHotel;
    @SerializedName("trip_fir_secs_from")
    @Expose
    private String tripFirSecsFrom;
    @SerializedName("trip_fir_secs_type")
    @Expose
    private String tripFirSecsType;
    @SerializedName("trip_type")
    @Expose
    private String trip_type;
    @SerializedName("trip_fir_secs_to")
    @Expose
    private String trip_fir_secs_to;

    public String getTrip_fir_secs_to() {
        return trip_fir_secs_to;
    }

    public void setTrip_fir_secs_to(String trip_fir_secs_to) {
        this.trip_fir_secs_to = trip_fir_secs_to;
    }

    public String getTrip_type() {
        return trip_type;
    }

    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }

    public String getTripArrDepsAirlines() {
        return tripArrDepsAirlines;
    }

    public void setTripArrDepsAirlines(String tripArrDepsAirlines) {
        this.tripArrDepsAirlines = tripArrDepsAirlines;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTripArrDepsAirport() {
        return tripArrDepsAirport;
    }

    public void setTripArrDepsAirport(String tripArrDepsAirport) {
        this.tripArrDepsAirport = tripArrDepsAirport;
    }

    public int getTripArrDepsId() {
        return tripArrDepsId;
    }

    public void setTripArrDepsId(int tripArrDepsId) {
        this.tripArrDepsId = tripArrDepsId;
    }

    public String getTripArrDepsTripnumber() {
        Resources resources = MyApplication.getInstance().getApplicationContext().getResources();
        String result = "";
        if (tripArrDepsTripnumber == null) {
            if (trip_type != null) {
                if (trip_type.equals("0"))
                    trip_type = resources.getString(R.string.checkIn);
                else
                    trip_type = resources.getString(R.string.checkOut);
                result = trip_type;
            }
            return result;
        } else {
            if (tripArrDepsType != null) {
                if (tripArrDepsType.equals("0")) {
                    result = resources.getString(R.string.arrivalNum) ;
                } else if (tripArrDepsType.equals("1")) {
                    result = resources.getString(R.string.departureNum);
                }
            }
            return result;
        }
    }

    public void setTripArrDepsTripnumber(String tripArrDepsTripnumber) {
        this.tripArrDepsTripnumber = tripArrDepsTripnumber;
    }

    public int getfKCitiesId() {
        return fKCitiesId;
    }

    public void setfKCitiesId(int fKCitiesId) {
        this.fKCitiesId = fKCitiesId;
    }

    public String getTripArrDepsDate() {
        return tripArrDepsDate;
    }

    public void setTripArrDepsDate(String tripArrDepsDate) {
        this.tripArrDepsDate = tripArrDepsDate;
    }

    public String getTripArrDepsType() {
        return tripArrDepsType;
    }

    public void setTripArrDepsType(String tripArrDepsType) {
        this.tripArrDepsType = tripArrDepsType;
    }

    public String getTripFirSecsId() {
        return tripFirSecsId;
    }

    public void setTripFirSecsId(String tripFirSecsId) {
        this.tripFirSecsId = tripFirSecsId;
    }

    public String getTripFirSecsHotel() {
        return tripFirSecsHotel;
    }

    public void setTripFirSecsHotel(String tripFirSecsHotel) {
        this.tripFirSecsHotel = tripFirSecsHotel;
    }

    public String getTripFirSecsFrom() {
        Log.i("getTripFirSecsFrom", "getTripFirSecsFrom: " + tripFirSecsFrom);
        return tripFirSecsFrom;
    }

    public void setTripFirSecsFrom(String tripFirSecsFrom) {
        this.tripFirSecsFrom = tripFirSecsFrom;
    }

    public String getTripFirSecsType() {
        return tripFirSecsType;
    }

    public void setTripFirSecsType(String tripFirSecsType) {
        this.tripFirSecsType = tripFirSecsType;
    }

    @Override
    public String toString() {
        return "DelTripData{" +
                "tripArrDepsAirlines='" + tripArrDepsAirlines + '\'' +
                ", city='" + city + '\'' +
                ", tripArrDepsAirport='" + tripArrDepsAirport + '\'' +
                ", tripArrDepsId=" + tripArrDepsId +
                ", tripArrDepsTripnumber='" + tripArrDepsTripnumber + '\'' +
                ", fKCitiesId=" + fKCitiesId +
                ", tripArrDepsDate='" + tripArrDepsDate + '\'' +
                ", tripArrDepsType=" + tripArrDepsType +
                ", tripFirSecsId=" + tripFirSecsId +
                ", tripFirSecsHotel='" + tripFirSecsHotel + '\'' +
                ", tripFirSecsFrom='" + tripFirSecsFrom + '\'' +
                ", tripFirSecsType=" + tripFirSecsType +
                '}';
    }
}