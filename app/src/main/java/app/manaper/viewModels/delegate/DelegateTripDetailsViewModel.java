package app.manaper.viewModels.delegate;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.Bindable;
import app.manaper.BR;
import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.common.Common;
import app.manaper.models.delegate.AcceptTripRequest;
import app.manaper.models.delegate.DepartureDetails.DepartureDetails;
import app.manaper.models.delegate.DepartureDetails.DepartureResponse;
import app.manaper.models.delegate.TripDetailsRequest;
import app.manaper.models.delegate.finishtrip.FinishResponse;
import app.manaper.models.delegate.firstDetails.DelegatePlaces;
import app.manaper.models.delegate.firstDetails.Distenation;
import app.manaper.models.delegate.firstDetails.FirstDisDetails;

public class DelegateTripDetailsViewModel extends BaseViewModel {
    DepartureDetails departureDetails;
    Distenation distenation;
    TripDetailsRequest detailsRequest;
    AcceptTripRequest acceptTripRequest;
    String tripId, arTypes, secTypes;
    public String places, date, time;
    public List<DelegatePlaces> placesList;
    public int finishShow, acceptShow, withTransport;

    public DelegateTripDetailsViewModel() {
        departureDetails = new DepartureDetails();
        distenation = new Distenation();
        placesList = new ArrayList<>();
        tripId = UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getTripId();
        arTypes = UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getArrDepsTypes();
        secTypes = UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getSecDepsTypes();
        detailsRequest = new TripDetailsRequest(tripId, arTypes, secTypes);
        if (!tripId.equals("0")) {
            if (secTypes != null)
                getDisTripDetails();
            getDepartureTripDetails();
        }
        Log.i("DelegateTrip", "DelegateTripDetailsViewModel: " + tripId);
    }

    public List<DelegatePlaces> getPlacesList() {
        return placesList;
    }

    public DepartureDetails getDepartureDetails() {
        return departureDetails;
    }

    public Distenation getDistenation() {
        return distenation;
    }

    public TripDetailsRequest getDetailsRequest() {
        return detailsRequest;
    }

    public void getDepartureTripDetails() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                DepartureResponse departureResponse = (DepartureResponse) response;
                if (departureResponse.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    departureDetails = departureResponse.getData();
                    notifyChange();
                } else {
                    Log.i("onRequestSuccess", "onRequestSuccess: " + ((DepartureResponse) response).getMsg());
                }

            }
        }).requestJsonObject(Request.Method.POST, URLS.DELEGATE_TRIPS_ONE, detailsRequest, DepartureResponse.class);
    }

    public void getDisTripDetails() {
        accessLoadingBar(View.VISIBLE);

        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                Log.i("onRequestSuccess", "onRequestSuccess: " + response);
                FirstDisDetails firstDisDetails = (FirstDisDetails) response;
                if (firstDisDetails.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    distenation = firstDisDetails.getDistenation();
                    placesList = distenation.getRelatedPlaces();
                    setWithTransport(distenation.getRelatedPlaces().size());
                    Log.i("onRequestSuccess", "onRequestSuccess: " + placesList);
                    notifyChange();
                } else {

                }
            }
        }).requestJsonObject(Request.Method.POST, URLS.DELEGATE_TRIPS_ONE, detailsRequest, FirstDisDetails.class);
    }

    public void back() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

    public void showPlaces() {
        getClicksMutableLiveData().setValue(Codes.SELECT_CITIES);
    }

    public void accepttrip() {
        if (!tripId.equals("0")) {
            if (secTypes != null) {
                acceptTripRequest = new AcceptTripRequest(null, tripId);
                acceptDist();
            } else {
                acceptTripRequest = new AcceptTripRequest(tripId, null);
                acceptArrival();
            }
        }
    }

    public void acceptDist() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                FirstDisDetails firstDisDetails = (FirstDisDetails) response;
                if (firstDisDetails.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    Log.i("onRequestSuccess", "onRequestSuccess: " + tripId);
                    getClicksMutableLiveData().setValue(Codes.BACK);
                    notifyChange();
                } else {
                    Log.i("onRequestSuccess", "onRequestSuccess: " + ((FirstDisDetails) response).getMsg());
                }
            }
        }).requestJsonObject(Request.Method.POST, URLS.DELEGATE_ACCEPT_ONE, acceptTripRequest, FirstDisDetails.class);

    }

    public void acceptArrival() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                DepartureResponse departureResponse = (DepartureResponse) response;
                if (departureResponse.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    getClicksMutableLiveData().setValue(Codes.BACK);
                    notifyChange();
                } else {
                    Log.i("onRequestSuccess", "onRequestSuccess: " + ((DepartureResponse) response).getMsg());
                }

            }
        }).requestJsonObject(Request.Method.POST, URLS.DELEGATE_ACCEPT_ONE, acceptTripRequest, DepartureResponse.class);

    }

    public void finishTrip() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                FinishResponse departureResponse = (FinishResponse) response;
                if (departureResponse.getCode() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    getClicksMutableLiveData().setValue(Codes.BACK);
                    notifyChange();
                } else {
                    Log.i("onRequestSuccess", "onRequestSuccess: " + ((DepartureResponse) response).getMsg());
                }

            }
        }).requestJsonObject(Request.Method.POST, URLS.DELEGATE_FINISH_REQUEST, detailsRequest, FinishResponse.class);

    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Bindable
    public int getFinishShow() {
        return Common.visiable == 1 ? View.VISIBLE : View.GONE;
    }

    public void setFinishShow(int finishShow) {
        this.finishShow = finishShow;
        notifyPropertyChanged(BR.finishShow);
    }

    @Bindable
    public int getAcceptShow() {
        return Common.visiable == 0 ? View.VISIBLE : View.GONE;
    }

    public void setAcceptShow(int acceptShow) {
        this.acceptShow = acceptShow;
        notifyPropertyChanged(BR.acceptShow);
    }

    @Bindable
    public int getWithTransport() {
        return withTransport != 0 ? View.VISIBLE : View.GONE;
    }

    public void setWithTransport(int withTransport) {
        this.withTransport = withTransport;
    }

}
