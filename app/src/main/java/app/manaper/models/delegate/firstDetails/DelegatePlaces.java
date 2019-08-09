package app.manaper.models.delegate.firstDetails;

 import com.google.gson.annotations.SerializedName;

 public class DelegatePlaces {

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

	@SerializedName("places_updated_at")
	private String placesUpdatedAt;

	@SerializedName("FK_cities_id")
	private int fKCitiesId;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("places_id")
	private int placesId;

	@SerializedName("places_created_at")
	private String placesCreatedAt;

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

	public void setPlacesUpdatedAt(String placesUpdatedAt){
		this.placesUpdatedAt = placesUpdatedAt;
	}

	public String getPlacesUpdatedAt(){
		return placesUpdatedAt;
	}

	public void setFKCitiesId(int fKCitiesId){
		this.fKCitiesId = fKCitiesId;
	}

	public int getFKCitiesId(){
		return fKCitiesId;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setPlacesId(int placesId){
		this.placesId = placesId;
	}

	public int getPlacesId(){
		return placesId;
	}

	public void setPlacesCreatedAt(String placesCreatedAt){
		this.placesCreatedAt = placesCreatedAt;
	}

	public String getPlacesCreatedAt(){
		return placesCreatedAt;
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
			",places_updated_at = '" + placesUpdatedAt + '\'' + 
			",fK_cities_id = '" + fKCitiesId + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",places_id = '" + placesId + '\'' + 
			",places_created_at = '" + placesCreatedAt + '\'' + 
			"}";
		}
}