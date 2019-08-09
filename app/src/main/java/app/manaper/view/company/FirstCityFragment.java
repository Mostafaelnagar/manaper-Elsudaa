package app.manaper.view.company;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.databinding.FragmentArrivalBinding;
import app.manaper.databinding.FragmentFirstCityBinding;
import app.manaper.databinding.FragmentTripDetailsBinding;
import app.manaper.viewModels.TripDetailsViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstCityFragment extends Fragment {
    FragmentFirstCityBinding firstCityBinding;
    TripDetailsViewModels detailsViewModels;

    public FirstCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firstCityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_city, container, false);
        detailsViewModels = new TripDetailsViewModels();
        firstCityBinding.setTripDetailsViewModels(detailsViewModels);
        return firstCityBinding.getRoot();
    }

}
