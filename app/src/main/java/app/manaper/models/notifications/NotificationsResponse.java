package app.manaper.models.notifications;

import java.io.Serializable;
import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class NotificationsResponse implements Serializable {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<Notifications> data;

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

	public void setData(List<Notifications> data){
		this.data = data;
	}

	public List<Notifications> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"NotificationsResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}