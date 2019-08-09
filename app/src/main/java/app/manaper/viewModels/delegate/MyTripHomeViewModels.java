package app.manaper.viewModels.delegate;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.adapters.MyTripHomeAdapter;
import app.manaper.adapters.UrgentHomeAdapter;
import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.allTrips.AllTripsResponse;
import app.manaper.models.delegate.DelegateTripResponse;
import app.manaper.models.delegate.myTrips.MyTripResponse;

public class MyTripHomeViewModels extends BaseViewModel {
    public MyTripHomeAdapter homeAdapter;
    public int emptyList;

    public MyTripHomeViewModels() {

    }

    public void getMyTrips() {
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
                    setEmptyList(0);
                    accessLoadingBar(View.GONE);
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.DELEGATE_MY_TRIPS, new Object(), DelegateTripResponse.class);

    }

    public void getDelAllTrips() {
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
        }).requestJsonObject(Request.Method.GET, URLS.DELEGATE_ALL_TRIPS + UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getFKCitiesId(), new Object(), DelegateTripResponse.class);

    }


    @BindingAdapter({"app:homeAdapter"})
    public static void getTripsBinding(RecyclerView recyclerView, MyTripHomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);
    }


    @Bindable
    public MyTripHomeAdapter getHomeAdapter() {
        return this.homeAdapter == null ? this.homeAdapter = new MyTripHomeAdapter() : this.homeAdapter;
    }


    public int getEmptyList() {
        return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        notifyChange();
        this.emptyList = emptyList;
    }
}
