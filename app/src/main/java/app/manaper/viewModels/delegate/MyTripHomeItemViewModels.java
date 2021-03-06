package app.manaper.viewModels.delegate;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import app.manaper.models.delegate.DelTripData;
import app.manaper.models.delegate.myTrips.MyTripData;

public class MyTripHomeItemViewModels extends BaseObservable {
    private DelTripData allTrips;
    private MutableLiveData<DelTripData> itemsOperationsLiveListener;
    public String place, date;

    public MyTripHomeItemViewModels(DelTripData allTrips, int position) {
        this.allTrips = allTrips;
        this.itemsOperationsLiveListener = new MutableLiveData<>();

    }


    public MutableLiveData<DelTripData> getItemsOperationsLiveListener() {
        return itemsOperationsLiveListener;
    }


    @Bindable
    public DelTripData getAllTrips() {
        return allTrips;
    }

    public void itemAction() {
        notifyChange();
        itemsOperationsLiveListener.setValue(allTrips);
    }

    public String getPlace() {

        if (allTrips.getTripArrDepsAirlines() != null)
            place = allTrips.getTripArrDepsAirlines();
        else
            place = allTrips.getTripFirSecsHotel();
        return place;
    }

    public void setPlace(String place) {

        this.place = place;
    }

    public String getDate() {
        if (allTrips.getTripArrDepsDate() != null)
            date = allTrips.getTripArrDepsDate();
        else
            date = allTrips.getTripFirSecsFrom();
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
