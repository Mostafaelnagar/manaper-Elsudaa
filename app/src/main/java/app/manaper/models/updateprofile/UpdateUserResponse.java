package app.manaper.models.updateprofile;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UpdateUserResponse implements Serializable{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private UpdateProfileItem data;

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

	public void setData(UpdateProfileItem data){
		this.data = data;
	}

	public UpdateProfileItem getData(){
		return data;
	}
}