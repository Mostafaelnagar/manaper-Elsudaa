package app.manaper.models.delegate.DepartureDetails;

 import com.google.gson.annotations.SerializedName;

 public class DepartureResponse  {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private DepartureDetails data;

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

	public void setData(DepartureDetails data){
		this.data = data;
	}

	public DepartureDetails getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"DepartureResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}