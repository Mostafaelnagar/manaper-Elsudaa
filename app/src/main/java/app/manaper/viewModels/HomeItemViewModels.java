package app.manaper.viewModels;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import app.manaper.models.allTrips.AllTrips;

public class HomeItemViewModels extends BaseObservable {
    private AllTrips allTrips;
    private MutableLiveData<AllTrips> itemsOperationsLiveListener;

    public HomeItemViewModels(AllTrips allTrips, int position) {
        this.allTrips = allTrips;
        this.itemsOperationsLiveListener = new MutableLiveData<>();
    }


    public MutableLiveData<AllTrips> getItemsOperationsLiveListener() {
        return itemsOperationsLiveListener;
    }


    @Bindable
    public AllTrips getAllTrips() {
        return allTrips;
    }

    public void itemAction() {
        notifyChange();
        itemsOperationsLiveListener.setValue(allTrips);
    }
}
