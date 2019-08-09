package app.manaper.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.Bindable;
import app.manaper.R;
import app.manaper.base.BaseViewModel;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.Params;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.common.Common;
import app.manaper.models.addTrip.AddTripRequest;
import app.manaper.models.addTrip.AddTripResponse;
import app.manaper.models.addTrip.addArrival.AddArrivalTripResponse;
import app.manaper.models.addTrip.addArrival.AddArrivalrequest;
import app.manaper.models.allTrips.checkTrip.CheckResponse;
import app.manaper.models.cities.Cities;
import app.manaper.models.cities.CitiesResponse;

public class AddTripsViewModel extends BaseViewModel {
    AddArrivalrequest addArrivalrequest;
    AddTripRequest addTripRequest;
    public List<Cities> citiesList;
    public String city, transType, place, placeDate, placeTime, timeHint, dateHint, placeHint;
    public int withTransport;

    public AddTripsViewModel() {
        addArrivalrequest = new AddArrivalrequest();
        addTripRequest = new AddTripRequest();
        getCities();

    }


    //TODO  date from
    public void onFromclick() {
        getClicksMutableLiveData().setValue(Codes.FROM_FIRST_TRIP);
    }

    public void onToClick() {
        getClicksMutableLiveData().setValue(Codes.To_FIRST_TRIP);
    }


