package app.manaper.models.company;

import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class ThirdCity  {

	@SerializedName("trip_fir_secs_hotel")
	private String tripFirSecsHotel;

	@SerializedName("trip_fir_secs_from")
	private String tripFirSecsFrom;

	@SerializedName("laravel_through_key")
	private int laravelThroughKey;

	@SerializedName("trip_fir_secs_id")
	private int tripFirSecsId;

	@SerializedName("trip_fir_secs_to")
	private String tripFirSecsTo;

	@SerializedName("trip_fir_secs_type")
	private int tripFirSecsType;

	@SerializedName("trip_fir_secs_nights")
	private String tripFirSecsNights;

	@SerializedName("trip_fir_secs_transport")
	private String tripFirSecsTransport;

	@SerializedName("related_places")
	private List<RelatedPlacesItem> relatedPlaces;

	@SerializedName("trip_fir_secs_desc")
	private String tripFirSecsDesc;

	@SerializedName("FK_trips_id")
	private int fKTripsId;

	@SerializedName("FK_cities_id")
	private int fKCitiesId;

	public void setTripFirSecsHotel(String tripFirSecsHotel){
		this.tripFirSecsHotel = tripFirSecsHotel;
	}

	public String getTripFirSecsHotel(){
		return tripFirSecsHotel;
	}

	public void setTripFirSecsFrom(String tripFirSecsFrom){
		this.tripFirSecsFrom = tripFirSecsFrom;
	}

	public String getTripFirSecsFrom(){
		return tripFirSecsFrom;
	}

	public void setLaravelThroughKey(int laravelThroughKey){
		this.laravelThroughKey = laravelThroughKey;
	}

	public int getLaravelThroughKey(){
		return laravelThroughKey;
	}

	public void setTripFirSecsId(int tripFirSecsId){
		this.tripFirSecsId = tripFirSecsId;
	}

	public int getTripFirSecsId(){
		return tripFirSecsId;
	}

	public void setTripFirSecsTo(String tripFirSecsTo){
		this.tripFirSecsTo = tripFirSecsTo;
	}

	public String getTripFirSecsTo(){
		return tripFirSecsTo;
	}

	public void setTripFirSecsType(int tripFirSecsType){
		this.tripFirSecsType = tripFirSecsType;
	}

	public int getTripFirSecsType(){
		return tripFirSecsType;
	}

	public void setTripFirSecsNights(String tripFirSecsNights){
		this.tripFirSecsNights = tripFirSecsNights;
	}

	public String getTripFirSecsNights(){
		return tripFirSecsNights;
	}

	public void setTripFirSecsTransport(String tripFirSecsTransport){
		this.tripFirSecsTransport = tripFirSecsTransport;
	}

	public String getTripFirSecsTransport(){
		return tripFirSecsTransport;
	}

	public void setRelatedPlaces(List<RelatedPlacesItem> relatedPlaces){
		this.relatedPlaces = relatedPlaces;
	}

	public List<RelatedPlacesItem> getRelatedPlaces(){
		return relatedPlaces;
	}

	public void setTripFirSecsDesc(String tripFirSecsDesc){
		this.tripFirSecsDesc = tripFirSecsDesc;
	}

	public String getTripFirSecsDesc(){
		return tripFirSecsDesc;
	}

	public void setFKTripsId(int fKTripsId){
		this.fKTripsId = fKTripsId;
	}

	public int getFKTripsId(){
		return fKTripsId;
	}

	public void setFKCitiesId(int fKCitiesId){
		this.fKCitiesId = fKCitiesId;
	}

	public int getFKCitiesId(){
		return fKCitiesId;
	}

	@Override
 	public String toString(){
		return 
			"ThirdCity{" + 
			"trip_fir_secs_hotel = '" + tripFirSecsHotel + '\'' + 
			",trip_fir_secs_from = '" + tripFirSecsFrom + '\'' + 
			",laravel_through_key = '" + laravelThroughKey + '\'' + 
			",trip_fir_secs_id = '" + tripFirSecsId + '\'' + 
			",trip_fir_secs_to = '" + tripFirSecsTo + '\'' + 
			",trip_fir_secs_type = '" + tripFirSecsType + '\'' + 
			",trip_fir_secs_nights = '" + tripFirSecsNights + '\'' + 
			",trip_fir_secs_transport = '" + tripFirSecsTransport + '\'' + 
			",related_places = '" + relatedPlaces + '\'' + 
			",trip_fir_secs_desc = '" + tripFirSecsDesc + '\'' + 
			",fK_trips_id = '" + fKTripsId + '\'' + 
			",fK_cities_id = '" + fKCitiesId + '\'' + 
			"}";
		}
}