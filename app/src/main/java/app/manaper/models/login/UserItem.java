package app.manaper.models.login;

import com.google.gson.annotations.SerializedName;

public class UserItem {

    @SerializedName("users_img")
    private String usersImg;

    @SerializedName("users_created_at")
    private String usersCreatedAt;

    @SerializedName("verified_status")
    private int verifiedStatus;

    @SerializedName("users_phonenumber")
    private String usersPhonenumber;

    @SerializedName("city")
    private String city;

    @SerializedName("jwt")
    private String jwt;

    @SerializedName("users_type")
    private int usersType;

    @SerializedName("firebase_token")
    private Object firebaseToken;

    @SerializedName("users_updated_at")
    private String usersUpdatedAt;

    @SerializedName("deleted_at")
    private Object deletedAt;

    @SerializedName("active_status")
    private int activeStatus;

    @SerializedName("users_name")
    private String usersName;

    @SerializedName("users_id")
    private int usersId;

    @SerializedName("FK_cities_id")
    private int fKCitiesId;

    @SerializedName("email")
    private String email;

    public void setUsersImg(String usersImg) {
        this.usersImg = usersImg;
    }

    public String getUsersImg() {
        return usersImg;
    }

    public void setUsersCreatedAt(String usersCreatedAt) {
        this.usersCreatedAt = usersCreatedAt;
    }

    public String getUsersCreatedAt() {
        return usersCreatedAt;
    }

    public void setVerifiedStatus(int verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public int getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setUsersPhonenumber(String usersPhonenumber) {
        this.usersPhonenumber = usersPhonenumber;
    }

    public String getUsersPhonenumber() {
        return usersPhonenumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setUsersType(int usersType) {
        this.usersType = usersType;
    }

    public int getUsersType() {
        return usersType;
    }

    public void setFirebaseToken(Object firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public Object getFirebaseToken() {
        return firebaseToken;
    }

    public void setUsersUpdatedAt(String usersUpdatedAt) {
        this.usersUpdatedAt = usersUpdatedAt;
    }

    public String getUsersUpdatedAt() {
        return usersUpdatedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setFKCitiesId(int fKCitiesId) {
        this.fKCitiesId = fKCitiesId;
    }

    public int getFKCitiesId() {
        return fKCitiesId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return
                "UserItem{" +
                        "users_img = '" + usersImg + '\'' +
                        ",users_created_at = '" + usersCreatedAt + '\'' +
                        ",verified_status = '" + verifiedStatus + '\'' +
                        ",users_phonenumber = '" + usersPhonenumber + '\'' +
                        ",city = '" + city + '\'' +
                        ",jwt = '" + jwt + '\'' +
                        ",users_type = '" + usersType + '\'' +
                        ",firebase_token = '" + firebaseToken + '\'' +
                        ",users_updated_at = '" + usersUpdatedAt + '\'' +
                        ",deleted_at = '" + deletedAt + '\'' +
                        ",active_status = '" + activeStatus + '\'' +
                        ",users_name = '" + usersName + '\'' +
                        ",users_id = '" + usersId + '\'' +
                        ",fK_cities_id = '" + fKCitiesId + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }


}