    //get init data
    private void getCities() {
        citiesList = new ArrayList<>();
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                CitiesResponse citiesResponse = (CitiesResponse) response;
                citiesList = citiesResponse.getData();
            }
        }).requestJsonObject(Request.Method.GET, URLS.CITIES, new Object(), CitiesResponse.class);
    }

    public void checkTripStatus() {
        citiesList = new ArrayList<>();
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                CheckResponse checkResponse = (CheckResponse) response;
                if (checkResponse.getCode() == WebServices.SUCCESS) {
                    UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addTripId(checkResponse.getData().getFkTripsId());
                    if (checkResponse.getData().getStep() == 1) {
                        getClicksMutableLiveData().setValue(Codes.ADD_FIRST_CITY);
                    } else if (checkResponse.getData().getStep() == 2) {
                        getClicksMutableLiveData().setValue(Codes.ADD_SECOND_CITY);

                    } else if (checkResponse.getData().getStep() == 3) {
                        getClicksMutableLiveData().setValue(Codes.ADD_Fourth_CITY);
                    }
                }

            }
        }).requestJsonObject(Request.Method.GET, URLS.CHECK_TRIP_STATUS, new Object(), CheckResponse.class);
    }

    //actions
    public void addArrivalTrip() {
        addArrivalrequest.setStep("1");
        if (getAddArrivalrequest().isValid()) {
            addArrivalrequest.setTripArrDepsType("0");
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    AddArrivalTripResponse arrivalTripResponse = (AddArrivalTripResponse) response;
                    if (arrivalTripResponse.getCode() == WebServices.SUCCESS) {
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addTripId(String.valueOf(arrivalTripResponse.getData().getFKTripsId()));
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addLastTripDate(String.valueOf(arrivalTripResponse.getData().getTripArrDepsDate()));
                        getClicksMutableLiveData().setValue(Codes.ADD_FIRST_CITY);
                    } else
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + arrivalTripResponse.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }).requestJsonObject(Request.Method.POST, URLS.ADD_ARRIVAL_TRIP, addArrivalrequest, AddArrivalTripResponse.class);

        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    public void addFirstTrip() {
        if (getAddTripRequest().isFirstValid()) {
//            if (getPlace() == null && getPlaceTime() == null && getPlaceDate() == null &&
//                    getPlace().equals("") && getPlaceTime().equals("") && getPlaceDate().equals("")) {
            addTripRequest.setTripFirSecsType("0");
            if (getWithTransport() != 0) {
                addTripRequest.setPlacesItems(Common.placesItemList);
                addTripRequest.setPlacesDates(Common.placesDates);
                addTripRequest.setPlacesNames(Common.placesNames);
            }
            addTripRequest.setStep("2");


            addTripRequest.setfKTripsId(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getTripId());
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    AddTripResponse arrivalTripResponse = (AddTripResponse) response;
                    if (arrivalTripResponse.getCode() == WebServices.SUCCESS) {
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addLastTripDate(String.valueOf(arrivalTripResponse.getData().getTripFirSecsTo()));
                        getClicksMutableLiveData().setValue(Codes.ADD_SECOND_CITY);


                        Common.placesItemList.clear();
                        Common.placesNames.clear();
                        Common.placesDates.clear();
                    } else {
                        accessLoadingBar(View.GONE);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), arrivalTripResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }
            }).requestJsonObject(Request.Method.POST, URLS.ADD_ARRIVAL_TRIP, addTripRequest, AddTripResponse.class);

        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getResources().getString(R.string.emptyFields), Toast.LENGTH_SHORT).show();

    }

    public void addSecondTrip() {
        if (getAddTripRequest().isFirstValid()) {
//            if (getPlace() == null && getPlaceTime() == null && getPlaceDate() == null) {
            addTripRequest.setTripFirSecsType("1");
            if (getWithTransport() != 0) {
                addTripRequest.setPlacesItems(Common.placesItemList);
                addTripRequest.setPlacesDates(Common.placesDates);
                addTripRequest.setPlacesNames(Common.placesNames);
            }
            addTripRequest.setStep("3");
            addTripRequest.setfKTripsId(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getTripId());
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    AddTripResponse arrivalTripResponse = (AddTripResponse) response;
                    if (arrivalTripResponse.getCode() == WebServices.SUCCESS) {
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addLastTripDate(String.valueOf(arrivalTripResponse.getData().getTripFirSecsTo()));

                        getClicksMutableLiveData().setValue(Codes.ADD_THIRD_CITY);

                    }
                }
            }).requestJsonObject(Request.Method.POST, URLS.ADD_ARRIVAL_TRIP, addTripRequest, AddTripResponse.class);

        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    //outLeaving Leaving
    public void addThirdTrip() {
        addArrivalrequest.setStep("4");
        if (getAddArrivalrequest().isLeavingValid()) {
            addArrivalrequest.setTripArrDepsType("1");
            addArrivalrequest.setFKTripsId(Integer.parseInt(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getTripId()));
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    AddArrivalTripResponse arrivalTripResponse = (AddArrivalTripResponse) response;
                    if (arrivalTripResponse.getCode() == WebServices.SUCCESS) {
                        getClicksMutableLiveData().setValue(Codes.HOME_SCREEN);
                    }


                }
            }).requestJsonObject(Request.Method.POST, URLS.ADD_ARRIVAL_TRIP, addArrivalrequest, AddArrivalTripResponse.class);

        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    //Inside trip
    public void addFourthTrip() {
        addArrivalrequest.setStep("4");
        if (getAddArrivalrequest().isValid()) {
            addArrivalrequest.setTripArrDepsType("1");
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    AddArrivalTripResponse arrivalTripResponse = (AddArrivalTripResponse) response;
                    Log.i("onRequestSuccess", "onRequestSuccess: " + arrivalTripResponse.getCode());

                    if (arrivalTripResponse.getCode() == WebServices.SUCCESS) {
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addTripId(String.valueOf(arrivalTripResponse.getData().getFKTripsId()));
                        getClicksMutableLiveData().setValue(Codes.ADD_Fourth_CITY);
                    } else
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + arrivalTripResponse.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }).requestJsonObject(Request.Method.POST, URLS.ADD_ARRIVAL_TRIP, addArrivalrequest, AddArrivalTripResponse.class);
        } else
            Log.i("addArrivalTrip", "addArrivalTrip: empty");
    }

    public void showCities() {
        getClicksMutableLiveData().setValue(Codes.SELECT_CITIES);
    }

    public void addNewPlace() {
        if (getPlace() != null && getPlaceDate() != null && getPlaceTime() != null &&
                !getPlace().equals("") && !getPlaceDate().equals("") && !getPlaceTime().equals("")) {
            getClicksMutableLiveData().setValue(Codes.ADD_NEW_PLACE);

            notifyChange();
        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getResources().getString(R.string.emptyPlaces), Toast.LENGTH_SHORT).show();
    }

    public void showTransportation() {
        getClicksMutableLiveData().setValue(Codes.SELECT_TRANSPORT_TYPE);

    }


    //Setter and Getter
    @Bindable
    public AddArrivalrequest getAddArrivalrequest() {
        return addArrivalrequest;
    }

    @Bindable
    public AddTripRequest getAddTripRequest() {
        return addTripRequest;
    }

    @Bindable
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
        notifyChange();

    }

    @Bindable
    public String getPlaceDate() {
        return placeDate;
    }

    public void setPlaceDate(String placeDate) {
        this.placeDate = placeDate;
        notifyChange();

    }

    @Bindable
    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
        notifyChange();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }


    @Bindable
    public int getWithTransport() {
        return withTransport != 0 ? View.VISIBLE : View.GONE;
    }

    public void setWithTransport(int withTransport) {
        this.withTransport = withTransport;
    }


    @Bindable
    public String getTimeHint() {
        return MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.attractiveTimeText);
    }

    public void setTimeHint(String timeHint) {
        this.timeHint = timeHint;
    }

    @Bindable

    public String getDateHint() {
        return MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.attractiveDateText);
    }

    public void setDateHint(String dateHint) {
        this.dateHint = dateHint;
    }

    @Bindable

    public String getPlaceHint() {
        return MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.attractivePlacesText);
    }

    public void setPlaceHint(String placeHint) {
        this.placeHint = placeHint;
    }

//    public void firToClick() {
//        new Params().from_step = 1;
//    }
//
//    public void frFromClick() {
//        new Params().from_step = 2;
//    }
}
