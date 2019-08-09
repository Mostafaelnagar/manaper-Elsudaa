package app.manaper.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessResponse {
    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose

    @SerializedName("code")
    private int code;
    @Expose

    @SerializedName("status")
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "SuccessResponse{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        "}";
    }
}