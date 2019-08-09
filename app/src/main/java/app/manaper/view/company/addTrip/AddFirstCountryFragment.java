package app.manaper.view.company.addTrip;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.PopUpItem;
import app.manaper.base.PopUpMenus;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.common.Common;
import app.manaper.databinding.FragmentAddFirstCountryBinding;
import app.manaper.models.addTrip.places.PlacesDate;
import app.manaper.models.addTrip.places.PlacesItem;
import app.manaper.models.addTrip.places.PlacesName;
import app.manaper.models.cities.Cities;
import app.manaper.viewModels.AddTripsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFirstCountryFragment extends BaseFragment {
    FragmentAddFirstCountryBinding firstCityBinding;
    AddTripsViewModel tripsViewModel;
    List<Cities> popUpItems;
    String arriveDate;
    Bundle bundle;

    public AddFirstCountryFragment() {
        // Required empty public constructor
        popUpItems = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firstCityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_first_country, container, false);
        bundle = this.getArguments();
        if (bundle != null) {
            arriveDate = bundle.getString("Arrive_Date", "");
        } else {
            arriveDate = UserPreferenceHelper.getInstance(getActivity()).getLastTripDate();
        }
        tripsViewModel = new AddTripsViewModel();
        firstCityBinding.setAddTripsViewModel(tripsViewModel);
        liveDataListeners();

        return firstCityBinding.getRoot();

    }

    private void liveDataListeners() {
        tripsViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.ADD_FIRST_CITY) {

                AddSecondCountryFragment addSecondCountryFragment = new AddSecondCountryFragment();
                MovementManager.replaceHomeFragment(getActivity(), addSecondCountryFragment, "");
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.SELECT_CITIES) {
                showCities();
            } else if (result == Codes.SELECT_TRANSPORT_TYPE) {
                showTransTypes();
            } else if (result == Codes.ADD_NEW_PLACE) {
                Common.addNewView(firstCityBinding.getRoot(), getActivity(),
                        tripsViewModel.getPlaceTime(),
                        tripsViewModel.getPlaceDate(),
                        tripsViewModel.getPlace()
                );
                tripsViewModel.setPlaceTime(null);
                tripsViewModel.setPlaceDate(null);
                tripsViewModel.setPlace(null);
                tripsViewModel.notifyChange();
            } else if (result == Codes.ADD_SECOND_CITY) {
                Bundle bundle = new Bundle();
                bundle.putString("First_To_Date", tripsViewModel.getAddTripRequest().getTripFirSecsTo());
                Toast.makeText(getActivity(), "" + tripsViewModel.getAddTripRequest().getTripFirSecsTo(), Toast.LENGTH_SHORT).show();
                AddSecondCountryFragment addSecondCountryFragment = new AddSecondCountryFragment();
                addSecondCountryFragment.setArguments(bundle);
                MovementManager.replaceHomeFragment(getActivity(), addSecondCountryFragment, "");
            } else if (result == Codes.FROM_FIRST_TRIP) {
                loadCalendar(arriveDate, 0);
            } else if (result == Codes.To_FIRST_TRIP) {
                loadCalendar(tripsViewModel.getAddTripRequest().getTripFirSecsFrom(), 1);

            }
        });
    }

    private void showCities() {

        popUpItems = tripsViewModel.citiesList;
        PopUpMenus.showCitiesPopUp(getActivity(), firstCityBinding.edCities, popUpItems).setOnMenuItemClickListener(item -> {
            tripsViewModel.setCity(popUpItems.get(item.getItemId()).getName());
            tripsViewModel.getAddTripRequest().setFKCitiesId("" + popUpItems.get(item.getItemId()).getId());
            tripsViewModel.notifyChange();
            return false;
        });
    }

    private void showTransTypes() {
        final ArrayList<PopUpItem> popUpItems = PopUpMenus.getTransportationTypes(getActivity());
        PopUpMenus.showTransPopUp(getActivity(), firstCityBinding.edTransportation, popUpItems).setOnMenuItemClickListener(item -> {
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


    public void loadCalendar(String firstdate, int type) {
        Calendar mcurrentTime = Calendar.getInstance();
        int year = mcurrentTime.get(Calendar.YEAR);
        int month = mcurrentTime.get(Calendar.MONTH) + 1;
        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_DayNight_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String selectedDate = i + "-" + (++i1) + "-" + i2;
                        String res = MovementManager.dateValidation(firstdate, selectedDate);

                        if (type == 0) {
                            //todo from
                            if (res.equals(Codes.DATE_IS_REFUSED)) {
                                tripsViewModel.getAddTripRequest().setTripFirSecsFrom("");
                                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.dateValidation) + firstdate, Toast.LENGTH_SHORT).show();

                            } else {
                                tripsViewModel.getAddTripRequest().setTripFirSecsFrom(selectedDate);
                            }
                        } else if (type == 1) {
                            //todo to

                            if (res.equals(Codes.DATE_IS_REFUSED)) {
                                tripsViewModel.getAddTripRequest().setTripFirSecsTo("");
                                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.dateValidation) + firstdate, Toast.LENGTH_SHORT).show();
                            } else {
                                tripsViewModel.getAddTripRequest().setTripFirSecsTo(selectedDate);
                            }
                        }
                        tripsViewModel.notifyChange();

                    }
                }, year, month, 0);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }


}
