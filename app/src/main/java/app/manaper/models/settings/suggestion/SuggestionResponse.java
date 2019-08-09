package app.manaper.models.settings.suggestion;

 import com.google.gson.annotations.SerializedName;

 public class SuggestionResponse {

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private SuggestionRequest data;

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

	public void setData(SuggestionRequest data){
		this.data = data;
	}

	public SuggestionRequest getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"SuggestionResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}