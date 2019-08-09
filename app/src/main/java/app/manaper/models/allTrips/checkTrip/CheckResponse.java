package app.manaper.models.allTrips.checkTrip;

 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckResponse implements Serializable {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private CheckData data;

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

	public void setData(CheckData data){
		this.data = data;
	}

	public CheckData getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"CheckResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}