package app.manaper.viewModels.delegate;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.adapters.DelHomeAdapter;
import app.manaper.adapters.UrgentHomeAdapter;
import app.manaper.base.BaseViewModel;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.common.Common;
import app.manaper.models.delegate.DelegateTripResponse;

public class UrgentHomeViewModels extends BaseViewModel {
    public UrgentHomeAdapter homeAdapter;
    public int emptyList;

    public UrgentHomeViewModels() {

    }

    public void getTodayTrips() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                DelegateTripResponse tripsResponse = (DelegateTripResponse) response;
                if (tripsResponse.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    setEmptyList(tripsResponse.getData().size());
                    getHomeAdapter().updateData(tripsResponse.getData());
                } else {
                    accessLoadingBar(View.GONE);
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.DELEGATE_TODAY_REQUEST, new Object(), DelegateTripResponse.class);
    }

    public void getTomorrowTrips() {
        accessLoadingBar(View.VISIBLE);

        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                DelegateTripResponse tripsResponse = (DelegateTripResponse) response;
                if (tripsResponse.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    setEmptyList(tripsResponse.getData().size());
                    getHomeAdapter().updateData(tripsResponse.getData());
                } else {
                    accessLoadingBar(View.GONE);
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.DELEGATE_TOMORROW_REQUEST, new Object(), DelegateTripResponse.class);
    }


    @BindingAdapter({"app:homeAdapter"})
    public static void getTripsBinding(RecyclerView recyclerView, UrgentHomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);
    }


    @Bindable
    public UrgentHomeAdapter getHomeAdapter() {
        return this.homeAdapter == null ? this.homeAdapter = new UrgentHomeAdapter() : this.homeAdapter;
    }


    public int getEmptyList() {
        return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        notifyChange();
        this.emptyList = emptyList;
    }
}
