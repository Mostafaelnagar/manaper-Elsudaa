package app.manaper.view.company;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import app.manaper.BR;
import app.manaper.R;
import app.manaper.adapters.FragmentsAdapter;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.PopUpMenus;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.databinding.FragmentTripDetailsBinding;
import app.manaper.models.company.RelatedPlacesItem;
import app.manaper.models.company.TripDetailsResponse;
import app.manaper.view.company.addTrip.AddFirstCountryFragment;
import app.manaper.viewModels.HomeViewModels;
import app.manaper.viewModels.TripDetailsViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailsFragment extends BaseFragment {
    FragmentTripDetailsBinding detailsBinding;
    TripDetailsViewModels detailsViewModels;
    List<RelatedPlacesItem> placesList;
    int type = 1;

    public TripDetailsFragment() {
        // Required empty public constructor
        placesList = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_trip_details, container, false);
        detailsViewModels = new TripDetailsViewModels();
        detailsBinding.setTripDetailsViewModels(detailsViewModels);

        detailsBinding.tabLayout.addTab(detailsBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabArrivalText)));
        detailsBinding.tabLayout.addTab(detailsBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabFirstCityText)));
        detailsBinding.tabLayout.addTab(detailsBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabSecondCityText)));
        detailsBinding.tabLayout.addTab(detailsBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabDepartureCityText)));
        detailsBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        liveDataListeners();

        detailsBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if (position == 0) {
                    detailsBinding.linArrival.setVisibility(View.VISIBLE);
                    detailsBinding.linFirstCity.setVisibility(View.GONE);
                    detailsBinding.linSecondCity.setVisibility(View.GONE);
                    detailsBinding.linOutLeaving.setVisibility(View.GONE);

                } else if (position == 1) {
                    type = 1;
                    detailsBinding.linArrival.setVisibility(View.GONE);
                    detailsBinding.linFirstCity.setVisibility(View.VISIBLE);
                    detailsBinding.linSecondCity.setVisibility(View.GONE);
                    detailsBinding.linOutLeaving.setVisibility(View.GONE);
                    showHints();
                    if (detailsViewModels.getArrivalRequest().getTripArrDepsAirport() != null) {

                        getPlacesItems(type);
                        if (detailsViewModels.getFirstCityRequest().getTripFirSecsTransport().equals("0"))
                            detailsBinding.frtrans.setText("---");
                        else
                            detailsBinding.frtrans.setText(MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.transText));
                    }
                } else if (position == 2) {
                    type = 2;
                    detailsBinding.linArrival.setVisibility(View.GONE);
                    detailsBinding.linFirstCity.setVisibility(View.GONE);
                    detailsBinding.linSecondCity.setVisibility(View.VISIBLE);
                    detailsBinding.linOutLeaving.setVisibility(View.GONE);
                    showHints();
                    if (detailsViewModels.getArrivalRequest().getTripArrDepsAirport() != null) {
                        getPlacesItems(type);
                        if (detailsViewModels.getSecondCityRequest().getTripFirSecsTransport().equals("0"))
                            detailsBinding.sectrans.setText("--");
                        else
                            detailsBinding.sectrans.setText(MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.transText));
                    }
                } else if (position == 3) {
                    detailsBinding.linArrival.setVisibility(View.GONE);
                    detailsBinding.linFirstCity.setVisibility(View.GONE);
                    detailsBinding.linSecondCity.setVisibility(View.GONE);
                    detailsBinding.linOutLeaving.setVisibility(View.VISIBLE);

                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return detailsBinding.getRoot();
    }

    private void showHints() {
        detailsBinding.places.setText("");
        detailsBinding.places2.setText("");
        detailsBinding.fDate1.setText("");
        detailsBinding.fDate2.setText("");
        detailsBinding.fTime1.setText("");
        detailsBinding.fTime2.setText("");
    }

    public void getPlacesItems(int type) {
        //1=> for first city
        //2=> for second city
        if (type == 1) {
            placesList = detailsViewModels.getFirstCityRequest().getRelatedPlaces();
        } else if (type == 2) {
            placesList = detailsViewModels.getSecondCityRequest().getRelatedPlaces();
        }

    }

    private void liveDataListeners() {
        detailsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.SELECT_CITIES) {
                showPlaces(type);
            } else if (result == Codes.DOWNLOAD_FILE) {
                String url = URLS.PDF + UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getTripId() + "/en";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } else if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            }
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

    private void showPlaces(int type) {
        View view;
        if (type == 1)
            view = detailsBinding.places;
        else
            view = detailsBinding.places2;
        PopUpMenus.showPlacesPopUp(getActivity(), view, placesList).setOnMenuItemClickListener(item -> {
            detailsViewModels.setPlaces(placesList.get(item.getItemId()).getPlacesName());
            detailsViewModels.setDate(placesList.get(item.getItemId()).getPlacesDate());
            detailsViewModels.setTime(placesList.get(item.getItemId()).getPlacesTime());
            detailsViewModels.notifyChange();
            return false;
        });
    }
}
