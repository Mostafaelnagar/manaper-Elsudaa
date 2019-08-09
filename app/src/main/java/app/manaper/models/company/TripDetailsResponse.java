package app.manaper.models.company;

 import com.google.gson.annotations.SerializedName;


public class TripDetailsResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private tripSections tripSections;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setTripSections(tripSections tripSections){
		this.tripSections = tripSections;
	}

	public tripSections getTripSections(){
		return tripSections;
	}

	@Override
 	public String toString(){
		return 
			"TripDetailsResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",tripSections = '" + tripSections + '\'' +
			"}";
		}
}