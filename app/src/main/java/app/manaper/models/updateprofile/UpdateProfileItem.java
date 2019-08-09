package app.manaper.models.updateprofile;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateProfileItem implements Serializable{

	@SerializedName("users_img")
	private String usersImg;

	@SerializedName("users_phonenumber")
	private String usersPhonenumber;

	@SerializedName("users_name")
	private String usersName;

	@SerializedName("city")
	private String city;

	@SerializedName("jwt")
	private String jwt;

	@SerializedName("users_id")
	private int usersId;

	@SerializedName("users_type")
	private int usersType;

	@SerializedName("firebase_token")
	private Object firebaseToken;

	@SerializedName("FK_cities_id")
	private int fKCitiesId;

	@SerializedName("users_updated_at")
	private String usersUpdatedAt;

	@SerializedName("email")
	private String email;

	public void setUsersImg(String usersImg){
		this.usersImg = usersImg;
	}

	public String getUsersImg(){
		return usersImg;
	}

	public void setUsersPhonenumber(String usersPhonenumber){
		this.usersPhonenumber = usersPhonenumber;
	}

	public String getUsersPhonenumber(){
		return usersPhonenumber;
	}

	public void setUsersName(String usersName){
		this.usersName = usersName;
	}

	public String getUsersName(){
		return usersName;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setJwt(String jwt){
		this.jwt = jwt;
	}

	public String getJwt(){
		return jwt;
	}

	public void setUsersId(int usersId){
		this.usersId = usersId;
	}

	public int getUsersId(){
		return usersId;
	}

	public void setUsersType(int usersType){
		this.usersType = usersType;
	}

	public int getUsersType(){
		return usersType;
	}

	public void setFirebaseToken(Object firebaseToken){
		this.firebaseToken = firebaseToken;
	}

	public Object getFirebaseToken(){
		return firebaseToken;
	}

	public void setFKCitiesId(int fKCitiesId){
		this.fKCitiesId = fKCitiesId;
	}

	public int getFKCitiesId(){
		return fKCitiesId;
	}

	public void setUsersUpdatedAt(String usersUpdatedAt){
		this.usersUpdatedAt = usersUpdatedAt;
	}

	public String getUsersUpdatedAt(){
		return usersUpdatedAt;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}