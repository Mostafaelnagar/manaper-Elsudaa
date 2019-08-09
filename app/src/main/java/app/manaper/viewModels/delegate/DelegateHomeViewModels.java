package app.manaper.viewModels.delegate;

import android.util.Log;
import android.view.View;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.adapters.DelHomeAdapter;
import app.manaper.adapters.HomeAdapter;
import app.manaper.base.BaseViewModel;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.allTrips.AllTripsResponse;
import app.manaper.models.delegate.DelegateTripResponse;

public class DelegateHomeViewModels extends BaseViewModel {
    DelHomeAdapter homeAdapter;
    public int emptyList;

    public DelegateHomeViewModels() {
        getDelegateTrips();
    }

    public void getDelegateTrips() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                DelegateTripResponse tripsResponse = (DelegateTripResponse) response;
                Log.i("onRequestSuccess", "onRequestSuccess: " + tripsResponse);
                if (tripsResponse.getCode() == 200) {
                    setEmptyList(tripsResponse.getData().size());
                    getHomeAdapter().updateData(tripsResponse.getData());
                    accessLoadingBar(View.GONE);
                } else
                    accessLoadingBar(View.GONE);

            }
        }).requestJsonObject(Request.Method.GET, URLS.DELEGATE_TRIPS, new Object(), DelegateTripResponse.class);
    }


    @BindingAdapter({"app:homeAdapter"})
    public static void getTripsBinding(RecyclerView recyclerView, DelHomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);
    }


    @Bindable
    public DelHomeAdapter getHomeAdapter() {
        return this.homeAdapter == null ? this.homeAdapter = new DelHomeAdapter() : this.homeAdapter;
    }


    public int getEmptyList() {
         return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        notifyChange();
        this.emptyList = emptyList;
     }
}
