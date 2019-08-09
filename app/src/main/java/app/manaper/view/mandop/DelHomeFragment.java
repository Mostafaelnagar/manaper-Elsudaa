package app.manaper.view.mandop;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.databinding.FragmentDelHomeBinding;
import app.manaper.viewModels.HomeViewModels;
import app.manaper.viewModels.delegate.DelegateHomeViewModels;


public class DelHomeFragment extends BaseFragment {
    FragmentDelHomeBinding homeBinding;
    DelegateHomeViewModels homeViewModels;

    public DelHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_del_home, container, false);
        homeViewModels = new DelegateHomeViewModels();
        homeBinding.setHomeViewModel(homeViewModels);

        liveDataListeners();

        return homeBinding.getRoot();
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
        homeViewModels.getHomeAdapter().tripsList.clear();
        homeViewModels.getDelegateTrips();
    }
}
