package app.manaper.viewModels;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.models.allTrips.AllTrips;
import app.manaper.models.notifications.Notifications;

public class NotificationsItemViewModels extends BaseObservable {
    private Notifications notifications;
    private MutableLiveData<Notifications> itemsOperationsLiveListener;
    public String notificationsImageUrl;

    public NotificationsItemViewModels(Notifications notifications) {
        this.notifications = notifications;
        this.itemsOperationsLiveListener = new MutableLiveData<>();
    }


    public MutableLiveData<Notifications> getItemsOperationsLiveListener() {
        return itemsOperationsLiveListener;
    }

    @BindingAdapter({"notificationsImageUrl"})
    public static void setNotificationsImageUrl(ImageView view, String imagePath) {
        ConnectionHelper.loadImage(view, imagePath);
    }

    @Bindable
    public Notifications getNotifications() {
        return notifications;
    }

    public void itemAction() {
        notifyChange();
        itemsOperationsLiveListener.setValue(notifications);
    }

    @Bindable
    public String getNotificationsImageUrl() {
        return notificationsImageUrl;
    }


}
