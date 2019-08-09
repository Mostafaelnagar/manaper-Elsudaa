package app.manaper.view.company;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.databinding.FragmentFirstCityBinding;
import app.manaper.databinding.FragmentSecondBinding;
import app.manaper.viewModels.TripDetailsViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondCityFragment extends Fragment {
    FragmentSecondBinding secondBinding;
    TripDetailsViewModels detailsViewModels;


    public SecondCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        secondBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        detailsViewModels = new TripDetailsViewModels();
        secondBinding.setTripDetailsViewModels(detailsViewModels);
        return secondBinding.getRoot();
    }

}
