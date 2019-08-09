package app.manaper.viewModels;

import android.view.View;

import com.android.volley.Request;


import androidx.databinding.Bindable;
import app.manaper.BR;
import app.manaper.R;
import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.company.Arrival;
import app.manaper.models.company.Departure;
import app.manaper.models.company.FirstCity;
import app.manaper.models.company.SecondCity;
import app.manaper.models.company.ThirdCity;
import app.manaper.models.company.TripDetailsResponse;

public class TripDetailsViewModels extends BaseViewModel {
    public Arrival arrivalRequest;
    public FirstCity firstCityRequest;
    public SecondCity secondCityRequest;
    public ThirdCity thirdCityRequest;
    public Departure departureRequest;
    public String places, date, time, attractiveTimeHint, attractiveDateHint, showPlacesHint;

    public TripDetailsViewModels() {
        getTripDetails();
        arrivalRequest = new Arrival();
        firstCityRequest = new FirstCity();
        secondCityRequest = new SecondCity();
        thirdCityRequest = new ThirdCity();
        departureRequest = new Departure();

    }

    @Bindable
    public Arrival getArrivalRequest() {
        return arrivalRequest;
    }

    @Bindable

    public FirstCity getFirstCityRequest() {
        return firstCityRequest;
    }

    @Bindable

    public SecondCity getSecondCityRequest() {
        return secondCityRequest;
    }

    @Bindable

    public ThirdCity getThirdCityRequest() {
        return thirdCityRequest;
    }

    @Bindable

    public Departure getDepartureRequest() {
        return departureRequest;
    }

    public void setArrivalRequest(Arrival arrivalRequest) {
        this.arrivalRequest = arrivalRequest;
        notifyPropertyChanged(BR.arrivalRequest);
    }

    public void setFirstCityRequest(FirstCity firstCityRequest) {

        this.firstCityRequest = firstCityRequest;
        notifyPropertyChanged(BR.firstCityRequest);
    }

    public void setSecondCityRequest(SecondCity secondCityRequest) {
        this.secondCityRequest = secondCityRequest;
        notifyPropertyChanged(BR.secondCityRequest);
    }

    public void setThirdCityRequest(ThirdCity thirdCityRequest) {
        this.thirdCityRequest = thirdCityRequest;
        notifyPropertyChanged(BR.thirdCityRequest);
    }

    public void setDepartureRequest(Departure departureRequest) {
        this.departureRequest = departureRequest;
        notifyPropertyChanged(BR.departureRequest);
    }

    public void getTripDetails() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                TripDetailsResponse tripDetailsResponse = (TripDetailsResponse) response;
                setArrivalRequest(tripDetailsResponse.getTripSections().getArrival());
                setFirstCityRequest(tripDetailsResponse.getTripSections().getFirstCity());
                setSecondCityRequest(tripDetailsResponse.getTripSections().getSecondCity());
                setThirdCityRequest(tripDetailsResponse.getTripSections().getThirdCity());
                setDepartureRequest(tripDetailsResponse.getTripSections().getDeparture());
                accessLoadingBar(View.GONE);
            }
        }).requestJsonObject(Request.Method.GET, URLS.TRIPS_ONE_DETAILS + UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getTripId(), new Object(), TripDetailsResponse.class);
    }

    public void showPlaces() {
        getClicksMutableLiveData().setValue(Codes.SELECT_CITIES);
    }

    public void getPdf() {
        getClicksMutableLiveData().setValue(Codes.DOWNLOAD_FILE);

    }

    public void back() {
        getClicksMutableLiveData().setValue(Codes.BACK);
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
    public String getAttractiveTimeHint() {
        return MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.attractiveTimeText);
    }

    public void setAttractiveTimeHint(String attractiveTimeHint) {
        this.attractiveTimeHint = attractiveTimeHint;
        notifyPropertyChanged(BR.attractiveTimeHint);
    }

    @Bindable
    public String getAttractiveDateHint() {
        return MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.attractiveDateText);
    }

    public void setAttractiveDateHint(String attractiveDateHint) {
        this.attractiveDateHint = attractiveDateHint;
        notifyPropertyChanged(BR.attractiveDateHint);
    }

    @Bindable
    public String getShowPlacesHint() {
        return MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.showPlacesHint);
    }

    public void setShowPlacesHint(String showPlacesHint) {
        this.showPlacesHint = showPlacesHint;
        notifyPropertyChanged(BR.showPlacesHint);
    }
}
