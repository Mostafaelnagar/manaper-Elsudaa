package app.manaper.customViews;

 import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

 import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.MyApplication;

public class NavViewModel extends BaseViewModel {
    public List<NavItems> navItems;
    public NavAdapter navAdapter;
    public String userImage, userName, userEmail;

    public NavViewModel() {
        navItems = new ArrayList<>();
        navAdapter = new NavAdapter();
        setUserData();
    }

    public void setUserData() {
        if (UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData() != null) {
            userImage = UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getUsersImg();
            userName = UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getUsersName();
            userEmail = UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getEmail();
             notifyChange();
        }
    }

    @BindingAdapter({"app:navAdapter", "app:navItems"})
    public static void bind(RecyclerView recyclerView, NavAdapter navAdapter, List<NavItems> navItems) {
        recyclerView.setAdapter(navAdapter);
        navAdapter.updateData(navItems);
    }

    @BindingAdapter({"imageUrl"})
    public static void setUserImage(ImageView view, String imagePath) {
        ConnectionHelper.loadImage(view, imagePath);
    }

    public void toProfile() {
        getClicksMutableLiveData().setValue(Codes.PROFILE);
    }

    public void grandInfo() {
        getClicksMutableLiveData().setValue(Codes.GRAND_INFO);
    }

    @Bindable
    public String getUserImage() {
        return userImage;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Bindable
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Bindable

    public List<NavItems> getNavItems() {
        return navItems;
    }

    public void setNavItems(List<NavItems> navItems) {
        this.navItems = navItems;
        notifyChange();
    }

    @Bindable

    public NavAdapter getNavAdapter() {
        return navAdapter;
    }

    public void setNavAdapter(NavAdapter navAdapter) {
        this.navAdapter = navAdapter;
    }

}
