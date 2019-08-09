package app.manaper.models.allTrips;

 import com.google.gson.annotations.SerializedName;

 public class AllTrips    {

	@SerializedName("laravel_through_key")
	private int laravelThroughKey;

	@SerializedName("trip_arr_deps_airlines")
	private String tripArrDepsAirlines;

	@SerializedName("city")
	private String city;

	@SerializedName("trip_arr_deps_phonenumber")
	private String tripArrDepsPhonenumber;

	@SerializedName("FK_trips_id")
	private String fKTripsId;

	@SerializedName("FK_cities_id")
	private int fKCitiesId;

	@SerializedName("trip_arr_deps_date")
	private String tripArrDepsDate;

	public void setLaravelThroughKey(int laravelThroughKey){
		this.laravelThroughKey = laravelThroughKey;
	}

	public int getLaravelThroughKey(){
		return laravelThroughKey;
	}

	public void setTripArrDepsAirlines(String tripArrDepsAirlines){
		this.tripArrDepsAirlines = tripArrDepsAirlines;
	}

	public String getTripArrDepsAirlines(){
		return tripArrDepsAirlines;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setTripArrDepsPhonenumber(String tripArrDepsPhonenumber){
		this.tripArrDepsPhonenumber = tripArrDepsPhonenumber;
	}

	public String getTripArrDepsPhonenumber(){
		return tripArrDepsPhonenumber;
	}

	public void setFKTripsId(String fKTripsId){
		this.fKTripsId = fKTripsId;
	}

	public String getFKTripsId(){
		return fKTripsId;
	}

	public void setFKCitiesId(int fKCitiesId){
		this.fKCitiesId = fKCitiesId;
	}

	public int getFKCitiesId(){
		return fKCitiesId;
	}

	public void setTripArrDepsDate(String tripArrDepsDate){
		this.tripArrDepsDate = tripArrDepsDate;
	}

	public String getTripArrDepsDate(){
		return tripArrDepsDate;
	}

	@Override
 	public String toString(){
		return 
			"AllTrips{" +
			"laravel_through_key = '" + laravelThroughKey + '\'' + 
			",trip_arr_deps_airlines = '" + tripArrDepsAirlines + '\'' + 
			",city = '" + city + '\'' + 
			",trip_arr_deps_phonenumber = '" + tripArrDepsPhonenumber + '\'' + 
			",fK_trips_id = '" + fKTripsId + '\'' + 
			",fK_cities_id = '" + fKCitiesId + '\'' + 
			",trip_arr_deps_date = '" + tripArrDepsDate + '\'' + 
			"}";
		}
}