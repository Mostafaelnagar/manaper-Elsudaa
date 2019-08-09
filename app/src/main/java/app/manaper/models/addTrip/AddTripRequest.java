package app.manaper.models.addTrip;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.manaper.models.addTrip.places.PlacesDate;
import app.manaper.models.addTrip.places.PlacesItem;
import app.manaper.models.addTrip.places.PlacesName;


public class AddTripRequest {

    @SerializedName("trip_fir_secs_updated_at")
    private String tripFirSecsUpdatedAt;

    @SerializedName("trip_fir_secs_transport")
    private String tripFirSecsTransport;

    @SerializedName("trip_fir_secs_desc")
    private String tripFirSecsDesc;

    @SerializedName("FK_trips_id")
    private String fKTripsId;

    @SerializedName("trip_fir_secs_hotel")
    private String tripFirSecsHotel;

    @SerializedName("trip_fir_secs_from")
    private String tripFirSecsFrom;

    @SerializedName("trip_fir_secs_created_at")
    private String tripFirSecsCreatedAt;

    @SerializedName("trip_fir_secs_to")
    private String tripFirSecsTo;

    @SerializedName("trip_fir_secs_id")
    private int tripFirSecsId;

    @SerializedName("trip_fir_secs_type")
    private String tripFirSecsType;

    @SerializedName("trip_fir_secs_nights")
    private String tripFirSecsNights;

    @SerializedName("step")
    private String step;

    @SerializedName("FK_cities_id")
    private String fKCitiesId;

    @SerializedName("places_time")
    private List<String> placesItems;
    @SerializedName("places_date")
    private List<String> placesDates;
    @SerializedName("places")
    private List<String> placesNames;


    public String getfKCitiesId() {
        return fKCitiesId;
    }

    public void setfKCitiesId(String fKCitiesId) {
        this.fKCitiesId = fKCitiesId;
    }

    public List<String> getPlacesItems() {
        return placesItems;
    }

    public void setPlacesItems(List<String> placesItems) {
        this.placesItems = placesItems;
    }

    public List<String> getPlacesDates() {
        return placesDates;
    }

    public void setPlacesDates(List<String> placesDates) {
        this.placesDates = placesDates;
    }

    public List<String> getPlacesNames() {
        return placesNames;
    }

    public void setPlacesNames(List<String> placesNames) {
        this.placesNames = placesNames;
    }

    public void setTripFirSecsUpdatedAt(String tripFirSecsUpdatedAt) {
        this.tripFirSecsUpdatedAt = tripFirSecsUpdatedAt;
    }

    public String getTripFirSecsUpdatedAt() {
        return tripFirSecsUpdatedAt;
    }

    public void setTripFirSecsTransport(String tripFirSecsTransport) {
        this.tripFirSecsTransport = tripFirSecsTransport;
    }

    public String getTripFirSecsTransport() {
        return tripFirSecsTransport;
    }

    public void setTripFirSecsDesc(String tripFirSecsDesc) {
        this.tripFirSecsDesc = tripFirSecsDesc;
    }

    public String getTripFirSecsDesc() {
        return tripFirSecsDesc;
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

    public void setTripFirSecsCreatedAt(String tripFirSecsCreatedAt) {
        this.tripFirSecsCreatedAt = tripFirSecsCreatedAt;
    }

    public String getTripFirSecsCreatedAt() {
        return tripFirSecsCreatedAt;
    }

    public void setTripFirSecsTo(String tripFirSecsTo) {
        this.tripFirSecsTo = tripFirSecsTo;
    }

    public String getTripFirSecsTo() {
        return tripFirSecsTo;
    }

    public void setTripFirSecsId(int tripFirSecsId) {
        this.tripFirSecsId = tripFirSecsId;
    }

    public int getTripFirSecsId() {
        return tripFirSecsId;
    }

    public void setTripFirSecsType(String tripFirSecsType) {
        this.tripFirSecsType = tripFirSecsType;
    }

    public String getTripFirSecsType() {
        return tripFirSecsType;
    }

    public void setTripFirSecsNights(String tripFirSecsNights) {
        this.tripFirSecsNights = tripFirSecsNights;
    }

    public String getTripFirSecsNights() {
        return tripFirSecsNights;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    public void setFKCitiesId(String fKCitiesId) {
        this.fKCitiesId = fKCitiesId;
    }

    public String getFKCitiesId() {
        return fKCitiesId;
    }

    public String getfKTripsId() {
        return fKTripsId;
    }

    public void setfKTripsId(String fKTripsId) {
        this.fKTripsId = fKTripsId;
    }

    @Override
    public String toString() {
        return
                "AddTripRequest{" +
                        "trip_fir_secs_updated_at = '" + tripFirSecsUpdatedAt + '\'' +
                        ",trip_fir_secs_transport = '" + tripFirSecsTransport + '\'' +
                        ",trip_fir_secs_desc = '" + tripFirSecsDesc + '\'' +
                        ",fK_trips_id = '" + fKTripsId + '\'' +
                        ",trip_fir_secs_hotel = '" + tripFirSecsHotel + '\'' +
                        ",trip_fir_secs_from = '" + tripFirSecsFrom + '\'' +
                        ",trip_fir_secs_created_at = '" + tripFirSecsCreatedAt + '\'' +
                        ",trip_fir_secs_to = '" + tripFirSecsTo + '\'' +
                        ",trip_fir_secs_id = '" + tripFirSecsId + '\'' +
                        ",trip_fir_secs_type = '" + tripFirSecsType + '\'' +
                        ",trip_fir_secs_nights = '" + tripFirSecsNights + '\'' +
                        ",step = '" + step + '\'' +
                        ",fK_cities_id = '" + fKCitiesId + '\'' +
                        "}";
    }

    public boolean isFirstValid() {
        return (tripFirSecsHotel != null
                && tripFirSecsFrom != null && tripFirSecsTo != null && tripFirSecsNights != null
                && tripFirSecsDesc != null
                && fKCitiesId != null && tripFirSecsTransport != null);
    }

}
