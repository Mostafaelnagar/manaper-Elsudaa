package app.manaper.models.addTrip;

 import com.google.gson.annotations.SerializedName;

 public class AddTripResponse    {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private AddTripRequest data;

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

	public void setData(AddTripRequest data){
		this.data = data;
	}

	public AddTripRequest getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"AddTripResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}