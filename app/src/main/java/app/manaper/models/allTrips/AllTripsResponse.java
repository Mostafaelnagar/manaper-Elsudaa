package app.manaper.models.allTrips;

import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class AllTripsResponse  {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<AllTrips> allTrips;

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

	public void setData(List<AllTrips> allTrips){
		this.allTrips = allTrips;
	}

	public List<AllTrips> getData(){
		return allTrips;
	}

	@Override
 	public String toString(){
		return 
			"AllTripsResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",allTrips = '" + allTrips + '\'' +
			"}";
		}
}