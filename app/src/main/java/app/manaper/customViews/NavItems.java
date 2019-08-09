package app.manaper.customViews;

import android.graphics.drawable.Drawable;

public class NavItems {
    String navName;
    int id;
    int imgId;

    public NavItems() {
    }

    public NavItems(String navName, int id, int imgId) {
        this.navName = navName;
        this.id = id;
        this.imgId = imgId;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
