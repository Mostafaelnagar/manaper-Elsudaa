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
import app.manaper.databinding.FragmentMandopUrgentTripsBinding;
import app.manaper.viewModels.TripDetailsViewModels;
import app.manaper.viewModels.UrgentTripsViewModel;
import app.manaper.viewModels.delegate.UrgentHomeViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class MandopUrgentTripsFragment extends BaseFragment {
    FragmentMandopUrgentTripsBinding mandopTripsBinding;
    UrgentHomeViewModels tripsViewModel;
    int position = 0;

    public MandopUrgentTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mandopTripsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mandop_urgent_trips, container, false);
        tripsViewModel = new UrgentHomeViewModels();
        mandopTripsBinding.setHomeViewModel(tripsViewModel);
        mandopTripsBinding.tabLayoutMandop.addTab(mandopTripsBinding.tabLayoutMandop.newTab().setText(getActivity().getResources().getString(R.string.tabMandopTodayText)));
        mandopTripsBinding.tabLayoutMandop.addTab(mandopTripsBinding.tabLayoutMandop.newTab().setText(getActivity().getResources().getString(R.string.tabMandopTomorrowText)));
        mandopTripsBinding.tabLayoutMandop.setTabGravity(TabLayout.GRAVITY_FILL);
        tripsViewModel.getHomeAdapter().visiable = 1;
        tripsViewModel.getTodayTrips();
        mandopTripsBinding.tabLayoutMandop.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if (position == 0) {
                    tripsViewModel.getHomeAdapter().tripsList.clear();
                    tripsViewModel.getHomeAdapter().visiable = 1;
                    tripsViewModel.getTodayTrips();
                } else {
                    tripsViewModel.getHomeAdapter().tripsList.clear();
                    tripsViewModel.getHomeAdapter().visiable = 2;
                    tripsViewModel.getTomorrowTrips();
                }
                tripsViewModel.notifyChange();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        liveDataListeners();
        return mandopTripsBinding.getRoot();
    }

    private void liveDataListeners() {
        tripsViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (position == 0) {
            tripsViewModel.getHomeAdapter().tripsList.clear();
            tripsViewModel.getTodayTrips();
        } else {
            tripsViewModel.getHomeAdapter().tripsList.clear();
            tripsViewModel.getTomorrowTrips();
        }
        tripsViewModel.notifyChange();
    }
}
