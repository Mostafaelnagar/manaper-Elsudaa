package app.manaper.models.delegate.firstDetails;

import android.util.Log;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import app.manaper.R;
import app.manaper.base.volleyutils.MyApplication;

public class Distenation {

    @SerializedName("trip_fir_secs_hotel")
    private String tripFirSecsHotel;

    @SerializedName("trip_fir_secs_from")
    private String tripFirSecsFrom;

    @SerializedName("trip_fir_secs_status")
    private int tripFirSecsStatus;

    @SerializedName("trip_fir_secs_id")
    private int tripFirSecsId;

    @SerializedName("trip_fir_secs_to")
    private String tripFirSecsTo;

    @SerializedName("trip_fir_secs_nights")
    private String tripFirSecsNights;

    @SerializedName("trip_fir_secs_transport")
    private String tripFirSecsTransport;

    @SerializedName("related_places")
    private List<DelegatePlaces> relatedPlaces;

    @SerializedName("trip_fir_secs_desc")
    private String tripFirSecsDesc;

    @SerializedName("FK_trips_id")
    private int fKTripsId;

    @SerializedName("FK_cities_id")
    private int fKCitiesId;
    @SerializedName("city")
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public void setTripFirSecsStatus(int tripFirSecsStatus) {
        this.tripFirSecsStatus = tripFirSecsStatus;
    }

    public int getTripFirSecsStatus() {
        return tripFirSecsStatus;
    }

    public void setTripFirSecsId(int tripFirSecsId) {
        this.tripFirSecsId = tripFirSecsId;
    }

    public int getTripFirSecsId() {
        return tripFirSecsId;
    }

    public void setTripFirSecsTo(String tripFirSecsTo) {
        this.tripFirSecsTo = tripFirSecsTo;
    }

    public String getTripFirSecsTo() {
        return tripFirSecsTo;
    }

    public void setTripFirSecsNights(String tripFirSecsNights) {
        this.tripFirSecsNights = tripFirSecsNights;
    }

    public String getTripFirSecsNights() {
        return tripFirSecsNights;
    }

    public void setTripFirSecsTransport(String tripFirSecsTransport) {
        this.tripFirSecsTransport = tripFirSecsTransport;
    }

    public String getTripFirSecsTransport() {
        if (tripFirSecsTransport != null) {
            if (tripFirSecsTransport.equals("1"))
                tripFirSecsTransport = MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.yesText);
            else
                tripFirSecsTransport = MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.other);
        }
        return tripFirSecsTransport;
    }

    public void setRelatedPlaces(List<DelegatePlaces> relatedPlaces) {
        this.relatedPlaces = relatedPlaces;
    }

    public List<DelegatePlaces> getRelatedPlaces() {
        return relatedPlaces;
    }

    public void setTripFirSecsDesc(String tripFirSecsDesc) {
        this.tripFirSecsDesc = tripFirSecsDesc;
    }

    public String getTripFirSecsDesc() {
        return tripFirSecsDesc;
    }

    public void setFKTripsId(int fKTripsId) {
        this.fKTripsId = fKTripsId;
    }

    public int getFKTripsId() {
        return fKTripsId;
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
                "Distenation{" +
                        "trip_fir_secs_hotel = '" + tripFirSecsHotel + '\'' +
                        ",trip_fir_secs_from = '" + tripFirSecsFrom + '\'' +
                        ",trip_fir_secs_status = '" + tripFirSecsStatus + '\'' +
                        ",trip_fir_secs_id = '" + tripFirSecsId + '\'' +
                        ",trip_fir_secs_to = '" + tripFirSecsTo + '\'' +
                        ",trip_fir_secs_nights = '" + tripFirSecsNights + '\'' +
                        ",trip_fir_secs_transport = '" + tripFirSecsTransport + '\'' +
                        ",related_places = '" + relatedPlaces + '\'' +
                        ",trip_fir_secs_desc = '" + tripFirSecsDesc + '\'' +
                        ",FK_trips_id = '" + fKTripsId + '\'' +
                        ",FK_cities_id = '" + fKCitiesId + '\'' +
                        "}";
    }
}