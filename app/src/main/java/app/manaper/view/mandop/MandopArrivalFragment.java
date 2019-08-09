package app.manaper.view.mandop;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.databinding.FragmentMandopArrivalBinding;
import app.manaper.viewModels.TripDetailsViewModels;
import app.manaper.viewModels.delegate.DelegateTripDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MandopArrivalFragment extends BaseFragment {
    FragmentMandopArrivalBinding arrivalBinding;
    DelegateTripDetailsViewModel viewModel;

    public MandopArrivalFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        arrivalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mandop_arrival, container, false);
        viewModel = new DelegateTripDetailsViewModel();
        arrivalBinding.setTripDetailsViewModels(viewModel);
        liveDataListeners();
         return arrivalBinding.getRoot();
    }

    private void liveDataListeners() {
        viewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.BACK) {
                UserPreferenceHelper.getInstance(getActivity()).removeArrDepsId();
                ((Activity) getActivity()).finish();
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
