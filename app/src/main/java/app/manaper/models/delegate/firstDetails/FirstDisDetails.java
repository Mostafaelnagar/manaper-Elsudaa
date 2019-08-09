package app.manaper.models.delegate.firstDetails;

 import com.google.gson.annotations.SerializedName;

 public class FirstDisDetails {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Distenation distenation;

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

	public void setData(Distenation distenation){
		this.distenation = distenation;
	}

	public Distenation getDistenation(){
		return distenation;
	}

	@Override
 	public String toString(){
		return 
			"FirstDisDetails{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",distenation = '" + distenation + '\'' +
			"}";
		}
}