package app.manaper.models.addTrip.addArrival;

import com.google.gson.annotations.SerializedName;

public class AddArrivalTripResponse {

    @SerializedName("msg")
    private String msg;

    @SerializedName("code")
    private int code;

    @SerializedName("data")

    private AddArrivalrequest arrivalrequest;

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

    public void setData(AddArrivalrequest data) {
        this.arrivalrequest = data;
    }

    public AddArrivalrequest getData() {
        return arrivalrequest;
    }

    @Override
    public String toString() {
        return
                "AddArrivalTripResponse{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",arrivalrequest = '" + arrivalrequest + '\'' +
                        "}";
    }
}