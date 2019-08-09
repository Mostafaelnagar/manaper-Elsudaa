package app.manaper.view.company.addTrip;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.PopUpItem;
import app.manaper.base.PopUpMenus;
import app.manaper.base.constantsutils.Codes;
import app.manaper.common.Common;
import app.manaper.databinding.FragmentAddSecondCountryBinding;
import app.manaper.databinding.FragmentInsideLeavingBinding;
import app.manaper.models.addTrip.places.PlacesDate;
import app.manaper.models.addTrip.places.PlacesItem;
import app.manaper.models.addTrip.places.PlacesName;
import app.manaper.models.cities.Cities;
import app.manaper.viewModels.AddTripsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsideLeavingFragment extends BaseFragment {
    FragmentInsideLeavingBinding insideLeavingBinding;
    AddTripsViewModel tripsViewModel;
    List<Cities> popUpItems;

    public InsideLeavingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        insideLeavingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_inside_leaving, container, false);
        tripsViewModel = new AddTripsViewModel();
        insideLeavingBinding.setAddTripsViewModel(tripsViewModel);
        liveDataListeners();

        return insideLeavingBinding.getRoot();
    }

    private void liveDataListeners() {
        tripsViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.ADD_THIRD_CITY) {
                MovementManager.replaceHomeFragment(getActivity(), new InsideLeavingFragment(), "");
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.ADD_Fourth_CITY) {
                MovementManager.replaceHomeFragment(getActivity(), new OutLeavingFragment(), "");
            } else if (result == Codes.SELECT_CITIES) {
                //showCities();
            } else if (result == Codes.SELECT_TRANSPORT_TYPE) {
                showTransTypes();
            }
        });
    }

    private void showCities(int type) {
        popUpItems = tripsViewModel.citiesList;
        if (type == 0) {
            PopUpMenus.showCitiesPopUp(getActivity(), insideLeavingBinding.edCities, popUpItems).setOnMenuItemClickListener(item -> {
                tripsViewModel.setCity(popUpItems.get(item.getItemId()).getName());
                tripsViewModel.getAddTripRequest().setFKCitiesId("" + popUpItems.get(item.getItemId()).getId());
                tripsViewModel.notifyChange();
                return false;
            });
        } else {
            PopUpMenus.showCitiesPopUp(getActivity(), insideLeavingBinding.edCities2, popUpItems).setOnMenuItemClickListener(item -> {
                tripsViewModel.setCity(popUpItems.get(item.getItemId()).getName());
                tripsViewModel.getAddTripRequest().setFKCitiesId("" + popUpItems.get(item.getItemId()).getId());
                tripsViewModel.notifyChange();
                return false;
            });
        }
    }

    private void showTransTypes() {
        final ArrayList<PopUpItem> popUpItems = PopUpMenus.getTransportationTypes(getActivity());
        PopUpMenus.showTransPopUp(getActivity(), insideLeavingBinding.edTransportation, popUpItems).setOnMenuItemClickListener(item -> {
            String name = popUpItems.get(item.getItemId()).getName();
            tripsViewModel.setTransType(name);
            tripsViewModel.getAddTripRequest().setTripFirSecsTransport("" + popUpItems.get(item.getItemId()).getId());
            if (name.equals(getActivity().getResources().getString(R.string.manaper)))
                tripsViewModel.setWithTransport(1);
            else
                tripsViewModel.setWithTransport(0);
            tripsViewModel.notifyChange();
            return false;
        });
    }

}
