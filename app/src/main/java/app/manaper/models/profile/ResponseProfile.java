package app.manaper.models.profile;

import com.google.gson.annotations.SerializedName;

import app.manaper.models.login.UserItem;

public class ResponseProfile {

    @SerializedName("msg")
    private String msg;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private ProfileData profileData;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public ProfileData getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileData profileData) {
        this.profileData = profileData;
    }

    @Override
    public String toString() {
        return
                "ResponseProfile{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",profileData = '" + profileData + '\'' +
                        "}";
    }
}