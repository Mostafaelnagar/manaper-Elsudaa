package app.manaper.models.delegate;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DelegateTripResponse {

    @SerializedName("msg")
    private String msg;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private List<DelTripData> data;

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

    public void setData(List<DelTripData> data) {
        this.data = data;
    }

    public List<DelTripData> getData() {
        return data;
    }

    @Override
    public String toString() {
        return
                "DelegateTripResponse{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",data = '" + data + '\'' +
                        "}";
    }
}