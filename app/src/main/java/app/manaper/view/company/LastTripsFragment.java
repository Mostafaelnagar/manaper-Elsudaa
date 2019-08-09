package app.manaper.view.company;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.databinding.FragmentLastTripsBinding;
import app.manaper.viewModels.HomeViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastTripsFragment extends BaseFragment {
    FragmentLastTripsBinding lastTripsBinding;
    HomeViewModels homeViewModels;


    public LastTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lastTripsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_last_trips, container, false);
        homeViewModels = new HomeViewModels(2);
        lastTripsBinding.setHomeViewModel(homeViewModels);
        liveDataListeners();
        return lastTripsBinding.getRoot();
    }

    private void liveDataListeners() {
        homeViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
