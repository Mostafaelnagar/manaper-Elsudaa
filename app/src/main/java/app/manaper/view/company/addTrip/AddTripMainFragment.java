package app.manaper.view.company.addTrip;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import app.manaper.R;
import app.manaper.common.Common;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTripMainFragment extends Fragment {


    public AddTripMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_trip_main, container, false);
        Common.replaceFragment(getActivity(), R.id.addTripContainer, new AddArrivalFragment());
        return view;
    }

}
