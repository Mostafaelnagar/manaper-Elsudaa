package app.manaper.view.company.addTrip;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.PopUpMenus;
import app.manaper.base.constantsutils.Codes;
import app.manaper.databinding.FragmentAddArrivalBinding;
import app.manaper.models.cities.Cities;
import app.manaper.view.mandop.MandopDepartureOutFragment;
import app.manaper.viewModels.AddTripsViewModel;


public class AddArrivalFragment extends BaseFragment {
    FragmentAddArrivalBinding addArrivalBinding;
    AddTripsViewModel tripsViewModel;
    List<Cities> popUpItems;

    public AddArrivalFragment() {
        // Required empty public constructor
        popUpItems = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addArrivalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_arrival, container, false);
        tripsViewModel = new AddTripsViewModel();
        addArrivalBinding.setAddTripsViewModel(tripsViewModel);
        tripsViewModel.checkTripStatus();
        liveDataListeners();

        return addArrivalBinding.getRoot();

    }

    private void liveDataListeners() {
        tripsViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.ADD_FIRST_CITY) {
                Bundle bundle = new Bundle();
                bundle.putString("Arrive_Date", addArrivalBinding.arriveDate.getText().toString());
                AddFirstCountryFragment addFirstCountryFragment = new AddFirstCountryFragment();
                addFirstCountryFragment.setArguments(bundle);
                MovementManager.replaceHomeFragment(getActivity(), addFirstCountryFragment, "");
            } else if (result == Codes.ADD_SECOND_CITY) {
                MovementManager.replaceHomeFragment(getActivity(), new AddSecondCountryFragment(), "");
            } else if (result == Codes.ADD_Fourth_CITY) {
                MovementManager.replaceHomeFragment(getActivity(), new OutLeavingFragment(), "");
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.SELECT_CITIES) {
                showCities();
            }
        });
    }

    private void showCities() {
        popUpItems = tripsViewModel.citiesList;
        PopUpMenus.showCitiesPopUp(getActivity(), addArrivalBinding.edCities, popUpItems).setOnMenuItemClickListener(item -> {
            tripsViewModel.setCity(popUpItems.get(item.getItemId()).getName());
            tripsViewModel.getAddArrivalrequest().setFKCitiesId("" + popUpItems.get(item.getItemId()).getId());
            tripsViewModel.notifyChange();
            return false;
        });
    }

}