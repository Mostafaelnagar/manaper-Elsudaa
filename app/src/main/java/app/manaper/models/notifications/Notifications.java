package app.manaper.models.notifications;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Notifications implements Serializable {

    @SerializedName("notifications_img")
    private Object notificationsImg;

    @SerializedName("notifications_desc")
    private String notificationsDesc;

    @SerializedName("notifications_title")
    private String notificationsTitle;

    @SerializedName("notifications_time")
    private String notificationsTime;

    public void setNotificationsImg(Object notificationsImg) {
        this.notificationsImg = notificationsImg;
    }

    public Object getNotificationsImg() {
        return notificationsImg;
    }

    public void setNotificationsDesc(String notificationsDesc) {
        this.notificationsDesc = notificationsDesc;
    }

    public String getNotificationsDesc() {
        return notificationsDesc;
    }

    public void setNotificationsTitle(String notificationsTitle) {
        this.notificationsTitle = notificationsTitle;
    }

    public String getNotificationsTitle() {
        return notificationsTitle;
    }

    public void setNotificationsTime(String notificationsTime) {
        this.notificationsTime = notificationsTime;
    }

    public String getNotificationsTime() {
        return notificationsTime;
    }

    @Override
    public String toString() {
        return
                "Notifications{" +
                        "notifications_img = '" + notificationsImg + '\'' +
                        ",notifications_desc = '" + notificationsDesc + '\'' +
                        ",notifications_title = '" + notificationsTitle + '\'' +
                        ",notifications_time = '" + notificationsTime + '\'' +
                        "}";
    }
}