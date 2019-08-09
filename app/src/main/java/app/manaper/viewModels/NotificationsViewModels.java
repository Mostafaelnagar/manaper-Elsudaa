package app.manaper.viewModels;

import android.view.View;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.adapters.HomeAdapter;
import app.manaper.adapters.NotificationsAdapter;
import app.manaper.base.BaseViewModel;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.allTrips.AllTripsResponse;
import app.manaper.models.notifications.NotificationsResponse;

public class NotificationsViewModels extends BaseViewModel {
    NotificationsAdapter notificationsAdapter;
    public int emptyList;

    public NotificationsViewModels() {
        getNotifications();
    }

    private void getNotifications() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                NotificationsResponse notificationsResponse = (NotificationsResponse) response;
                if (notificationsResponse.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    setEmptyList(notificationsResponse.getData().size());
                    getNotificationsAdapter().updateData(notificationsResponse.getData());
                } else
                    accessLoadingBar(View.GONE);
            }
        }).requestJsonObject(Request.Method.GET, URLS.NOTIFICATIONS, new Object(), NotificationsResponse.class);
    }


    @BindingAdapter({"app:notificationsAdapter"})
    public static void getNotificationsBinding(RecyclerView recyclerView, NotificationsAdapter notificationsAdapter) {
        recyclerView.setAdapter(notificationsAdapter);
    }


    @Bindable
    public NotificationsAdapter getNotificationsAdapter() {
        return this.notificationsAdapter == null ? this.notificationsAdapter = new NotificationsAdapter() : this.notificationsAdapter;
    }


    public int getEmptyList() {
        return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        notifyChange();
        this.emptyList = emptyList;
    }
}
