package app.manaper.models.login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private UserItem userItem;

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

	public void setUserItem(UserItem userItem){
		this.userItem = userItem;
	}

	public UserItem getUserItem(){
		return userItem;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",userItem = '" + userItem + '\'' +
			"}";
		}
}