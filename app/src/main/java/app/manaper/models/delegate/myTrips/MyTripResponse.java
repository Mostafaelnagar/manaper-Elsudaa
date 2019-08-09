package app.manaper.models.delegate.myTrips;

import java.io.Serializable;
import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class MyTripResponse implements Serializable {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<MyTripData> data;

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

	public void setData(List<MyTripData> data){
		this.data = data;
	}

	public List<MyTripData> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"MyTripResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}