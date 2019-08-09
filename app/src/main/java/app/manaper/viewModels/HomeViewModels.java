package app.manaper.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.adapters.HomeAdapter;
import app.manaper.base.BaseViewModel;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.SuccessResponse;
import app.manaper.models.allTrips.AllTrips;
import app.manaper.models.allTrips.AllTripsResponse;

public class HomeViewModels extends BaseViewModel {
    HomeAdapter homeAdapter;
    public int emptyList;
    public List<AllTrips> allTrips;

    public HomeViewModels(int type) {
        allTrips = new ArrayList<>();
        if (type == 1)
            getAllTrips();
        else if (type == 2)
            getLastTrips();

    }


    private void getLastTrips() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                AllTripsResponse tripsResponse = (AllTripsResponse) response;
                if (tripsResponse.getCode() == WebServices.SUCCESS) {
                    setEmptyList(tripsResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getHomeAdapter().updateData(tripsResponse.getData());
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.LAST_TRIP, new Object(), AllTripsResponse.class);
    }

    @BindingAdapter({"app:homeAdapter"})
    public static void getTripsBinding(RecyclerView recyclerView, HomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);

    }


    @Bindable
    public HomeAdapter getHomeAdapter() {
        return this.homeAdapter == null ? this.homeAdapter = new HomeAdapter() : this.homeAdapter;
    }


    public void getAllTrips() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                AllTripsResponse tripsResponse = (AllTripsResponse) response;
                if (tripsResponse.getCode() == WebServices.SUCCESS) {
//                    emptyList = View.GONE;
                    setEmptyList(tripsResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    allTrips = tripsResponse.getData();
                    getHomeAdapter().updateData(tripsResponse.getData());
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.ALL_TRIPS, new Object(), AllTripsResponse.class);
    }

    public void deleteOneTrip(int position) {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                SuccessResponse tripsResponse = (SuccessResponse) response;
                if (tripsResponse.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), tripsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    getAllTrips();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.DELETE_TRIP + allTrips.get(position).getFKTripsId(), new Object(), SuccessResponse.class);
    }

    @Bindable
    public int getEmptyList() {
        return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        notifyChange();
        this.emptyList = emptyList;
    }
}
