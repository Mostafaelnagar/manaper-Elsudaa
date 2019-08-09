package app.manaper.models.company;

 import com.google.gson.annotations.SerializedName;

 public class RelatedPlacesItem  {

	@SerializedName("places_time")
	private String placesTime;

	@SerializedName("FK_trip_fir_secs_id")
	private int fKTripFirSecsId;

	@SerializedName("FK_users_id")
	private int fKUsersId;

	@SerializedName("places_name")
	private String placesName;

	@SerializedName("places_date")
	private String placesDate;

	@SerializedName("places_id")
	private int placesId;

	 public RelatedPlacesItem(String placesTime, String placesName, String placesDate) {
		 this.placesTime = placesTime;
		 this.placesName = placesName;
		 this.placesDate = placesDate;
	 }

	 public void setPlacesTime(String placesTime){
		this.placesTime = placesTime;
	}

	public String getPlacesTime(){
		return placesTime;
	}

	public void setFKTripFirSecsId(int fKTripFirSecsId){
		this.fKTripFirSecsId = fKTripFirSecsId;
	}

	public int getFKTripFirSecsId(){
		return fKTripFirSecsId;
	}

	public void setFKUsersId(int fKUsersId){
		this.fKUsersId = fKUsersId;
	}

	public int getFKUsersId(){
		return fKUsersId;
	}

	public void setPlacesName(String placesName){
		this.placesName = placesName;
	}

	public String getPlacesName(){
		return placesName;
	}

	public void setPlacesDate(String placesDate){
		this.placesDate = placesDate;
	}

	public String getPlacesDate(){
		return placesDate;
	}

	public void setPlacesId(int placesId){
		this.placesId = placesId;
	}

	public int getPlacesId(){
		return placesId;
	}

	@Override
 	public String toString(){
		return 
			"RelatedPlacesItem{" + 
			"places_time = '" + placesTime + '\'' + 
			",fK_trip_fir_secs_id = '" + fKTripFirSecsId + '\'' + 
			",fK_users_id = '" + fKUsersId + '\'' + 
			",places_name = '" + placesName + '\'' + 
			",places_date = '" + placesDate + '\'' + 
			",places_id = '" + placesId + '\'' + 
			"}";
		}
}