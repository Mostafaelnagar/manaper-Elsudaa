package app.manaper.models.cities;

import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class CitiesResponse  {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<Cities> data;

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

	public void setData(List<Cities> data){
		this.data = data;
	}

	public List<Cities> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"CitiesResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}