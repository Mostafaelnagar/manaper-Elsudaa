package app.manaper.view.mandop;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import app.manaper.R;
import app.manaper.adapters.FragmentsAdapter;
import app.manaper.base.BaseFragment;
import app.manaper.databinding.FragmentMandopMyTripBinding;
import app.manaper.viewModels.HomeViewModels;
import app.manaper.viewModels.delegate.MyTripHomeViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class MandopMyTripFragment extends BaseFragment {
    FragmentMandopMyTripBinding myTripBinding;
    MyTripHomeViewModels homeViewModels;
    int position = 0;

    public MandopMyTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myTripBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mandop_my_trip, container, false);
        homeViewModels = new MyTripHomeViewModels();
        myTripBinding.setHomeViewModel(homeViewModels);
        myTripBinding.tabLayoutMandop.addTab(myTripBinding.tabLayoutMandop.newTab().setText(getActivity().getResources().getString(R.string.menuMyTrips)));
        myTripBinding.tabLayoutMandop.addTab(myTripBinding.tabLayoutMandop.newTab().setText(getActivity().getResources().getString(R.string.tabMandopAllTripsText)));
        myTripBinding.tabLayoutMandop.setTabGravity(TabLayout.GRAVITY_FILL);
        homeViewModels.getMyTrips();
        myTripBinding.tabLayoutMandop.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if (position == 0) {
                    homeViewModels.getHomeAdapter().tripsList.clear();
                    homeViewModels.getMyTrips();
                } else {
                    homeViewModels.getHomeAdapter().tripsList.clear();
                    homeViewModels.getDelAllTrips();
                }
                homeViewModels.notifyChange();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        liveDataListeners();
        return myTripBinding.getRoot();
    }


    private void liveDataListeners() {
        homeViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (position == 0) {
            homeViewModels.getHomeAdapter().tripsList.clear();
            homeViewModels.getMyTrips();
        } else {
            homeViewModels.getHomeAdapter().tripsList.clear();
            homeViewModels.getDelAllTrips();
        }
        homeViewModels.notifyChange();
    }
}
