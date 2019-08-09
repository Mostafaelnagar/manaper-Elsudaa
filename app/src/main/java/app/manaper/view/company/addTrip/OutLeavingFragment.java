package app.manaper.view.company.addTrip;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

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
import app.manaper.base.PopUpMenus;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.customViews.NavView;
import app.manaper.databinding.FragmentOutLeavingBinding;
import app.manaper.models.cities.Cities;
import app.manaper.view.HomeFragment;
import app.manaper.viewModels.AddTripsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutLeavingFragment extends BaseFragment {

    FragmentOutLeavingBinding leavingBinding;
    AddTripsViewModel tripsViewModel;
    List<Cities> popUpItems;
    Bundle bundle;
    String lastDate;

    public OutLeavingFragment() {
        // Required empty public constructor
        popUpItems = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        leavingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_out_leaving, container, false);
        bundle = this.getArguments();
        if (bundle != null) {
            lastDate = bundle.getString("Second_To_Date", "");
        } else {
            lastDate = UserPreferenceHelper.getInstance(getActivity()).getLastTripDate();
        }
        tripsViewModel = new AddTripsViewModel();

        leavingBinding.setAddTripsViewModel(tripsViewModel);
        liveDataListeners();

        return leavingBinding.getRoot();
    }

    private void liveDataListeners() {
        tripsViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.HOME_SCREEN) {
                new NavView(getActivity()).drawerBinding.toolbarTitle.setText(R.string.menu_home);
                MovementManager.replaceHomeFragment(getActivity(), new HomeFragment(), "");
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.SELECT_CITIES) {
                showCities();
            } else if (result == Codes.To_FIRST_TRIP) {
                loadCalendar(lastDate);

            }
        });
    }

    private void showCities() {
        popUpItems = tripsViewModel.citiesList;
        PopUpMenus.showCitiesPopUp(getActivity(), leavingBinding.edCities, popUpItems).setOnMenuItemClickListener(item -> {
            tripsViewModel.setCity(popUpItems.get(item.getItemId()).getName());
            tripsViewModel.getAddArrivalrequest().setFKCitiesId("" + popUpItems.get(item.getItemId()).getId());
            tripsViewModel.notifyChange();
            return false;
        });
    }

    public void loadCalendar(String firstdate) {
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
                        if (res.equals(Codes.DATE_IS_REFUSED)) {
                            tripsViewModel.getAddArrivalrequest().setTripArrDepsDate("");
                            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.dateValidation) + firstdate, Toast.LENGTH_SHORT).show();
                        } else {
                            tripsViewModel.getAddArrivalrequest().setTripArrDepsDate(selectedDate);
                        }
                        tripsViewModel.notifyChange();

                    }
                }, year, month, 0);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

}
