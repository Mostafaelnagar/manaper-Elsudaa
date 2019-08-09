package app.manaper.view.company;


import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.common.Common;
import app.manaper.databinding.FragmentArrivalBinding;
import app.manaper.models.company.TripDetailsResponse;
import app.manaper.viewModels.TripDetailsViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArrivalFragment extends BaseFragment {
    FragmentArrivalBinding arrivalBinding;
    TripDetailsViewModels detailsViewModels;

    public ArrivalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        arrivalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_arrival, container, false);
        detailsViewModels = new TripDetailsViewModels();
        arrivalBinding.setTripDetailsViewModels(detailsViewModels);
         return arrivalBinding.getRoot();
    }
}
