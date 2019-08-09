package app.manaper.customViews;

import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.nostra13.universalimageloader.core.ImageLoader;

import app.manaper.R;
import app.manaper.base.BaseViewModel;

public class NavItemViewModel extends BaseViewModel {
    public String iconName;
    public String IconImage;
    NavItems navItems;
    public MutableLiveData<NavItems> itemsOperationsLiveListenerl;

    public NavItemViewModel(NavItems navItems) {
        this.navItems = navItems;
        itemsOperationsLiveListenerl = new MutableLiveData<>();
     }

    @BindingAdapter({"iconImage"})
    public static void loadImage(ImageView view, int iconImage) {
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        // Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
        //	which implements ImageAware interface)
        imageLoader.displayImage("drawable://" + iconImage, view);

    }

    public void performClickAction() {
        notifyChange();
        itemsOperationsLiveListenerl.setValue(navItems);
     }

    @Bindable
    public String getIconName() {
        return navItems.getNavName();
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public int getIconImage() {
        return navItems.getImgId();
    }

    public void setIconImage(String iconImage) {
        IconImage = iconImage;
    }

    public MutableLiveData<NavItems> getItemsOperationsLiveListenerl() {
        return itemsOperationsLiveListenerl;
    }



}
