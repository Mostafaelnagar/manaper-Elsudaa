package app.manaper.models.delegate.finishtrip;

 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FinishResponse implements Serializable {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private FinishData data;

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

	public void setData(FinishData data){
		this.data = data;
	}

	public FinishData getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"FinishResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}